package gr.kariera.MindTheCode.SecondProject.SecondProject.MVC;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.NewOrderDto;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Order;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.OrderRepository;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderMVCController {

    private final OrderService service;

    private final OrderRepository orderRepository;

    public OrderMVCController(OrderService service, OrderRepository orderRepository) {
        this.service = service;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/index")
    public String greeting(
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("orders", service.getAll());
        model.addAttribute("sort", sort);
        model.addAttribute("addresses", address);
        return "order-table";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("order",  new Order());
        return "orders";
    }

    @PostMapping("/create-or-update")
    public String saveCreateForm(@RequestParam Optional<Integer> id, @ModelAttribute Order order , Model model) {


        service.create(orderRepository.save(order));


        return "redirect:/orders/index";
    }


}
