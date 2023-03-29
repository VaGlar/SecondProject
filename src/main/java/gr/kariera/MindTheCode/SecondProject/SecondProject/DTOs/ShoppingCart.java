package gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

    private List<CartItem> cartItems;

    public ShoppingCart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public BigDecimal getGrandTotal() {
        BigDecimal cartTotal = new BigDecimal(0);
        for (CartItem item : this.cartItems) {
            cartTotal = cartTotal.add(item.getSubtotal());
        }
        return cartTotal;
    }
    public void removeCartItem(CartItem cartItem) {
        cartItems.removeIf(item -> Objects.equals(item.getId(), cartItem.getId()));
    }

    public void clearItems() {
        cartItems.clear();
    }

    public  CartItem findCartItemByProduct(Integer id){
        for(CartItem item : this.cartItems){
            if(item.getProduct().getId().equals(id)){
                return item;
            }
        }
        return null;
    }


    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
