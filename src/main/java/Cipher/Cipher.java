package Cipher;

import Cipher.Key.Key;

import java.io.*;
import java.math.BigInteger;

public abstract class Cipher {
    protected Key key;
    public abstract BigInteger[] encrypt(String text);
    public abstract String decrypt(BigInteger[] ciphertext);

    public void encrypt(String input, String output){
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(input));
            writer = new BufferedWriter(new FileWriter(output));
            String result;
            while (reader.ready()) {
                result = reader.readLine();
                BigInteger[] ciphertext = encrypt(result);
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < ciphertext.length; i++) {
                    buffer.append(ciphertext[i].toString(16).toUpperCase() + " ");
                }
                writer.write(buffer.toString());
                writer.newLine();
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

                String[] words = text.trim().split("\\s+");
                BigInteger[] ciphertext = new BigInteger[words.length];
                for (int i = 0; i < words.length; i++)
                    ciphertext[i] = new BigInteger(words[i], 16);
                String recoveredPlaintext = decrypt(ciphertext);
                writer.write(recoveredPlaintext);
                writer.newLine();
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Key getKey(){
        return key;
    }
    public void setKey(Key key){
        this.key = key;
    }

    public Cipher(Key key){
        this.key = key;
     }

    protected BigInteger[] textToIntArray(String text){
        BigInteger[] result = new BigInteger[text.length()];
        for (int i = 0; i < text.length(); i++){
            result[i] = BigInteger.valueOf((int)text.charAt(i));
        }
        return result;
    }

    protected String intArrayToText(BigInteger[] ciphertext) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < ciphertext.length; i++) {
            result.append( (char) (Integer.parseInt(ciphertext[i].toString())));
        }
        return result.toString();
    }

}
