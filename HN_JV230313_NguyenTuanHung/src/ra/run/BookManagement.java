package ra.run;

import ra.service.CartService;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private static List<Catalog> catalogList = new ArrayList<>();
    private static List<Product> productList = new ArrayList<>();
    static ProductService productService = new ProductService();
    static CartService cartService = new CartService();

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        catalogList.add(new Catalog(1,"quan"));
        catalogList.add(new Catalog(2,"quan1"));
        catalogList.add(new Catalog(3,"quan2"));
        catalogList.add(new Catalog(4,"quan3"));
        boolean isExit = false;

        while (!isExit) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    catalogManagement();
                    break;
                case 2:
                    productManagement();
                    break;
                case 3:
                    handleUserMenu(productService, cartService);
                    break;
                case 4:
                    isExit = true;
                    System.out.println("Đã thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("**************************BASIC-MENU**************************");
        System.out.println("1. Quản lý danh mục");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Dành cho người dùng");
        System.out.println("4. Thoát");
        System.out.println("**************************************************************");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static void catalogManagement() {
        boolean isBack = false;
        while (!isBack) {
            displayCatalogMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCatalog();
                    break;
                case 2:
                    displayCatalogs();
                    break;
                case 3:
                    updateCatalogName();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    isBack = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void displayCatalogMenu() {
        System.out.println("********************CATALOG-MANAGEMENT********************");
        System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
        System.out.println("2. Hiển thị thông tin tất cả các danh mục");
        System.out.println("3. Sửa tên danh mục theo mã danh mục");
        System.out.println("4. Xóa danh muc theo mã danh mục (lưu ý không xóa khi có sản phẩm)");
        System.out.println("5. Quay lại");
        System.out.println("**********************************************************");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static void addCatalog() {
        System.out.print("Nhập số danh mục thêm mới: ");
        int numCatalogs = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCatalogs; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1));
            System.out.print("Nhập mã danh mục: ");
            int catalogId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nhập tên danh mục: ");
            String catalogName = scanner.nextLine();

            Catalog catalog = new Catalog(catalogId, catalogName);
            catalogList.add(catalog);
        }

        System.out.println("Đã thêm mới danh mục thành công!");
    }

    private static void displayCatalogs() {
        if (catalogList.isEmpty()) {
            System.out.println("Danh sách danh mục rỗng.");
        } else {
            System.out.println("Danh sách các danh mục:");
            for (Catalog catalog : catalogList) {
                System.out.println(catalog);
            }
        }
    }
    private static void updateCatalogName() {
        System.out.print("Nhập mã danh mục cần sửa: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng new line

        Catalog catalogToUpdate = null;
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogId() == catalogId) {
                catalogToUpdate = catalog;
                break;
            }
        }

        if (catalogToUpdate != null) {
            System.out.print("Nhập tên danh mục mới: ");
            String newCatalogName = scanner.nextLine();
            catalogToUpdate.setCatalogName(newCatalogName);
            System.out.println("Đã cập nhật tên danh mục thành công!");
        } else {
            System.out.println("Không tìm thấy danh mục với mã danh mục đã nhập.");
        }
    }


    private static void deleteCatalog() {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine();

        Catalog catalogToDelete = null;
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogId() == catalogId) {
                catalogToDelete = catalog;
                break;
            }
        }

        if (catalogToDelete != null) {
            if (isCatalogInUse(catalogToDelete)) {
                System.out.println("Không thể xóa danh mục đã có sản phẩm.");
            } else {
                catalogList.remove(catalogToDelete);
                System.out.println("Đã xóa danh mục thành công!");
            }
        } else {
            System.out.println("Không tìm thấy danh mục với mã danh mục đã nhập.");
        }
    }

    private static boolean isCatalogInUse(Catalog catalog) {
        for (Product product : productList) {
            if (product.getCatalog().equals(catalog)) {
                return true;
            }
        }
        return false;
    }

    private static void productManagement() {
        boolean isBack = false;

        while (!isBack) {
            displayProductMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    isBack = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void displayProductMenu() {
        System.out.println("********************PRODUCT-MANAGEMENT********************");
        System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
        System.out.println("2. Hiển thị thông tin các sản phẩm");
        System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
        System.out.println("4. Xóa sản phẩm theo mã");
        System.out.println("5. Tìm kiếm sách theo tên sách");
        System.out.println("6. Thay đổi thông tin của sách theo mã sách");
        System.out.println("7. Quay lại");
        System.out.println("**********************************************************");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static void addProduct() {
        System.out.print("Nhập số sản phẩm thêm mới: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numProducts; i++) {
            System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1));
            System.out.print("Nhập mã sản phẩm: ");
            String productId = scanner.nextLine();

            System.out.print("Nhập tên sản phẩm: ");
            String productName = scanner.nextLine();

            System.out.print("Nhập giá sản phẩm: ");
            double productPrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Nhập mô tả sản phẩm: ");
            String description = scanner.nextLine();

            System.out.print("Nhập số lượng sản phẩm: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nhập mã danh mục cho sản phẩm: ");
            int catalogId = scanner.nextInt();
            scanner.nextLine();

            Catalog catalog = null;
            for (Catalog c : catalogList) {
                if (c.getCatalogId() == catalogId) {
                    catalog = c;
                    break;
                }
            }
            if (catalog != null) {
                Product product = new Product(productId, productName, productPrice, description, stock, catalog);
                productList.add(product);
                System.out.println("Đã thêm mới sản phẩm thành công!");
            } else {
                System.out.println("Không tìm thấy danh mục với mã danh mục đã nhập. Sản phẩm không được thêm mới.");
            }
        }
    }

    private static void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
        } else {
            System.out.println("Danh sách các sản phẩm:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    private static void deleteProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String productId = scanner.nextLine();

        Product productToDelete = null;
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                productToDelete = product;
                break;
            }
        }

        if (productToDelete != null) {
            productList.remove(productToDelete);
            System.out.println("Đã xóa sản phẩm thành công!");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã sản phẩm đã nhập.");
        }
    }

    private static void handleUserMenu(ProductService productService, CartService cartService) {
        boolean isBack = false;

        while (!isBack) {
            displayUserMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addToCart(productService, cartService);
                    break;
                case 3:
                    displayCartItems(cartService);
                    break;
                case 4:
                    updateCartItemQuantity(cartService);
                    break;
                case 5:
                    removeCartItem(cartService);
                    break;
                case 6:
                    clearCart(cartService);
                    break;
                case 7:
                    isBack = true;
                    System.out.println("Quay lại BASIC-MENU.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }

    private static void displayUserMenu() {
        System.out.println("**************************MENU-USER**************************");
        System.out.println("1. Xem danh sách sản phẩm");
        System.out.println("2. Thêm vào giỏ hàng");
        System.out.println("3. Xem tất cả sản phẩm giỏ hàng");
        System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng");
        System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
        System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng");
        System.out.println("7. Quay lại");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static void displayAllProducts(ProductService productService) {
        System.out.println("Danh sách sản phẩm:");
        productService.displayAllProducts();
    }

    private static void addToCart(ProductService productService, CartService cartService) {
        System.out.print("Nhập mã sản phẩm muốn thêm vào giỏ hàng: ");
        String productId = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addCartItem(product, quantity);
            System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã đã nhập.");
        }
    }

    private static void displayCartItems(CartService cartService) {
        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        cartService.displayAllCartItems();
    }

    private static void updateCartItemQuantity(CartService cartService) {
        System.out.print("Nhập mã sản phẩm trong giỏ hàng: ");
        int cartItemId = scanner.nextInt();
        System.out.print("Nhập số lượng mới: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        cartService.updateCartItemQuantity(cartItemId, quantity);
        System.out.println("Đã cập nhật số lượng sản phẩm trong giỏ hàng.");
    }
    private static void removeCartItem(CartService cartService) {
        System.out.print("Nhập mã sản phẩm trong giỏ hàng: ");
        int cartItemId = scanner.nextInt();
        scanner.nextLine();

        cartService.removeCartItem(cartItemId);
        System.out.println("Đã xóa sản phẩm khỏi giỏ hàng.");
    }
    private static void clearCart(CartService cartService) {
        cartService.clearCart();
        System.out.println("Đã xóa toàn bộ sản phẩm trong giỏ hàng.");
    }
}

