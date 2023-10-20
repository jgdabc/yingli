package jgdabc.yingli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jgdabc.yingli.entity.ProductInfo;
import jgdabc.yingli.mapper.ProductInfoMapper;
import jgdabc.yingli.mode.ConstantEnum;
import jgdabc.yingli.mode.AppProducts;
import jgdabc.yingli.mode.dto.InvestRankDto;
import jgdabc.yingli.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public AppProducts getAppProductList() {
//        查询新手宝类型的产品
        List<ProductInfo> xinList = productInfoMapper.selectByProductType(ConstantEnum.PRODUCT_TYPE_XIN_BAO, 0, 1);
        List<ProductInfo> youList = productInfoMapper.selectByProductType(ConstantEnum.PRUDUCT_TYPE_YOU_XUAN, 0, 3);
        List<ProductInfo> sanList = productInfoMapper.selectByProductType(ConstantEnum.PRODUCT_TYPE_SAN_BIAO ,0, 3);



        return new AppProducts(xinList,youList,sanList);
    }

    @Override
    public Page<ProductInfo> queryByType(Integer type, Integer pageNo, Integer pageSize) {

        QueryWrapper<ProductInfo> productInfoQueryWrapper = new QueryWrapper<>();
        productInfoQueryWrapper.eq("product_type",type).orderByDesc("release_time","rate","id");
        Page<ProductInfo> productInfoPage = productInfoMapper.selectPage(Page.of(pageNo, pageSize), productInfoQueryWrapper);


        return  productInfoPage;
    }

    @Override
    public List<InvestRankDto> loadInvestRecord(Integer pid) {
        return  productInfoMapper.loadInvestRecord(pid);
    }
}
