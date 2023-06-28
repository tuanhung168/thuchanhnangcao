package ra.service;

import ra.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements Iservice<Product, String> {
    private List<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<>();
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public Product findById(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        Product productToRemove = null;
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }

    public void displayAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
        } else {
            System.out.println("Danh sách sản phẩm:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    public Product getProductById(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Trả về null nếu không tìm thấy sản phẩm với mã đã nhập
    }

}
