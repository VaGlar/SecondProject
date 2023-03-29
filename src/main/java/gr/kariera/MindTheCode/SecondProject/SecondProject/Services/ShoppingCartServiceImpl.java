package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.ShoppingCart;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.CartItem;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart getShoppingCart(User user) {
        return new ShoppingCart(cartItemRepository.findAllByUserAndOrderIsNull(user));
    }
    @Override
    @Cacheable("itemcount")
    public int getItemsNumber(User user) {
        return cartItemRepository.countDistinctByUserAndOrderIsNull(user);
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
        return opt.get();
    }

    @Override
    @CacheEvict(value = "itemcount", allEntries = true)
    public CartItem addProductToShoppingCart(Product product, User user, int qty, String size) {
        ShoppingCart shoppingCart = this.getShoppingCart(user);
        CartItem cartItem = shoppingCart.findCartItemByProduct(product.getId());
        if (cartItem != null ) {
            cartItem.addQuantity(qty);
            cartItem = cartItemRepository.save(cartItem);
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQty(qty);
            cartItem = cartItemRepository.save(cartItem);
        }
        return cartItem;
    }

    @Override
    @CacheEvict(value = "itemcount", allEntries = true)
    public void removeCartItem(CartItem cartItem) {
        cartItemRepository.deleteById(cartItem.getId());
    }

    @Override
    @CacheEvict(value = "itemcount", allEntries = true)
    public void updateCartItem(CartItem cartItem, Integer qty) {
        if (qty == null || qty <= 0) {
            this.removeCartItem(cartItem);
        } else if (cartItem.getProduct().hasStock(qty)) {
            cartItem.setQty(qty);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    @CacheEvict(value = "itemcount", allEntries = true)
    public void clearShoppingCart(User user) {
        cartItemRepository.deleteAllByUserAndOrderIsNull(user);
    }
}

