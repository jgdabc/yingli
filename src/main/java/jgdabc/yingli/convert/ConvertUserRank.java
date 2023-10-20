package jgdabc.yingli.convert;

import jgdabc.yingli.entity.ProductInfo;
import jgdabc.yingli.mode.dto.UserRankDto;
import jgdabc.yingli.mode.vo.ProductInfoVo;
import jgdabc.yingli.mode.vo.UserRankVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConvertUserRank {

    List<UserRankVo> convertRankVo(List<UserRankDto> userRankDto);
    @Mappings({
            @Mapping(target = "money",source = "sumBidMoney"),
    })
   UserRankVo convertUserRankVo(UserRankDto userRankDto);

}
