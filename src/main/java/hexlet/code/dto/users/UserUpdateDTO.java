package hexlet.code.dto.users;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserUpdateDTO {
    @NotNull private JsonNullable<String> email;
    @NotNull private JsonNullable<String> password;
    @NotNull private JsonNullable<String> firstName;
    @NotNull private JsonNullable<String> lastName;
}
