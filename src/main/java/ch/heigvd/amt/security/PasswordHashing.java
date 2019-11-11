package ch.heigvd.amt.security;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public class PasswordHashing {

    /**
     * Method to hash a password
     *
     * @param plainTextPassword Password that we need to hash
     * @return  Hashed password in String format
     */
    public static String hashPassword(String plainTextPassword) {
        return Hashing.sha256()
                .hashString(plainTextPassword, StandardCharsets.UTF_8)
                .toString();
    }
}
