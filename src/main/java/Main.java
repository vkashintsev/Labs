import Cipher.*;
import Cipher.Key.SymmetricKeys.Rc4Key;
import Cipher.Key.SymmetricKeys.SymmetricKey;

public class Main {
    public static void main(String args[])   {
        SymmetricKey symmetricKey = new Rc4Key("qwerty".toCharArray());
        Cipher cipher = new Rc4Cipher((Rc4Key) symmetricKey);
        cipher.encrypt("src/Files/input/input.txt", "src/Files/output/output.txt");
        cipher.decrypt("src/Files/output/output.txt", "src/Files/output/output (1).txt");
    }


}
