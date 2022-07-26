/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.common
 * @className: R
 * @author: Eric
 * @description: TODO
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

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public static R<String> flag(boolean flag) {
        if (flag) {
            return R.success("操作成功!");
        } else {
            return R.error("操作失败,请重试!");
        }
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
