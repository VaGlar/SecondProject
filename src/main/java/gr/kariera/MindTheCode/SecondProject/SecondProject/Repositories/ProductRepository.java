package gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
