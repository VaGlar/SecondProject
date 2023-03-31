package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.CreateOrderWrapper;
import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.OrderUpdateDto;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Order;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.OrderProduct;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.OrderRepository;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
        private final OrderRepository orderRepository;
        private final ProductRepository productRepository;

        public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
            this.orderRepository = orderRepository;
            this.productRepository = productRepository;
        }

        @Override
        public Order create(CreateOrderWrapper createOrderWrapper) {
            Order order = new Order();
            BigDecimal tp =new BigDecimal(0);
            if(createOrderWrapper.orderProductSet!=null){


            for(OrderProduct dto : createOrderWrapper.orderProductSet) {
                Product product = productRepository.findById(dto.getProductId()).orElseThrow();
                tp = tp.add(product.getPrice().multiply(dto.getQuantity()));
            }
            order.setTotalPrice(tp);


            order.setOrderProduct(createOrderWrapper.orderProductSet);}
            order.setAddress(createOrderWrapper.getAddress());
            return orderRepository.save(order);
        }
        @Override
        public Order getById(Integer id) {
            return orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order with id: " + id + " not found"));
        }

        @Override
        public List<Order> getAll() {
            return orderRepository.findAll();
        }

        @Override
        public void deleteById(Integer id) {
            try {
                orderRepository.deleteById(id);
            } catch (Exception e) {
                System.out.println("Could not delete order with id: " + id);
            }
        }

        @Override
        public void update(Integer id, Order orderUpdateDto) {
            Order order = orderRepository.findById(id).orElseThrow();
            order.setAddress(orderUpdateDto.getAddress());
            orderRepository.save(order);
        }

    @Override
    public BigDecimal calculateTotalPrice(Order a) {
            BigDecimal tp = BigDecimal.valueOf(0);
            for( OrderProduct o: a.getOrderProduct()){
                tp=tp.add(productRepository.findById(o.getProductId()).orElseThrow(()-> new  RuntimeException()).getPrice().multiply(o.getQuantity())) ;

            }
            return tp;

    }
    @Override
    public List<Product> findOrdersProduct(Order order){
            List<Product> products = new ArrayList<>();
            order.getOrderProduct().forEach(s -> {
                Optional<Product> productOptional =productRepository.findById(s.getProductId());
                productOptional.ifPresent(products::add);
            });
            return  products;
    }
}

