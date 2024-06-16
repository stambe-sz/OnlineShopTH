package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.enums.RoleEnum;
import onlineshop.model.entity.Role;
import onlineshop.repository.RoleRepository;
import onlineshop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Long repoCount() {
        return this.roleRepository.count();
    }

    @Override
    public Role findRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName);
    }
}
