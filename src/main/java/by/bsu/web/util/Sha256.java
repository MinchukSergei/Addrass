package by.bsu.web.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Sha256 {
    private final static String HASH_TYPE = "SHA-256";
    private MessageDigest messageDigest;

    public Sha256() {
        try {
            messageDigest = MessageDigest.getInstance(HASH_TYPE);
        } catch (NoSuchAlgorithmException ignored) {}
    }

    public String hashBytes(byte[] bytes) {
        messageDigest.update(bytes);
        return String.format("%064x", new BigInteger(1, messageDigest.digest()));
    }

    public String hashString(String value) {
        return hashBytes(value.getBytes());
    }
}
