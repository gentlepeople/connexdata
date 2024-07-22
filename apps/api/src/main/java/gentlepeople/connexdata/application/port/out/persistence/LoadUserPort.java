package gentlepeople.connexdata.application.port.out.persistence;

import gentlepeople.connexdata.domain.entity.User;

import java.math.BigInteger;
import java.util.Optional;

public interface LoadUserPort {
  Optional<User> loadUser(BigInteger userId);
}
