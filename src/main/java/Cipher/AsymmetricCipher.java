package Cipher;

import Cipher.Key.Key;
import Maths.Converter;

import java.io.*;
import java.math.BigInteger;

public abstract class AsymmetricCipher extends Cipher {
    public AsymmetricCipher(Key key) {
        super(key);
    }
    public abstract BigInteger[] encrypt(BigInteger[] message);
    public abstract BigInteger[] decrypt(BigInteger[] message);
    @Override
    public void encrypt(String input, String output){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            while (reader.ready()) {
                String result = reader.readLine();
                BigInteger[] bigDigits = Converter.textToBigArray(result);
                BigInteger[] ciphertext = encrypt(bigDigits);
                writer.write(Converter.bigArrayToHex(ciphertext));
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
            while (reader.ready()) {
                String text = reader.readLine();
                if (text.isEmpty()) {
                    writer.newLine();
                    continue;
                }
                BigInteger[] encrypted = Converter.hexToBigArray(text);
                String recoveredPlaintext = Converter.bigArrayToText(decrypt(encrypted));
                writer.write(recoveredPlaintext);
                writer.newLine();
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
