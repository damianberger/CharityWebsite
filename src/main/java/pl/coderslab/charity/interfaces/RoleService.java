package pl.coderslab.charity.interfaces;

import pl.coderslab.charity.entity.Role;
import java.util.List;

public interface RoleService {
    void save(Role role);
    Role findOneByName(String roleName);
    List<Role> findAll();
}
