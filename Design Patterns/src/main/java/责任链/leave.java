package 责任链;


import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class leave {

    //天数
    private int leaveDays;
    //姓名
    private String name;
}
