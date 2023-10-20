package jgdabc.yingli.mapper;

import jgdabc.yingli.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jgdabc.yingli.mode.dto.UserRankDto;

import java.util.List;

/**
* @author administrator-pc
* @description 针对表【u_user(用户表)】的数据库操作Mapper
* @createDate 2023-10-10 18:50:18
* @Entity jgdabc.yingli.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<UserRankDto> loadUserRank();
}




