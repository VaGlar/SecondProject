package gr.kariera.MindTheCode.SecondProject.SecondProject.Entities;

import gr.kariera.MindTheCode.SecondProject.SecondProject.MVC.OrderMVCController;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name= "orders")
public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        private String address;

        private BigDecimal totalPrice;



        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "orders_id", referencedColumnName = "id")
        Set<OrderProduct> orderProduct= new HashSet<>();

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public Set<OrderProduct> getOrderProduct() {
                return orderProduct;
        }

        public void setOrderProduct(Set<OrderProduct> orderProduct) {
                this.orderProduct = orderProduct;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public BigDecimal getTotalPrice() {
                return totalPrice;
        }


        public void setTotalPrice(BigDecimal totalPrice) {
                this.totalPrice = totalPrice;
        }


        public String getProductIds(){
                StringBuilder a = new StringBuilder();
                for(OrderProduct ap:getOrderProduct()){
                  a.append(" ").append(ap.getProductId());
                }
                return a.toString();
        }
        public String getProductQuantity(){
                StringBuilder a = new StringBuilder();
                for(OrderProduct ap:getOrderProduct()){
                        a.append(" ").append(ap.getQuantity());
                }
                return a.toString();
        }










}