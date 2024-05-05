package com.skillsync.backend.utils;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Run this to quickly generate a secret key for 'tokenSecret' section in application.yaml (for local testing)
 */
public class SecureKeyGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 64;  // Length of the token secret
    private static final SecureRandom random = new SecureRandom();

    public static String generateSecureRandomKey() {
        return IntStream.range(0, LENGTH)
                .map(i -> CHARACTERS.charAt(random.nextInt(CHARACTERS.length())))
                .mapToObj(c -> "" + (char) c)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String secureKey = generateSecureRandomKey();
        System.out.println("Generated Secure Key: " + secureKey);
    }
}
