package jgdabc.yingli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jgdabc.yingli.entity.FinanceAccount;
import jgdabc.yingli.mapper.FinanceAccountMapper;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.service.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FinanceAccountServiceImpl implements FinanceAccountService {
    @Autowired
    private FinanceAccountMapper financeAccountMapper;
    @Override
    public int saveUserFinance(BigDecimal newUserAvailableMoney, Integer userId) {
        FinanceAccount financeAccount = new FinanceAccount();
        financeAccount.setUid(userId);
        financeAccount.setAvailableMoney(newUserAvailableMoney);


        return  financeAccountMapper.insert(financeAccount);
    }

    @Override
    public R getUserAvailableMoney(Integer id) {
        QueryWrapper<FinanceAccount> financeAccountQueryWrapper = new QueryWrapper<>();
        financeAccountQueryWrapper.eq("uid", id);
        FinanceAccount financeAccount = financeAccountMapper.selectOne(financeAccountQueryWrapper);
        BigDecimal availableMoney = financeAccount.getAvailableMoney();
        return  R.success(availableMoney);
    }
}
