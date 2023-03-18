package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;


import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Product;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }
    @Override
    public Product createOrUpdateProduct(Integer id, Product product) throws Exception {
        if (id != null) {
            if (!id.equals(product.getId())) {
                throw new Exception("id in path does not patch id in body");
            }
        }
        return repo.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product match = repo.findById(id)
                .orElseThrow();
        repo.delete(match);
    }

    @Override
    public Product getById(Integer id) {
        return repo.findById(id)
                .orElseThrow();
    }

    @Override
    public Page<Product> getProduct(String description, int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("description").ascending() :
                        Sort.by("description").descending());

        Page<Product> res;
        if (description == null) {
            res = repo.findAll(paging);
        } else {
            res = repo.findByDescriptionContainingIgnoreCase(description, paging);
        }

        return res;
    }


}
