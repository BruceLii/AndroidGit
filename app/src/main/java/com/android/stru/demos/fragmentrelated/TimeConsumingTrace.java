package com.android.stru.demos.fragmentrelated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeConsumingTrace {
    //第一个值
    int key();

    //第二个值
    String value();
}