package park_su_park.backend.web.security.encoder;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BCryptPasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    @Override
    public boolean match(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
