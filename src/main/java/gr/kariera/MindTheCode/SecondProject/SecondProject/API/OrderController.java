package gr.kariera.MindTheCode.SecondProject.SecondProject.API;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.CreateOrderWrapper;
import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.OrderUpdateDto;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Order;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins="*")
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
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderWrapper createOrderWrapper) {

        return new ResponseEntity<>(orderService.create(createOrderWrapper), HttpStatus.CREATED);
    }
    @PostMapping("/{id}")
    public void updateOrder(@PathVariable Integer id, @RequestBody Order orderUpdateDto){
        orderService.update(id, orderUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Integer id){
        orderService.deleteById(id);}
}