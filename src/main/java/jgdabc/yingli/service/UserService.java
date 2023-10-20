package jgdabc.yingli.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jgdabc.yingli.entity.User;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.mode.dto.UserRankDto;
import jgdabc.yingli.mode.query.Realname;
import jgdabc.yingli.mode.query.UserQuery;

import java.util.List;

public interface UserService {

    List<UserRankDto>loadUserRank();
    List<User>selectQueryUsers();


    R saveUsers(UserQuery userQuery);

    R userLogin(UserQuery userQuery);



    R remealMe(Realname realname);

    User loadUser(QueryWrapper<User> userQueryWrapper);
}
