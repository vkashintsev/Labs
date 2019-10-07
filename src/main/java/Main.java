import Cipher.*;
import Cipher.Key.SymmetricKey;

public class Main {
    public static void main(String args[])   {
        SymmetricKey symmetricKey = new SymmetricKey("qwerty");
        Cipher cipher = new RijndaelCipher(symmetricKey);
        cipher.encrypt("src/Files/input/input.txt", "src/Files/output/output.txt");
        cipher.decrypt("src/Files/output/output.txt", "src/Files/output/output (1).txt");
    }


}
