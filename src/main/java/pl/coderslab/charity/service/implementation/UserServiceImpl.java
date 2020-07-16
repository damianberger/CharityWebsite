package pl.coderslab.charity.service.implementation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return userRepository.findByEmail(currentUser.getUsername());
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findOneByName("ROLE_USER"));
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findOneByName("ROLE_ADMIN"));
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    @Override
    public void editUser(@AuthenticationPrincipal CurrentUser currentUser, User user) {
        User modifyUserData = findCurrentUser(currentUser);
        modifyUserData.setFirstName(user.getFirstName());
        modifyUserData.setLastName(user.getLastName());
        modifyUserData.setEmail(user.getEmail());
        userRepository.save(modifyUserData);
    }

    @Override
    public void changePassword(@AuthenticationPrincipal CurrentUser currentUser, User user) {
        User modifyUserData = findCurrentUser(currentUser);
        modifyUserData.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(modifyUserData);
    }

}