package com.lsw.management.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  12:21
 */
public class EnumUtils {

    public static <T extends Enum<T>> Object getNameByCode(Class<T> enumClass, String property1, String property2, Object value) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String methodName1=property1.substring(0,1).toUpperCase()+property1.substring(1);
        String methodName2=property2.substring(0,1).toUpperCase()+property2.substring(1);
        for (T enumConstant : enumClass.getEnumConstants()) {
            Method declaredMethod = enumConstant.getDeclaringClass().getDeclaredMethod(String.format("get%s", methodName1));
            Object invoke = declaredMethod.invoke(enumConstant);
            if(invoke.equals(value)){
                Method method = enumConstant.getDeclaringClass().getDeclaredMethod(String.format("get%s", methodName2));
                return method.invoke(enumConstant);
            }

        }
        return null;
    }
}
