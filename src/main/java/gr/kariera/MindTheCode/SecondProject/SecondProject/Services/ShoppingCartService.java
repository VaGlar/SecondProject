package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.ShoppingCart;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.CartItem;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart(User user);

    int getItemsNumber(User user);

    CartItem findCartItemById(Long cartItemId);

    CartItem addProductToShoppingCart(Product product , User user, int qty, String size);

    void clearShoppingCart(User user);

    void updateCartItem(CartItem cartItem, Integer qty);

    void removeCartItem(CartItem cartItem);

}