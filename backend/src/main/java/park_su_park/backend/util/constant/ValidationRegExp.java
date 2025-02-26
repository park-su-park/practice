package park_su_park.backend.util.constant;

public interface ValidationRegExp {
    String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9_])[A-Za-z\\d[^A-Za-z0-9_]]+$";
    String USERNAME_REGEXP = "^[^\\s]+$";
}
