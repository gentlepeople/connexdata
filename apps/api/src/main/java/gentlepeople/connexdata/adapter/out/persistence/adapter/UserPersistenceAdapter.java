package gentlepeople.connexdata.adapter.out.persistence.adapter;

import gentlepeople.connexdata.adapter.out.persistence.converter.UserMapper;
import gentlepeople.connexdata.adapter.out.persistence.repository.SpringDataUserRepository;
import gentlepeople.connexdata.application.port.out.LoadUserPort;
import gentlepeople.connexdata.common.PersistenceAdapter;
import gentlepeople.connexdata.domain.entity.User;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements LoadUserPort {

    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> loadUser(BigInteger id) {
        return userRepository.findById(id).map(userMapper::toDomainEntity);
    }
}
