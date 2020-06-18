package pl.coderslab.charity.interfaces;

import pl.coderslab.charity.entity.User;

public interface UserService {
    User findByEmail(String email);

    void saveUser(User user);

    void saveAdmin(User user);
}
