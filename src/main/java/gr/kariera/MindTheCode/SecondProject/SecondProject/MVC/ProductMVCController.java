package gr.kariera.MindTheCode.SecondProject.SecondProject.MVC;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.ProductUpdateDto;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.ProductRepository;
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
    private final ProductRepository productRepository;

    public ProductMVCController(ProductService service,
                                ProductRepository productRepository) {
        this.service = service;
        this.productRepository = productRepository;
    }
    @GetMapping("/index")
    public String greeting(
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("products", service.getAll());
        model.addAttribute("sort", sort);
        model.addAttribute("description", description);
        return "products";
    }

    @GetMapping("/admin")
    public String admin(
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("products", service.getAll());
        model.addAttribute("sort", sort);
        model.addAttribute("description", description);
        return "products-admin";
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
        service.deleteById(id);
        return "redirect:/products/index";
    }


    @PostMapping("/create-or-update")
    public String saveCreateForm(@RequestParam Optional<Integer> id, @ModelAttribute Product product , Model model) {

        service.create(productRepository.save(product));


        return "redirect:/products/index";
    }



}