package jgdabc.yingli.service;

import jgdabc.yingli.mode.dto.AppBaseInfoDto;
import jgdabc.yingli.mode.vo.AppBaseInfoVo;

public interface AppService {


    /**
     * 统计三项基本数据
     * @return AppBaseInfoDto
     */
    AppBaseInfoDto getAppBaseInfo();
}
