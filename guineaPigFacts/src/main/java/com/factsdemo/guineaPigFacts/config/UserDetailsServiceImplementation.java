package com.factsdemo.guineaPigFacts.config;

import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.repositories.UserRepository;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            BasicPasswordEncryptor passwordEncoder = new BasicPasswordEncryptor();
            User user = userRepository.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                    .password(passwordEncoder.encryptPassword(user.getPassword()))
                    .authorities(user.getRoles().get(0))
                    .build();
        }
}
