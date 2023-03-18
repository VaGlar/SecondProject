package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.OrderUpdateDto;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Order;


import java.util.List;

public interface OrderService {
    
    Order create (Order order);

    Order getById(Integer id);

    List<Order> getAll();

    void deleteById(Integer id);

    void update(Integer id, OrderUpdateDto orderUpdateDto);
}

