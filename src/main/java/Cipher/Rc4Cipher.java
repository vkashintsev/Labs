package Cipher;
import Cipher.Key.SymmetricKey;

import java.io.*;

public class Rc4Cipher extends Cipher {
    public Rc4Cipher(SymmetricKey key) {
        super(key);
    }

    private String compute(String text) {
        String key =  this.key.getKey();
        StringBuffer result = new StringBuffer();
        int x, y, j = 0;
        int[] perm = new int[256];

        for (int i = 0; i < 256; i++) {
            perm[i] = i;
        }

        for (int i = 0; i < 256; i++) {
            j = (key.charAt(i % key.length()) + perm[i] + j) % 256;
            x = perm[i];
            perm[i] = perm[j];
            perm[j] = x;
        }

        for (int i = 0; i < text.length(); i++) {
            y = i % 256;
            j = (perm[y] + j) % 256;
            x = perm[y];
            perm[y] = perm[j];
            perm[j] = x;
            result.append((char)( ((int) text.charAt(i)) ^ perm[(perm[y] + perm[j]) % 256]));
        }
        return result.toString();
    }



    @Override
    public void encrypt(String input, String output) {
        char[] arr = new char[256];
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            int charsRead;
            while ((charsRead = reader.read(arr)) != -1) {
                for (int i = 0; i < charsRead; i++) {
                    result += arr[i];
                }
                writer.write(compute(result));
            }
            reader.close();
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(String input, String output) {
        encrypt(input,output);
    }
}

