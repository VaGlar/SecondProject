package gr.kariera.MindTheCode.SecondProject.SecondProject.Entities;

import jakarta.persistence.*;


@Entity(name= "orders")
public class Order {
        @Id
        @GeneratedValue(strategy =  GenerationType.AUTO)
        private Integer id;

        private String address;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }
}