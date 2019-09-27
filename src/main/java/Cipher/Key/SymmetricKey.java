package Cipher.Key;

public class SymmetricKey implements Key{
    private String key;

    public SymmetricKey(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public int length() {
        return key.length();
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }
}
