package ru.itis.web_project.utils;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
   public  static String getHash(String password)  {
       MessageDigest messageDigest = null;
       try {
           messageDigest = MessageDigest.getInstance("SHA-256");
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       byte[] passBytes = password.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(passBytes);

        byte[] digestedBytes = messageDigest.digest();
        String hashValue =DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        return  hashValue;
    }
}
