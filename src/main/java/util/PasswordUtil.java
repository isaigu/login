package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final int SALT_LENGTH = 16;

    public static String hash(String plainPassword) {
        try {
            byte[] salt = new byte[SALT_LENGTH];
            new SecureRandom().nextBytes(salt);

            byte[] hash = digest(plainPassword, salt);

            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(String plainPassword, String stored) {
        if (stored == null || !stored.contains(":")) {
            return false;
        }
        try {
            String[] partes = stored.split(":", 2);
            byte[] salt = Base64.getDecoder().decode(partes[0]);
            byte[] hashGuardado = Base64.getDecoder().decode(partes[1]);

            byte[] hashCalculado = digest(plainPassword, salt);

            return MessageDigest.isEqual(hashGuardado, hashCalculado);
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            return false;
        }
    }

    private static byte[] digest(String plainPassword, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        return md.digest(plainPassword.getBytes());
    }
}
