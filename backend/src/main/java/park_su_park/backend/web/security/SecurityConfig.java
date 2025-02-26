package park_su_park.backend.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import park_su_park.backend.web.security.encoder.BCryptPasswordEncoder;
import park_su_park.backend.web.security.encoder.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
