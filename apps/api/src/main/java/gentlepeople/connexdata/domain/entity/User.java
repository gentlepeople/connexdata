package gentlepeople.connexdata.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

  private BigInteger id;

  private String username;

  private String email;

  private String notionAccessToken;

  private String notionUserId;

  public static User create(BigInteger id, String username, String email, String notionAccessToken, String notionUserId) {
    return new User(id, username, email, notionAccessToken, notionUserId);
  }
}
