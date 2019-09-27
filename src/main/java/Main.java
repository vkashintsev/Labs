import Cipher.Cipher;
import Cipher.Key.SymmetricKey;
import Cipher.Rc4Cipher;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        Cipher cipher = new Rc4Cipher(new SymmetricKey("qwerty"));

        cipher.encrypt("src/Files/input/input.txt", "src/Files/output/output.txt");
        cipher.encrypt("src/Files/output/output.txt", "src/Files/output/output (1).txt");
    }


}
