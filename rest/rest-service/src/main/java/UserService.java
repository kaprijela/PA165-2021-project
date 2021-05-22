import entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> find(Long id);

    Optional<User> findByUsername(String username);
}
