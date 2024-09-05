package api.reqres.pojo.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPojo {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
