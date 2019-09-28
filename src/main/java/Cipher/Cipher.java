package Cipher;

import Cipher.Key.Key;

public abstract class Cipher {
    protected Key key;


    public abstract void encrypt(String input, String output);
    public abstract void decrypt(String input, String output);


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
