package park_su_park.backend.logIn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncoderTest {

    @Test
    void encode() {
        PasswordEncoder passwordEncoder1 = new PasswordEncoder();
        PasswordEncoder passwordEncoder2 = new PasswordEncoder();
        String encoded = passwordEncoder1.encode("1234");
        assertTrue(passwordEncoder2.matches("1234", encoded));
    }

    @Test
    void matches() {
    }
}
