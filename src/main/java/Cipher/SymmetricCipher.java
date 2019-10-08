package Cipher;

import Cipher.Key.SymmetricKeys.SymmetricKey;
import Maths.Converter;

import java.io.*;
import java.util.Arrays;

public abstract class SymmetricCipher extends Cipher {
    public SymmetricCipher(SymmetricKey key) {
        super(key);
    }
    public abstract byte[] encrypt(byte[] message);
    public abstract byte[] decrypt(byte[] message);

    @Override
    public void encrypt(String input, String output){
        try {
            FileInputStream reader = new FileInputStream(input);
            FileOutputStream writer = new FileOutputStream(output);
            while (reader.available() > 0) {
                byte[] tmp = new byte[16];
                int i = reader.read(tmp);
                if (i == 0)
                    break;
                byte[] ciphertext =  encrypt(tmp);
                writer.write(ciphertext);
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(String input, String output) {
        try {
            FileInputStream reader = new FileInputStream(input);
            FileOutputStream writer = new FileOutputStream(output);
            byte[] tmp = new byte[16];
            while (reader.read(tmp)>0) {
                    byte[] recoveredPlaintext = decrypt(tmp);
                    writer.write(recoveredPlaintext);
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
