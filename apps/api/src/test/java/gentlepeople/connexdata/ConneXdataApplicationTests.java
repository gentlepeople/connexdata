package gentlepeople.connexdata;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
  "spring.flyway.enabled=false"
})
class ConneXdataApplicationTests {
  @Test
  void contextLoads() {
  }

}
