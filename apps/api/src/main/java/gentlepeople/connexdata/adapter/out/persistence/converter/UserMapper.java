package gentlepeople.connexdata.adapter.out.persistence.converter;

import gentlepeople.connexdata.adapter.out.persistence.entity.UserJpaEntity;
import gentlepeople.connexdata.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserJpaEntity toJpaEntity(User user) {
    return new UserJpaEntity(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getNotionAccessToken(),
      user.getNotionUserId(),
      null, // createdAt - JPA auto
      null  // updatedAt - JPA auto
    );
  }

  public User toDomainEntity(UserJpaEntity jpaEntity) {
    return User.create(
      jpaEntity.getId(),
      jpaEntity.getUsername(),
      jpaEntity.getEmail(),
      jpaEntity.getNotionAccessToken(),
      jpaEntity.getNotionUserId()
    );
  }
}

