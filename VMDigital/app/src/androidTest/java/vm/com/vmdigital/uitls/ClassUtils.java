package vm.com.vmdigital.uitls;

import java.lang.reflect.Field;
import java.security.AccessControlException;

/**
 * Created by Pub on 12/07/2017.
 */

public class ClassUtils {

    public static Object getPrivateVariable(String variableName, Object targetObject){
        try {
            Field field = targetObject.getClass().getDeclaredField(variableName);
            field.setAccessible(true);
            return field.get(targetObject);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }
}
