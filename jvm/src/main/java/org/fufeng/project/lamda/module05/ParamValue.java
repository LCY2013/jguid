package org.fufeng.project.lamda.module05;

import java.lang.annotation.*;

/**
 * @ClassName: ParamValue
 * @author: LuoChunYun
 * @Date: 2019/4/29 14:51
 * @Description: TODO
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD ,  ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ParamValue {

    String value() default "lcy";
}
