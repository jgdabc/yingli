package jgdabc.yingli.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoDto<T> {

    Long pages;
    Long  current;
    List<T> T;
    Long total;

}
