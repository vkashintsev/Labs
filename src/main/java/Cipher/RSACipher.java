package Cipher;
import Cipher.Key.RSAKey;
import java.math.BigInteger;
import java.io.*; 

public class RSACipher extends Cipher {
    BigInteger N;
    BigInteger E, D;

    public RSACipher(RSAKey key) {
        super(key);
        E = key.getPrivateKey().getKey();
        D = key.getPublicKey().getKey();
        N = key.getPrivateKey().getValue();
    }

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
        return(encrypted);
    }

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


    @Override
    public void encrypt(String input, String output) {
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(input));
            writer = new BufferedWriter(new FileWriter(output));
            String result;
            while (reader.ready()) {
                result = reader.readLine();
                BigInteger[] ciphertext = encrypt(result);
                for (int i = 0; i < ciphertext.length; i++) {
                    writer.write(ciphertext[i].toString(16).toUpperCase() + " ");
                    if (i != ciphertext.length - 1)
                        writer.write(" ");
                }
                writer.newLine();
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(String input, String output) {
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(input));
            writer = new BufferedWriter(new FileWriter(output));
            String result = "";

            while (reader.ready()) {
                BigInteger[] ciphertext = new BigInteger[255];
                int i = 0;
                char ch = '\0';
                while (i < 255 && reader.ready()) {
                    ch = (char) reader.read();
                    if (ch == '\n')
                        break;
                    if (!Character.isSpaceChar(ch) && (ch != '\r')) {
                        result += ch;
                    } else {
                        if (result.length() == 0){
                            continue;
                        }
                        ciphertext[i++] = new BigInteger(result, 16);
                        result = "";
                    }
                }

                BigInteger[] tmp = new BigInteger[i];
                for (int j = 0; j < i; j++)
                    tmp[j] = ciphertext[j];
                String recoveredPlaintext = decrypt(tmp);
                writer.write(recoveredPlaintext);

                if (ch == '\n')
                    writer.newLine();
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
