package park_su_park.backend.dto.responseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseBody {

    private final String message;
    private final ApiResponseData data;

    public static ApiResponseBody success(String message, ApiResponseData data) {
        return new ApiResponseBody(message, data);
    }

    public static ApiResponseBody failure(String message) {
        return new ApiResponseBody(message, null);
    }
}
