package gentlepeople.sheetbridge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

  @Autowired
  CustomerRepository customerRepository;

  @Test
  @Transactional
  public void testCustomer() throws Exception {
    // given
    Customer customer = new Customer();
    customer.setName("customer1");

    // when
    Long savedId = customerRepository.save(customer);
    Customer findCustomer = customerRepository.findById(savedId);

    // then
    Assertions.assertThat(findCustomer.getId()).isEqualTo(customer.getId());
    Assertions.assertThat(findCustomer.getName()).isEqualTo(customer.getName());
  }

}
