package park_su_park.backend.web.security.encoder;

public interface PasswordEncoder {
    String encode(String rawPassword);

    boolean match(String rawPassword, String encodedPassword);
}
