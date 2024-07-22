package gentlepeople.connexdata.application.service;

import gentlepeople.connexdata.application.port.in.GetUserQuery;
import gentlepeople.connexdata.application.port.out.persistence.LoadUserPort;
import gentlepeople.connexdata.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GetUserService implements GetUserQuery {

  private final LoadUserPort loadUserPort;

  @Override
  public Optional<User> getUser(BigInteger userId) {
    return loadUserPort.loadUser(userId);
  }
}
