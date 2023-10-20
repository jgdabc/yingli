package jgdabc.yingli.controller;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.extra.tokenizer.Result;
import jgdabc.yingli.mode.ConstantEnum;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.service.SmsService;
import jgdabc.yingli.util.RedisCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//发送短信的控制器类
@RestController
public class SmsController {
    @Autowired

    private SmsService smsService;




    @GetMapping("/sms/{type}")

    public R sendCode(@PathVariable("type") Integer type,  @RequestParam("phone") String phone   ){

        // TODO: 2023/10/15  可以优化一下，就是限制发送短信的次数
        R r  = R.error(ConstantEnum.PHONE_FOEMAT_ERR.getMsg());
        if (PhoneUtil.isPhone(phone)){
           R result = smsService.sendSms(phone, type);

            r  =  result;

        }
        return  r;

    }
}
