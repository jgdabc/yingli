package jgdabc.yingli.controller;

import jgdabc.yingli.JwtUtils;
import jgdabc.yingli.entity.User;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.service.FinanceAccountService;
import jgdabc.yingli.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FinanceController {
    @Autowired
    private FinanceAccountService financeAccountService;

    @GetMapping("/finance/user")
    public R loadFinanceUser(@RequestHeader("jwtAuth") String jwtAuth) {
        log.info("jwtAuth" + jwtAuth);
        String userJson = JwtUtils.parseJwt(jwtAuth);
        User user = JsonUtils.toObject(userJson, User.class);
        Integer userId = user.getId();

        return financeAccountService.getUserAvailableMoney(userId);

    }
}
