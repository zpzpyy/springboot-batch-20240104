package a.b.c.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User {

    private String userId;
    private String userName;
    private String mobile;

    private LocalDateTime regDate;

}
