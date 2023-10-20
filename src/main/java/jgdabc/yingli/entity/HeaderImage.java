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
 * @TableName u_header_image
 */
@TableName(value ="u_header_image")
@Data
public class HeaderImage implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * fdfs组名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 文件路径名称
     */
    @TableField(value = "file_id")
    private String fileId;

    /**
     * 扩展名
     */
    @TableField(value = "ext_name")
    private String extName;

    /**
     * 头像宽度
     */
    @TableField(value = "img_width")
    private Integer imgWidth;

    /**
     * 头像高度
     */
    @TableField(value = "img_height")
    private Integer imgHeight;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}