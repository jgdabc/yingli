package jgdabc.yingli.mode;

import jgdabc.yingli.entity.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppProducts {

    private List<ProductInfo> xinList;
    private List<ProductInfo> youList;
    private List<ProductInfo> sanList;
}
