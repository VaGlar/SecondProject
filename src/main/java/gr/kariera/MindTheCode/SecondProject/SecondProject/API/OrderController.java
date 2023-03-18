package gr.kariera.MindTheCode.SecondProject.SecondProject.API;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.NewOrderDto;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Order;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>>
    getAllOrder() {return ResponseEntity.ok(
                orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order>
    getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(
            orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.create(order), HttpStatus.CREATED);
    }
    @PostMapping("/{id}")
    public void updateOrder(@PathVariable Integer id, @RequestBody NewOrderDto newOrderDto){
        orderService.update(id, newOrderDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Integer id){
        orderService.deleteById(id);}
}
