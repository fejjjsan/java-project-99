package hexlet.code.dto.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;


import java.util.Date;


@EqualsAndHashCode
@Getter
@Setter
@Builder
public class UserDTO {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date createdAt;
}
