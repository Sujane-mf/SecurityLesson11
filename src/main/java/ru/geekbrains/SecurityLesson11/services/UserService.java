package ru.geekbrains.SecurityLesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.SecurityLesson11.entities.Privilege;
import ru.geekbrains.SecurityLesson11.entities.User;
import ru.geekbrains.SecurityLesson11.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapPrivilegeToAuthorities(user.getPrivileges()));
    }


    private Collection<? extends GrantedAuthority> mapPrivilegeToAuthorities(Collection<Privilege> privileges) {
        return privileges.stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName())).collect(Collectors.toList());
    }
}
