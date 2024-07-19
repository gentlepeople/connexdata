package gentlepeople.connexdata.adapter.out.persistence.repository;

import gentlepeople.connexdata.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, BigInteger> {
}
