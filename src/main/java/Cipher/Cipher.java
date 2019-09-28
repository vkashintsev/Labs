package Cipher;

import Cipher.Key.Key;

import java.io.*;

public abstract class Cipher {
    protected Key key;
    public abstract String encrypt(String message);
    public abstract String decrypt(String message);

    public void encrypt(String input, String output){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            while (reader.ready()) {
                String result = reader.readLine();
                String ciphertext = encrypt(result);
                writer.write(ciphertext);
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
                String recoveredPlaintext = decrypt(text);
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


}
