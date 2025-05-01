package com.mentalhealth.security;


import com.mentalhealth.models.common.BaseUser;
import com.mentalhealth.repositories.doctor.DoctorRepository;
import com.mentalhealth.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowerCaseUsername = username.toLowerCase(Locale.ROOT);
        BaseUser user = userRepository.findByEmail(lowerCaseUsername)
                .map(BaseUser.class::cast)
                .or(() -> doctorRepository.findByEmail(lowerCaseUsername).map(BaseUser.class::cast))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + lowerCaseUsername));
        return new CustomUserDetails(user);
    }
}
