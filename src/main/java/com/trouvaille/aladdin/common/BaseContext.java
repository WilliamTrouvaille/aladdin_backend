/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.common
 * @className: BaseContext
 * @author: Eric
 * @description: TODO
 * @date: 2022/7/26 15:09
 * @version: 1.0
 */
package com.trouvaille.aladdin.common;

public class BaseContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static Long getCurrentId() {
        return threadLocal.get();
    }


    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }
}