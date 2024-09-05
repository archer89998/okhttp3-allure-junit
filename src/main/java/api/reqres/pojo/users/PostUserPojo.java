package api.reqres.pojo.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class PostUserPojo {
    public String name;
    public String job;
    public String id;
    public Date createdAt;
}
