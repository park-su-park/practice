package park_su_park.backend.logIn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PasswordEncoderTest {

    @Test
    void encode() {
        PasswordEncoder passwordEncoder1 = new PasswordEncoder();
        PasswordEncoder passwordEncoder2 = new PasswordEncoder();
        String encoded = passwordEncoder1.encode("1234");
        org.assertj.core.api.Assertions.assertThat(passwordEncoder2.matches("1234", encoded))
            .isEqualTo(true);
    }

    @Test
    void matches() {
    }
}