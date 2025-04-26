package net.jyou.javase.enums;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class ReflectUtil {
    public static String getLambdaFunctionName(IFunction<?,?> lambda) {
        try {
            Method replaceMethod = lambda.getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda)replaceMethod.invoke(lambda);
            return serializedLambda.getImplMethodName().replace("get", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
