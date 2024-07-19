package gentlepeople.connexdata.adapter.in.web;

import gentlepeople.connexdata.application.port.in.GetUserQuery;
import gentlepeople.connexdata.common.WebAdapter;
import gentlepeople.connexdata.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@WebAdapter
@RestController
@RequiredArgsConstructor
class GetUserController {

  private final GetUserQuery getUserQuery;

  @GetMapping(path = "/users/{userId}")
  public ResponseEntity<User> getUser(@PathVariable("userId") BigInteger userId) {
    return getUserQuery.getUser(userId)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
