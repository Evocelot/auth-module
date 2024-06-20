package hu.evocelot.auth.service.auth.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Helper class for handling passwords.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PasswordHelper {

    /**
     * For encrypting password with SHA-512 and salt.
     *
     * @param originalPassword
     *         - the password to hash.
     * @param salt
     *         - the salt.
     * @return - with the encrypted password.
     * @throws BusinessException
     *         - when an error occurs.
     */
    public String encryptPassword(String originalPassword, String salt) throws BusinessException {
        String saltedPassword = originalPassword + salt;
        String encryptedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            encryptedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(FaultType.REST_INTERNAL_SERVER_ERROR,
                    MessageFormat.format("Cannot hash the password. Message: [{0}]", e.getMessage()));
        }

        return encryptedPassword;
    }
}
