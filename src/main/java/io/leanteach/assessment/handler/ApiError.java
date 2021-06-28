package io.leanteach.assessment.handler;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
class ApiError {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private HttpStatus status;
    private String timestamp;
    private String message;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now().format(formatter);
    }

    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    abstract class ApiSubError {
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    class ApiValidationError extends ApiSubError {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;

        ApiValidationError(String object, String message) {
            this.object = object;
            this.message = message;
        }
    }
}