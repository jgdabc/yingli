package jgdabc.yingli.controller;

import jgdabc.yingli.convert.ConvertProduct;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.mode.dto.AppBaseInfoDto;
import jgdabc.yingli.mode.AppProducts;
import jgdabc.yingli.mode.vo.AppBaseInfoVo;
import jgdabc.yingli.mode.vo.ProductInfoVo;
import jgdabc.yingli.service.AppService;
import jgdabc.yingli.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@CrossOrigin
@RestController
public class AppController {


    @Autowired
    private AppService appService;
    @Autowired
    private ProductService productService;
    @Autowired

    private  ConvertProduct convertProduct;



    /**
     * 统计三项数据
     */

    @GetMapping("/app/baseinfo")
    public R<AppBaseInfoVo> getBaseInfo(){
        AppBaseInfoDto appBaseInfo = appService.getAppBaseInfo();
        AppBaseInfoVo appBaseInfoVo = new AppBaseInfoVo();
        BeanUtils.copyProperties(appBaseInfo, appBaseInfoVo);
        return  R.success(appBaseInfoVo);


    }

    @GetMapping("/app/products")

    public R showRroducts(){
        AppProducts appProducts = productService.getAppProductList();
        List<ProductInfoVo> xinListVo = convertProduct.convertProductVoList(appProducts.getXinList());
        List<ProductInfoVo> youListVo = convertProduct.convertProductVoList(appProducts.getYouList());
        List<ProductInfoVo> sanListVo = convertProduct.convertProductVoList(appProducts.getSanList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("xinList",xinListVo);
        map.put("youList",youListVo);
        map.put("sanList",sanListVo);
        return R.success(map);

    }
}
