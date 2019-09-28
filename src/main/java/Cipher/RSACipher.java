package Cipher;
import Cipher.Key.RSAKey;
import java.math.BigInteger;

public class RSACipher extends Cipher {
    BigInteger N;
    BigInteger E, D;

    public RSACipher(RSAKey key) {
        super(key);
        E = key.getPrivateKey().getKey();
        D = key.getPublicKey().getKey();
        N = key.getPrivateKey().getValue();
    }
    @Override
    public BigInteger[] encrypt(String message) {
        int i;
        byte[] temp = new byte[1];
        byte[] digits = message.getBytes();
        BigInteger[] bigDigits = new BigInteger[digits.length];
        for(i = 0; i < bigDigits.length; i++) {
            temp[0] = digits[i];
            bigDigits[i] = new BigInteger(temp);
        }
        BigInteger[] encrypted = new BigInteger[bigDigits.length];
        for(i = 0; i < bigDigits.length; i++)
            encrypted[i] = bigDigits[i].modPow(E, N);
        return encrypted;
    }
    @Override
    public String decrypt(BigInteger[] encrypted) {
        int i;
        BigInteger[] decrypted = new BigInteger[encrypted.length];
        for(i = 0; i < decrypted.length; i++)
            decrypted[i] = encrypted[i].modPow(D, N);
        char[] charArray = new char[decrypted.length];
        for(i = 0; i < charArray.length; i++)
            charArray[i] = (char) (decrypted[i].intValue());
        return(new String(charArray));
    }
}
