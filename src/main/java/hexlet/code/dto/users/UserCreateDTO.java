package hexlet.code.dto.users;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateDTO {
    @Email private String email;
    @Size(min = 3) private String password;
    private String firstName;
    private String lastName;
}
