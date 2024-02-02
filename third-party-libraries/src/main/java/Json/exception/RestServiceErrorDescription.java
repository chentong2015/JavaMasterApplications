package json.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

// 标注在带有null的属性值的情况下也可以序列化
// To suppress serializing properties with null values using Jackson >2.0,
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestServiceErrorDescription implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String code;
    private final String message;
    private RestServiceErrorDescription[] innerErrors;

    @JsonIgnore
    private int statusCode;

    @JsonCreator
    public RestServiceErrorDescription(@JsonProperty("code") String code,
                                       @JsonProperty("message") String message) {
        this.code = Objects.requireNonNull(code, "error code must not be null");
        this.message = Objects.requireNonNull(message, "error message must not be null");
    }

    @JsonProperty("code")
    public String code() {
        return code;
    }

    @JsonProperty("message")
    public String message() {
        return message;
    }

    @JsonProperty("innerErrors")
    public RestServiceErrorDescription[] innerErrors() {
        if (innerErrors == null) {
            return null;
        }
        return innerErrors.clone();
    }

    public void setInnerErrors(RestServiceErrorDescription[] errors) {
        if (errors == null) {
            this.innerErrors = null;
        } else {
            this.innerErrors = errors.clone();
        }
    }
}
