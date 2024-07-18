package gentlepeople.connexdata.application.port.in;

import gentlepeople.connexdata.domain.entity.User;

import java.math.BigInteger;
import java.util.Optional;

public interface GetUserQuery {
  Optional<User> getUser(BigInteger userId);
}
