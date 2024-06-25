package com.awbd.bootstrap;

import com.awbd.entities.Authority;
import com.awbd.entities.Users;
import com.awbd.repositories.AuthorityRepository;
import com.awbd.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Profile("mysql")
public class DataLoader implements CommandLineRunner {

    private AuthorityRepository authorityRepository;
    private UsersRepository userRepository;
    private PasswordEncoder passwordEncoder;


    private void loadUserData() {
        if (userRepository.count() == 0){
            Authority adminRole = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
            Authority guestRole = authorityRepository.save(Authority.builder().role("ROLE_GUEST").build());

            Users admin = Users.builder()
                    .id(1L)
                    .username("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .authority(adminRole)
                    .build();

            Users guest1 = Users.builder()
                    .id(2L)
                    .username("guest1")
                    .email("guest1@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .authority(guestRole)
                    .build();

            Users guest2 = Users.builder()
                    .id(3L)
                    .username("guest2")
                    .email("guest2@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .authority(guestRole)
                    .build();

            userRepository.save(admin);
            userRepository.save(guest1);
            userRepository.save(guest2);
        }
    }


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
}