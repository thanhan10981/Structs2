package com.dao;

import com.model.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getProducts(String lang) {
        List<Product> list = new ArrayList<>();

        String sql = """
        SELECT p.ProductID, pt.LanguageID, pt.ProductName, pt.ProductDescription,
               p.Price, p.Weight, pct.CategoryName
        FROM Product p
        JOIN ProductTranslation pt ON p.ProductID = pt.ProductID
        JOIN ProductCategoryTranslation pct ON p.ProductCategoryID = pct.ProductCategoryID
        WHERE pt.LanguageID = ? AND pct.LanguageID = ?
        ORDER BY p.ProductID
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lang.trim());
            ps.setString(2, lang.trim());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setPrice(rs.getDouble("Price"));
                p.setWeight(rs.getDouble("Weight"));
                p.setCategoryName(rs.getString("CategoryName"));

                if ("vi".equalsIgnoreCase(lang)) {
                    p.setName_vi(rs.getString("ProductName"));
                    p.setDescription_vi(rs.getString("ProductDescription"));
                } else {
                    p.setName_en(rs.getString("ProductName"));
                    p.setDescription_en(rs.getString("ProductDescription"));
                }

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi lấy sản phẩm: " + e.getMessage());
        }

        return list;
    }

    public void addProduct(Product p) {
        String sqlGetMaxId = "SELECT ISNULL(MAX(ProductID), 0) + 1 FROM Product";
        String sqlProduct = "INSERT INTO Product (ProductID, Price, Weight, ProductCategoryID) VALUES (?, ?, ?, ?)";
        String sqlTrans = "INSERT INTO ProductTranslation (ProductID, LanguageID, ProductName, ProductDescription) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            int newId = 0;
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(sqlGetMaxId);
                if (rs.next()) newId = rs.getInt(1);
            }

            try (PreparedStatement ps1 = conn.prepareStatement(sqlProduct)) {
                ps1.setInt(1, newId);
                ps1.setDouble(2, p.getPrice());
                ps1.setDouble(3, p.getWeight());
                ps1.setInt(4, p.getCategoryID());
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(sqlTrans)) {
                ps2.setInt(1, newId);
                ps2.setString(2, "vi");
                ps2.setString(3, p.getName_vi());
                ps2.setString(4, p.getDescription_vi());
                ps2.executeUpdate();

                ps2.setInt(1, newId);
                ps2.setString(2, "en");
                ps2.setString(3, p.getName_en());
                ps2.setString(4, p.getDescription_en());
                ps2.executeUpdate();
            }

            conn.commit();
            System.out.println("✅ Đã thêm sản phẩm đa ngôn ngữ (ID: " + newId + ") thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sqlTrans = "DELETE FROM ProductTranslation WHERE ProductID = ?";
        String sqlProd = "DELETE FROM Product WHERE ProductID = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps1 = conn.prepareStatement(sqlTrans);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(sqlProd);
            ps2.setInt(1, id);
            ps2.executeUpdate();

            System.out.println("🗑️ Đã xóa sản phẩm ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getById(int id) {
        String sql = """
        SELECT p.ProductID, p.Price, p.Weight, pct.CategoryName,
               pt_vi.ProductName AS NameVI, pt_vi.ProductDescription AS DescVI,
               pt_en.ProductName AS NameEN, pt_en.ProductDescription AS DescEN,
               p.ProductCategoryID
        FROM Product p
        JOIN ProductTranslation pt_vi ON p.ProductID = pt_vi.ProductID AND pt_vi.LanguageID='vi'
        JOIN ProductTranslation pt_en ON p.ProductID = pt_en.ProductID AND pt_en.LanguageID='en'
        JOIN ProductCategoryTranslation pct ON p.ProductCategoryID = pct.ProductCategoryID AND pct.LanguageID='vi'
        WHERE p.ProductID = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setPrice(rs.getDouble("Price"));
                p.setWeight(rs.getDouble("Weight"));
                p.setCategoryID(rs.getInt("ProductCategoryID"));
                p.setCategoryName(rs.getString("CategoryName"));
                p.setName_vi(rs.getString("NameVI"));
                p.setDescription_vi(rs.getString("DescVI"));
                p.setName_en(rs.getString("NameEN"));
                p.setDescription_en(rs.getString("DescEN"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduct(Product p) {
        String sql1 = "UPDATE Product SET Price=?, Weight=?, ProductCategoryID=? WHERE ProductID=?";
        String sql2 = "UPDATE ProductTranslation SET ProductName=?, ProductDescription=? WHERE ProductID=? AND LanguageID=?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                ps1.setDouble(1, p.getPrice());
                ps1.setDouble(2, p.getWeight());
                ps1.setInt(3, p.getCategoryID());
                ps1.setInt(4, p.getId());
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                ps2.setString(1, p.getName_vi());
                ps2.setString(2, p.getDescription_vi());
                ps2.setInt(3, p.getId());
                ps2.setString(4, "vi");
                ps2.executeUpdate();

                ps2.setString(1, p.getName_en());
                ps2.setString(2, p.getDescription_en());
                ps2.setInt(3, p.getId());
                ps2.setString(4, "en");
                ps2.executeUpdate();
            }

            conn.commit();
            System.out.println("✅ Đã cập nhật sản phẩm ID " + p.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
