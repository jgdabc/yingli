package jgdabc.yingli.service;

import jgdabc.yingli.mode.R;

import java.math.BigDecimal;

public interface FinanceAccountService {


    int saveUserFinance(BigDecimal newUserAvailableMoney, Integer userId);


    R getUserAvailableMoney(Integer id);
}
