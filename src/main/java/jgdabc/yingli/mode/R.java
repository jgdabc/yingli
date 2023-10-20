package jgdabc.yingli.mode;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Data
public class R<T>implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    //这样的泛型设计非常巧妙了
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;//这里可以接收到返回的值
        r.code = ConstantEnum.SUCCESS.code;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = ConstantEnum.FAILED.code;
        return r;
    }
    public static <T> R<T> error(Integer code) {
        R r = new R();
        r.code = code;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}