package gr.kariera.MindTheCode.SecondProject.SecondProject.MVC;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.ShoppingCart;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.CartItem;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.ProductService;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartMVCController {

    private ProductService productService;

    private ShoppingCartService shoppingCartService;

    @RequestMapping("/cart")
    public String shoppingCart(Model model,User user) {

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);
        model.addAttribute("cartItemList", shoppingCart.getCartItems());
        model.addAttribute("shoppingCart", shoppingCart);
        return "shoppingCart";
    }

    @RequestMapping("/add-item")
    public String addItem(@ModelAttribute("article") Product product, @RequestParam("qty") String qty,
                          @RequestParam("size") String size, RedirectAttributes attributes, Model model,User user) {
        product = productService.findProductById(product.getId());
        if (!product.hasStock(Integer.parseInt(qty))) {
            attributes.addFlashAttribute("notEnoughStock", true);
            return "redirect:/article-detail?id="+product.getId();
        }
        shoppingCartService.addProductToShoppingCart(product, user, Integer.parseInt(qty), size);
        attributes.addFlashAttribute("addProductSuccess", true);
        return "redirect:/article-detail?id="+product.getId();
    }
    @RequestMapping("/update-item")
    public String updateItemQuantity(@RequestParam("id") Long cartItemId,
                                     @RequestParam("qty") Integer qty, Model model) {
        CartItem cartItem = shoppingCartService.findCartItemById(cartItemId);
        if (cartItem.canUpdateQty(qty)) {
            shoppingCartService.updateCartItem(cartItem, qty);
        }
        return "redirect:/shopping-cart/cart";
    }

    @RequestMapping("/remove-item")
    public String removeItem(@RequestParam("id") Long id) {
        shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));
        return "redirect:/shopping-cart/cart";
    }
}
