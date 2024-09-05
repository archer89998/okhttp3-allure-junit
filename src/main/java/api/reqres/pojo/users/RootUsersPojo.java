package api.reqres.pojo.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RootUsersPojo {
    private List<UserPojo> data;
}
