package onlineshop.service;

import onlineshop.model.entity.Role;

public interface RoleService {
    Long repoCount();

    Role findRoleByName(String roleName);
}
