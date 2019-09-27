import Cipher.*;
import Cipher.Key.RSAKey;
import Cipher.Key.SymmetricKey;
import Cipher.Rc4Cipher;
import Maths.*;
import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String args[])   {
        RSACipher cipher = new RSACipher(new RSAKey(1024));
        cipher.encrypt("src/Files/input/input.txt", "src/Files/output/output.txt");
        cipher.decrypt("src/Files/output/output.txt", "src/Files/output/output (1).txt");
     /*   Cipher cipher = new Rc4Cipher(new SymmetricKey("qwerty"));

        cipher.encrypt("src/Files/input/input.txt", "src/Files/output/output.txt");
        cipher.encrypt("src/Files/output/output.txt", "src/Files/output/output (1).txt"); */
    }


}
