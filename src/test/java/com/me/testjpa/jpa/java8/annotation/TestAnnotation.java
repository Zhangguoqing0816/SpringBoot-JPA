package com.me.testjpa.jpa.java8.annotation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * 重复注解
 * 和
 * 类型注解  TypeParameter
 */
public class TestAnnotation {
    @MyAnnotation("A")
    @MyAnnotation("B")
    public void test1() {

    }

    @Test
    public void test2() throws Exception {
        Class<TestAnnotation> testAnnotationClass = TestAnnotation.class;
        Method test1 = testAnnotationClass.getMethod("test1");
        MyAnnotation[] annotationsByType = test1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println(myAnnotation.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void test(@MyAnnotation("abc")String str) {

    }
}
