package hu.evocelot.auth.service.auth.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.interceptor.InterceptorBinding;

/**
 * The annotation is intended to protect those REST methods that we want to secure. It allows defining the required permission, and before the method
 * execution, it checks whether the user attempting to use the function has the necessary permissions.
 * <p>
 * For every method annotated with {@code PermissionNeeded}, the user must be logged in and have a valid access token.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface PermissionNeeded {

    /**
     * Defines the required permission for the annotated method.
     *
     * @return the required permission
     */
    Permission permission();
}
