package gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.CartItem;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findAllByUserAndOrderIsNull(User user);

    void deleteAllByUserAndOrderIsNull(User user);

    int countDistinctByUserAndOrderIsNull(User user);
}
