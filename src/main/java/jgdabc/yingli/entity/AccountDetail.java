package jgdabc.yingli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName b_account_detail
 */
@TableName(value ="b_account_detail")
@Data
public class AccountDetail implements Serializable {
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
     * 对账号的操作 ：add增加金额， sub:减少金额
     */
    @TableField(value = "action")
    private String action;

    /**
     * 时间
     */
    @TableField(value = "action_time")
    private Date actionTime;

    /**
     * 操作金额
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 备注
     */
    @TableField(value = "memo")
    private String memo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}