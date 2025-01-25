package com.example.demo.service;


import com.example.demo.model.Users;
import com.example.demo.model.UserPrincipal;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user = userRepo.findByUsername(username);
//        if (user == null) {
//            System.out.println("User Not Found");
//            throw new UsernameNotFoundException("user not found");
//        }
//
//        return new UserPrincipal(user);
//    }
//}


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Hard-coded admin user
        if ("admin".equals(username)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder(12).encode("admin123")) // Hard-coded password
                    .roles("ADMIN") // Assign admin role
                    .build();
        }

        // Hard-coded regular user
        if ("user".equals(username)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username("user")
                    .password(new BCryptPasswordEncoder(12).encode("user123")) // Hard-coded password
                    .roles("USER") // Assign user role
                    .build();
        }

        // Otherwise, check the database
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }
}
