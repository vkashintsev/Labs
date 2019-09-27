package Cipher.Key;

public class SymmetricKey implements Key{
    private String key;

    public SymmetricKey(String key){
        this.key = key;
    }


    public String getKey() {
        return key;
    }

}
