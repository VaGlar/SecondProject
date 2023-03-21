package gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.OrderProduct;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;

import java.util.List;
import java.util.Set;

public class CreateOrderWrapper {

        public String address;

        public Set<OrderProduct> orderProductSet;



    public CreateOrderWrapper(String address, List<Product> products) {
        this.address = address;
    }



    public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Set<OrderProduct> getOrderProductDtoSet() {
            return orderProductSet;
        }

        public void setOrderProductDtoSet(Set<OrderProduct> orderProductSet) {
            this.orderProductSet = orderProductSet;
        }
}
