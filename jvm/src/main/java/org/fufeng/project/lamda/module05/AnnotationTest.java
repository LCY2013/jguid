package org.fufeng.project.lamda.module05;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @ClassName: AnnotationTest
 * @author: LuoChunYun
 * @Date: 2019/4/29 14:50
 * @Description: TODO
 */
public class AnnotationTest {

    public static void main(String[] args) {
        final Method[] methods = AnnotationTest.class.getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> "say".equals(method.getName()))
                .forEach(method -> {
                    final Parameter[] parameters = method.getParameters();
                    Arrays.stream(parameters)
                            .forEach(parameter -> {
                                //这里需要设置编译器的参数-parameters 地址：https://docs.oracle.com/javase/tutorial/reflect/member/methodparameterreflection.html
                                System.out.println("参数名称:"+parameter.getName());
                                System.out.println("参数类型:"+parameter.getType());
                                Arrays.stream(parameter.getAnnotations())
                                    .forEach(annotation -> {
                                        System.out.println("注解类型:"+annotation.annotationType());
                                        if(isSameClass(annotation.annotationType(),ParamValue.class)) {
                                            ParamValue paramValue = (ParamValue) annotation;
                                            System.out.println("注解的值:" +paramValue.value());
                                        }
                                    });
                            });
                    //final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                });
    }

    private void say(@ParamValue("nameLuo")String name){

    }

    private static boolean isSameClass(Class<?> one,Class<?> two){
        System.out.println(one.getName().equals(two.getName()));
        System.out.println(one.getClassLoader().getClass().getName().equals(two.getClassLoader().getClass().getName()));
        if(one.getName().equals(two.getName())
                && one.getClassLoader().getClass().getName().equals(two.getClassLoader().getClass().getName())){
            return true;
        }
        return false;
    }
}
