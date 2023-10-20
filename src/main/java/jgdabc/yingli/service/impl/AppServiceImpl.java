package jgdabc.yingli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jgdabc.yingli.entity.BidInfo;
import jgdabc.yingli.entity.ProductInfo;
import jgdabc.yingli.mapper.BidInfoMapper;
import jgdabc.yingli.mapper.ProductInfoMapper;
import jgdabc.yingli.mapper.UserMapper;
import jgdabc.yingli.mode.dto.AppBaseInfoDto;
import jgdabc.yingli.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 应用程序统计的实现类
 */
@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private BidInfoMapper bBidInfoMapper;
    @Override
    public AppBaseInfoDto getAppBaseInfo() {

//        获取注册人数
        Long userCount = userMapper.selectCount(null);

//        获取平均利率
        QueryWrapper<ProductInfo> bProductInfoQueryWrapper = new QueryWrapper<>();
        bProductInfoQueryWrapper.select("IFNULL(avg(rate),0) as rate");
        ProductInfo bProductInfo = productInfoMapper.selectOne(bProductInfoQueryWrapper);
        BigDecimal rate = bProductInfo.getRate();

//        获取投资金额的和
        BigDecimal bidMoney = bBidInfoMapper.selectOne(
                new QueryWrapper<BidInfo>().select("IFNULL(sum(bid_money),0) as bid_money")).getBidMoney();
        return  new AppBaseInfoDto(rate,bidMoney,userCount);
    }
}
