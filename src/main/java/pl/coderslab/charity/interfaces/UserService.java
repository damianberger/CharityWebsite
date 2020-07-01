package pl.coderslab.charity.interfaces;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import pl.coderslab.charity.Model.CurrentUser;
import pl.coderslab.charity.entity.User;

public interface UserService {
    User findByEmail(String email);

    User findCurrentUser(@AuthenticationPrincipal CurrentUser currentUser);

    void saveUser(User user);

    void saveAdmin(User user);

    void editUser(@AuthenticationPrincipal CurrentUser currentUser, User user);

    void changePassword(@AuthenticationPrincipal CurrentUser currentUser, User user);
}
