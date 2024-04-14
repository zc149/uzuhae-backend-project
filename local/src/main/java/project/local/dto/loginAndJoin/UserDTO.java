package project.local.dto.loginAndJoin;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String password;
    private String nickName;
    private String name;
    private Date joinDate;
    private String role;

}
