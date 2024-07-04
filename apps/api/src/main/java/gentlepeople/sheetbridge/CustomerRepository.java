package gentlepeople.sheetbridge;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Customer customer) {
        em.persist(customer);
        return customer.getId();
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }
}
