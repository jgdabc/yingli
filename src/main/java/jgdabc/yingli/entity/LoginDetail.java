package jgdabc.yingli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName b_login_detail
 */
@TableName(value ="b_login_detail")
@Data
public class LoginDetail implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 登录ip
     */
    @TableField(value = "login_ip")
    private String loginIp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}