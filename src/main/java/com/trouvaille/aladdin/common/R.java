/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.common
 * @className: R
 * @author: William_Trouvaille
 * @description: 服务端返回结果类
 * @date: 2022/7/26 15:06
 * @version: 1.0
 */
package com.trouvaille.aladdin.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> implements Serializable {
    
    //    编码：1成功，0和其它数字为失败
    private Integer code;
    
    //    错误信息
    private String msg;
    
    //    数据
    private T data;
    
    //    动态数据
    private Map map = new HashMap();
    
    public static <T> R<T> success (T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }
    
    public static <T> R<T> error (String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }
    
    public static R<String> flag (boolean flag , String msg_suc , String msg_err) {
        if (flag) {
            return R.success(msg_suc);
        } else {
            return R.error(msg_err);
        }
    }
    
    public static R<String> flag (boolean flag) {
        return R.flag(flag , "操作成功!" , "操作失败,请重试!");
    }
    
    public R<Object> flag (boolean flag , T object , String msg) {
        if (flag) {
            return R.success(object);
        } else {
            return R.error(msg);
        }
    }
    
    public R<T> add (String key , Object value) {
        this.map.put(key , value);
        return this;
    }
    
}
