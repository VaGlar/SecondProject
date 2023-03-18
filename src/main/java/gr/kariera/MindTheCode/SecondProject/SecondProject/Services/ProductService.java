package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import org.springframework.data.domain.Page;

public interface  ProductService {
    public abstract Product createOrUpdateProduct(Integer id, Product product) throws Exception;
    public abstract void deleteProduct(Integer id);
    public abstract Page<Product> getProduct(
            String description,
            int page,
            int size,
            String sort
    );

    public abstract Product getById(Integer id);
}
