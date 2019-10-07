package Cipher.Key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SymmetricKey implements Key{
    private String key;

    public SymmetricKey(String key){
        this.key = key;
    }


    public String getKey() {
        return key;
    }

    public byte[] getMd5Key(){
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(key.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return digest;
    }
}
