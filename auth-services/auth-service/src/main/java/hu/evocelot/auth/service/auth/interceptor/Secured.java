package hu.evocelot.auth.service.auth.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.interceptor.InterceptorBinding;

/**
 * The secured annotation for secure the rest method with login and optional permission check. For using the method with this annotation, the user
 * must be logged in and having a valid access token.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Secured {

}
