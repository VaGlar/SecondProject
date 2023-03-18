package gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories;


import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
}
