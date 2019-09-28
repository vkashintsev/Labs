package Cipher;

import Cipher.Key.Key;
import Maths.Converter;

import java.io.*;

public abstract class StreamCipher extends Cipher {
    public StreamCipher(Key key) {
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
                String ciphertext =  Converter.byteToHex(encrypt(result.getBytes()));
                writer.write(ciphertext);
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
                String recoveredPlaintext = Converter.byteToText(decrypt(bytes));
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
