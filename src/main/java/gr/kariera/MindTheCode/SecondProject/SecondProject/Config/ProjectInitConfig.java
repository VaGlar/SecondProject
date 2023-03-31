package gr.kariera.MindTheCode.SecondProject.SecondProject.Config;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Category;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.CategoryRepository;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class ProjectInitConfig {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProjectInitConfig(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Bean
    public  void createInitialProduct(){
        Category save1 = categoryRepository.save(new Category("food"));
        Category save2 = categoryRepository.save(new Category("furniture"));


        List<Product> productList = List.of(new Product("chocolate", BigDecimal.valueOf(10),4,"chocolate.png",Set.of(new Category("food"))),
                new Product("biscuit", BigDecimal.valueOf(8),12,"biscuit.png",Set.of(save1)),
                new Product("pasta", BigDecimal.valueOf(2),50,"pasta.jpg",Set.of(save1)),
                new Product("table", BigDecimal.valueOf(100),8,"table.jpg",Set.of(save2)),
                new Product("chair", BigDecimal.valueOf(55),20,"chair.jpg",Set.of(save2)));
        productRepository.saveAll(productList);
    }

}
