package gr.kariera.MindTheCode.SecondProject.SecondProject.MVC;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductMVCController {
    private final ProductService service;

    public ProductMVCController(ProductService service) {
        this.service = service;
    }
    @GetMapping("/index")
    public String greeting(
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("products", service.getProduct(description, page, size, sort));
        model.addAttribute("sort", sort);
        model.addAttribute("description", description);
        return "products";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product",  new Product());
        return "create-or-update-products";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product",  service.getById(id));
        return "create-or-update-products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        service.deleteProduct(id);
        return "redirect:/products/index";
    }


    @PostMapping("/create-or-update")
    public String saveCreateForm(@RequestParam Optional<Integer> id, @ModelAttribute Product product , Model model) {
        try {
            service.createOrUpdateProduct(id.isPresent() ? id.get() : null, product);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }

        return "redirect:/products/index";
    }



}
