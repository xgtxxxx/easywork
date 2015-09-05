/**
 * 
 */
package xgt.easy.utils.check;

import java.lang.reflect.Field;

/**
 * @author Gavin
 *
 */
public class RequiredChecker {

	public static <T> void checkRequired(T t) {
		Class<?> clazz = t.getClass();
		do{
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Required required = field.getAnnotation(Required.class);
				if (required != null) {
					try {
						Object value = field.get(t);
						if(value==null){
							throw new NullPointerException("Check required field failed : "+field.getName()+" in "+clazz.getName()+" is null!");
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
			clazz = clazz.getSuperclass();
		}while(clazz!=null);
	}

}
