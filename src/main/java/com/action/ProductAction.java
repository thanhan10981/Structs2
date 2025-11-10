package com.action;

import com.dao.ProductDAO;
import com.model.Product;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ProductService;
import java.util.Locale;
import com.opensymphony.xwork2.ActionContext;
import java.util.List;

public class ProductAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    // View Model
    private List<Product> products;
    private Product product;
    private String lang = "vi";
    private int id;

    // Service/DAO
    private final ProductService service = new ProductService();

    // Getters/Setters
    public List<Product> getProducts() { return products; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public String getLang() { return lang; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // ✅ Cập nhật Locale khi nhận lang
    public void setLang(String lang) {
        if (lang == null || lang.isEmpty()) {
            this.lang = "vi";
        } else {
            this.lang = lang;
        }

        // ⚡ Áp dụng Locale ngay khi set
        if ("en".equalsIgnoreCase(this.lang)) {
            ActionContext.getContext().setLocale(Locale.ENGLISH);
        } else {
            ActionContext.getContext().setLocale(new Locale("vi", "VN"));
        }
    }

    // Danh sách
    @Override
    public String execute() {
        products = service.getProducts(lang);
        return SUCCESS;
    }

    // Thêm sản phẩm
    public String add() {
        new ProductDAO().addProduct(product);
        addActionMessage("✅ Thêm sản phẩm thành công!");
        return SUCCESS;
    }

    // Xóa sản phẩm
    public String delete() {
        new ProductDAO().deleteProduct(id);
        products = service.getProducts(lang);
        addActionMessage("🗑️ Xóa sản phẩm ID: " + id + " thành công!");
        return SUCCESS;
    }

    // Mở form sửa
    public String edit() {
        product = new ProductDAO().getById(id);
        if (product == null) {
            addActionError("Không tìm thấy sản phẩm ID " + id);
            return ERROR;
        }
        return "edit";
    }

    // Cập nhật sản phẩm
    public String update() {
        new ProductDAO().updateProduct(product);
        addActionMessage("✏️ Đã cập nhật sản phẩm thành công!");
        products = service.getProducts(lang);
        return SUCCESS;
    }
}
