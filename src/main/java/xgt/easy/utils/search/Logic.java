/**
 * 
 */
package xgt.easy.utils.search;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Gavin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Logic {
	LogicType type() default LogicType.EQ;
	String field() default "";
}
