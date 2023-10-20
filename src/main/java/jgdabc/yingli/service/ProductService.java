package jgdabc.yingli.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jgdabc.yingli.entity.ProductInfo;
import jgdabc.yingli.mode.AppProducts;
import jgdabc.yingli.mode.dto.InvestRankDto;

import java.util.List;

/**
 * 理财产品Service
 */
public interface ProductService {
   AppProducts getAppProductList();
   Page<ProductInfo> queryByType(Integer type, Integer pageNo, Integer pageSize);

   List<InvestRankDto> loadInvestRecord(Integer pid);
}
