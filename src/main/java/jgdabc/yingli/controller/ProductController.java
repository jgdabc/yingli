package jgdabc.yingli.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jgdabc.yingli.convert.ConvertProduct;
import jgdabc.yingli.entity.ProductInfo;
import jgdabc.yingli.mode.PageInfoDto;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.mode.dto.InvestRankDto;
import jgdabc.yingli.mode.vo.ProductInfoVo;
import jgdabc.yingli.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ConvertProduct convertProduct;

    @GetMapping("/product/type")
    public R productByType(@RequestParam Integer type, @RequestParam Integer pageNo){
        System.out.println("pageno======"+pageNo);
        if (pageNo<1||pageNo>Integer.MAX_VALUE){
            pageNo  =  1;
        }

        Page<ProductInfo> productInfoPage = productService.queryByType(type, pageNo, 9);
//        当前页的数据
        List<ProductInfo> productLists = productInfoPage.getRecords();
//     查看总页数
        long pages = productInfoPage.getPages();
//        当前页
        long current = productInfoPage.getCurrent();
//        获取总记录数
        List<ProductInfoVo> productInfoVos = convertProduct.convertProductVoList(productLists);

        long total = productInfoPage.getTotal();
        PageInfoDto<ProductInfoVo> productInfoPageInfo = new PageInfoDto<>(pages, current, productInfoVos,total);
        return  R.success(productInfoPageInfo);



    }

    @GetMapping("/getInvestRecord/")
    public R loadInvestRecord(@RequestParam("pid") Integer pid ){

        List<InvestRankDto> investRankDtos = productService.loadInvestRecord(pid);
//        System.out.println(pid);
       return R.success( productService.loadInvestRecord(pid));


    }




}
