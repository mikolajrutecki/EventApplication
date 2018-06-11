package com.example.SpringPSS.components;

import com.example.SpringPSS.dtos.UserDto;
import com.example.SpringPSS.entities.Privilege;
import com.example.SpringPSS.entities.Role;
import com.example.SpringPSS.repositories.PrivilegeRepository;
import com.example.SpringPSS.repositories.RoleRepository;
import com.example.SpringPSS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        final Privilege userPrivilege = createPrivilegeIfNotFound("USER_PRIVILEGE");
        final Privilege adminPrivilege = createPrivilegeIfNotFound("ADMIN_PRIVILEGE");

        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(userPrivilege, adminPrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Collections.singletonList(userPrivilege));

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        try {
            userService.registerNewUserAccount(new UserDto("user", "user", "user", "user", "user", Collections.singletonList(roleRepository.findByName("ROLE_USER")), true));
            userService.registerNewUserAccount(new UserDto("admin", "admin", "admin", "admin", "admin", Arrays.asList(roleRepository.findByName("ROLE_USER"), roleRepository.findByName("ROLE_ADMIN")),
                    true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

}
