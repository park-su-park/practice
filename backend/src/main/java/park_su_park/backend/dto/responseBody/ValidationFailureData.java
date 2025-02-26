package park_su_park.backend.dto.responseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationFailureData implements ApiResponseData {

    private final Map<String, String> fieldErrors;

    public static ValidationFailureData create(Map<String, String> errors) {
        return new ValidationFailureData(errors);
    }
}
