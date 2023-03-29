package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.ProductUpdateDto;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product getById(Integer id);

    List<Product> getAll();

    void deleteById(Integer id);

    Product findProductById(Integer id);

    void update(Integer id, Product product);
}
