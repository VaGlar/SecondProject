package gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Order;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersWithProductsDetails extends Order {

    private List<Product> productList;

    public OrdersWithProductsDetails(Order order,List<Product> ordersProduct) {
        super(order.getId(),order.getAddress(),order.getTotalPrice(),order.getOrderProduct());

        productList= ordersProduct;

    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getProductsNames(){
        return productList.stream().map(Product::getName)
                .collect(Collectors.joining(", "));
    }


}
