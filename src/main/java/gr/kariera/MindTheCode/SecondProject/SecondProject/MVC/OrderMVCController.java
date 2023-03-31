package gr.kariera.MindTheCode.SecondProject.SecondProject.MVC;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.CreateOrderWrapper;
import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.OrdersWithProductsDetails;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Order;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.OrderProduct;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.OrderRepository;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.OrderService;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderMVCController {

    private final OrderService service;

    private final OrderRepository orderRepository;

    private final ProductService productService;

    public OrderMVCController(OrderService service, OrderRepository orderRepository, ProductService productService) {
        this.service = service;
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @GetMapping("/index")
    public String greeting(
            @RequestParam(required = false) String address,
//            @RequestParam(required = false) BigDecimal totalPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {

       List<OrdersWithProductsDetails> orders = service.getAll().stream().map(s -> new OrdersWithProductsDetails(s, service.findOrdersProduct(s))).collect(Collectors.toList());

        model.addAttribute("orders", orders);
//        model.addAttribute("orders",service.getAll());
        model.addAttribute("sort", sort);
        model.addAttribute("addresses", address);
//        model.addAttribute("totalPrice",service.calculateTotalPrice());

        return "order-table";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("order",  new Order());
        model.addAttribute("products",productService.getAll());
        return "orders";
    }

    @PostMapping("/create-or-update")
    public String saveCreateForm(@RequestParam Optional<Integer> id, @ModelAttribute CreateOrderWrapper order  , Model model) {
        orderRepository.save(service.create(order));

        return "redirect:/orders/index";
    }

    @PostMapping("/add-product")
    public String addProductToOrder(@RequestParam Integer orderId,@ModelAttribute OrderProduct orderProduct,Model model ){

        Order a= service.getById(orderId);
        Set<OrderProduct> products = a.getOrderProduct();
        for(OrderProduct op :products){
            if(op.getProductId().equals(orderProduct.getProductId())){
                op.setQuantity(orderProduct.getQuantity().add(op.getQuantity()));
                products.add(op);
                a.setOrderProduct(products);
                a.setTotalPrice(service.calculateTotalPrice(a));
                orderRepository.save(a);
                model.addAttribute("products",findProductsInOrder(a));


                return "redirect:/orders/index";
            }
        }
        products.add(orderProduct);
        a.setOrderProduct(products);
        a.setTotalPrice(service.calculateTotalPrice(a));
        orderRepository.save(a);
        model.addAttribute("products",findProductsInOrder(a));
        return "redirect:/orders/index";

    }

    private List<Product> findProductsInOrder (Order order){
        List<Integer> productIds = new ArrayList<>();
        for(OrderProduct op :order.getOrderProduct()){
            productIds.add(op.getProductId());
        }
        List<Product> products = new ArrayList<>();
        for(Integer prodid : productIds){
            products.add(productService.getById(prodid));
        }
        return products;
    }


}

