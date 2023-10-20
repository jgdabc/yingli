package jgdabc.yingli.convert;

import jgdabc.yingli.entity.ProductInfo;
import jgdabc.yingli.mode.vo.ProductInfoVo;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * 转换理财产品的对象
 */


@Mapper(componentModel = "spring")
public interface ConvertProduct {
    List<ProductInfoVo> convertProductVoList(List<ProductInfo> infos);

//    ProductInfo   ---ProductInfoVo
    @Mappings({
            @Mapping(target = "name",source = "productName"),
            @Mapping(target = "type",source = "productType"),
            @Mapping(target = "money",source = "productMoney"),
            @Mapping(target = "leftMoney",source = "leftProductMoney"),
            @Mapping(target = "minLimit",source = "bidMinLimit"),
            @Mapping(target = "maxLimit",source = "bidMaxLimit"),
    })
    ProductInfoVo convertProductInfoVo(ProductInfo productInfo);
}
