package com.me.testjpa.jpa.java8.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@Repeatable(MyAnnotations.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER}
)@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "guoqing";
}
