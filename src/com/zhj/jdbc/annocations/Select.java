package com.zhj.jdbc.annocations;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2020/8/27.
 */
@Target(ElementType.METHOD) //可以用在方法上
@Retention(RetentionPolicy.RUNTIME) //在jvm中存在，可以通过反射获得注解信息
@Inherited  //注解可继承 我们在接口方法定义的注解，在实现类的方法上也可以获得。
public @interface Select {

    public String value() ;
}
