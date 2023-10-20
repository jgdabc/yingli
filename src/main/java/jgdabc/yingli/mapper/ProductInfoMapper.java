package jgdabc.yingli.mapper;

import jgdabc.yingli.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jgdabc.yingli.mode.dto.InvestRankDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author administrator-pc
* @description 针对表【b_product_info(产品信息表)】的数据库操作Mapper
* @createDate 2023-10-10 18:50:18
* @Entity jgdabc.yingli.entity.ProductInfo
*/
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

//    按照产品类型分页查询
    List<ProductInfo> selectByProductType(
            @Param("type") Integer type,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);


    List<InvestRankDto> loadInvestRecord(Integer pid);
}




