package onlineshop.service.impl;

import onlineshop.model.entity.Role;
import onlineshop.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RoleServiceImplTest {

    RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    RoleServiceImpl roleService = new RoleServiceImpl(roleRepository);

    @Test
    void repoCount() {
        // setting expectations
        when(roleRepository.count()).thenReturn(2L);

        // when 
        Long count = roleService.repoCount();

        // then
        assertEquals(2L, count);
        verify(roleRepository, times(1)).count();
    }
    @Test
    void findRoleByName_whenRoleExists() {
        // given
        Role role = new Role();
        role.setName("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);

        // when
        Role foundRole = roleService.findRoleByName("ROLE_USER");

        // then
        assertEquals("ROLE_USER", foundRole.getName());
        verify(roleRepository, times(1)).findByName("ROLE_USER");
    }

    @Test
    void findRoleByName_whenRoleDoesNotExist() {
        // given
        when(roleRepository.findByName("ROLE_UNKNOWN")).thenReturn(null);

        // when
        Role foundRole = roleService.findRoleByName("ROLE_UNKNOWN");

        // then
        assertNull(foundRole);
        verify(roleRepository, times(1)).findByName("ROLE_UNKNOWN");
    }
}
