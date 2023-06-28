package ra.service;

import ra.model.CartItem;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<CartItem> cartItems;

    public CartService() {
        this.cartItems = new ArrayList<>();
    }

    public void displayAllProducts(List<Product> productList) {
        System.out.println("Danh sách tất cả sản phẩm đang được bán:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void addProductToCart(int productId, int quantity, List<Product> productList) {
        Product productToAdd = null;
        for (Product product : productList) {
            if (Integer.parseInt(product.getProductId()) == productId) {
                productToAdd = product;
                break;
            }
        }

        if (productToAdd != null) {
            int cartItemId = generateCartItemId();
            double price = productToAdd.getProductPrice();
            CartItem cartItem = new CartItem(cartItemId, productToAdd, price, quantity);
            cartItems.add(cartItem);
            System.out.println("Đã thêm sản phẩm vào giỏ hàng!");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã sản phẩm đã nhập.");
        }
    }

    public void displayCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng rỗng.");
        } else {
            System.out.println("Danh sách sản phẩm trong giỏ hàng:");
            for (CartItem cartItem : cartItems) {
                System.out.println(cartItem);
            }
        }
    }

    public void updateQuantity(int cartItemId, int newQuantity) {
        CartItem cartItemToUpdate = null;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                cartItemToUpdate = cartItem;
                break;
            }
        }

        if (cartItemToUpdate != null) {
            Product product = cartItemToUpdate.getProduct();
            int currentQuantity = cartItemToUpdate.getQuantity();
            int stock = product.getStock();

            if (newQuantity > currentQuantity) {
                int quantityDiff = newQuantity - currentQuantity;
                if (stock >= quantityDiff) {
                    cartItemToUpdate.setQuantity(newQuantity);
                    product.setStock(stock - quantityDiff);
                    System.out.println("Đã cập nhật số lượng sản phẩm thành công!");
                } else {
                    System.out.println("Số lượng sản phẩm không đủ trong kho.");
                }
            } else if (newQuantity < currentQuantity) {
                int quantityDiff = currentQuantity - newQuantity;
                cartItemToUpdate.setQuantity(newQuantity);
                product.setStock(stock + quantityDiff);
                System.out.println("Đã cập nhật số lượng sản phẩm thành công!");
            } else {
                System.out.println("Số lượng sản phẩm không thay đổi.");
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }

    public void removeProductFromCart(int cartItemId) {
        CartItem cartItemToRemove = null;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                cartItemToRemove = cartItem;
                break;
            }
        }

        if (cartItemToRemove != null) {
            Product product = cartItemToRemove.getProduct();
            int quantity = cartItemToRemove.getQuantity();
            int stock = product.getStock();

            cartItems.remove(cartItemToRemove);
            product.setStock(stock + quantity);
            System.out.println("Đã xóa sản phẩm ra khỏi giỏ hàng.");
        } else {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }

    public void removeAllProductsFromCart() {
        if (!cartItems.isEmpty()) {
            for (CartItem cartItem : cartItems) {
                Product product = cartItem.getProduct();
                int quantity = cartItem.getQuantity();
                int stock = product.getStock();
                product.setStock(stock + quantity);
            }
            cartItems.clear();
            System.out.println("Đã xóa toàn bộ sản phẩm trong giỏ hàng.");
        } else {
            System.out.println("Giỏ hàng rỗng.");
        }
    }

    private int generateCartItemId() {
        return cartItems.size() + 1;
    }


    public void addCartItem(Product product, int quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);
        System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
    }

    public void updateCartItemQuantity(int cartItemId, int quantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductId().equals(cartItemId)) {
                cartItem.setQuantity(quantity);
                System.out.println("Đã cập nhật số lượng sản phẩm trong giỏ hàng.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm trong giỏ hàng với mã đã nhập.");
    }

    public void removeCartItem(int cartItemId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductId().equals(cartItemId)) {
                cartItems.remove(cartItem);
                System.out.println("Đã xóa sản phẩm khỏi giỏ hàng.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm trong giỏ hàng với mã đã nhập.");
    }

    public void clearCart() {
        cartItems.clear();
        System.out.println("Đã xóa toàn bộ sản phẩm trong giỏ hàng.");
    }

    public void displayAllCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
        } else {
            System.out.println("Danh sách sản phẩm trong giỏ hàng:");
            for (CartItem cartItem : cartItems) {
                System.out.println(cartItem);
            }
        }
    }
}
