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
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            while (reader.ready()) {
                String result = reader.readLine();
                for (int i = 0; i < result.getBytes().length; i+= 16){
                    byte[] tmp = Arrays.copyOfRange(result.getBytes(), i, i+16);
                    String ciphertext =  Converter.byteToHex(encrypt(tmp));
                    writer.write(ciphertext);
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            while (reader.ready()) {
                String text = reader.readLine();
                if (text.isEmpty()) {
                    writer.newLine();
                    continue;
                }
                byte[] bytes = Converter.hexToByte(text);
                for (int i = 0; i < bytes.length; i+= 16){
                    byte[] tmp = Arrays.copyOfRange(bytes, i, i+16);
                    String recoveredPlaintext = Converter.byteToText(decrypt(tmp));
                    writer.write(recoveredPlaintext);
                }

                writer.newLine();
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
