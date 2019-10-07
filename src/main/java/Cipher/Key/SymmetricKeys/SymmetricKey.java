package Cipher.Key.SymmetricKeys;

import Cipher.Key.Key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SymmetricKey implements Key {
    protected char[] key;

    public SymmetricKey(char[] key){
        this.key = key;
    }


    public char[] getKey() {
        return key;
    }

    public byte[] getMd5Key(){
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(key.toString().getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return digest;
    }
}
