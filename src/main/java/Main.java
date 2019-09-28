import Cipher.*;
import Cipher.Key.PaillierKey;
import Cipher.Key.RSAKey;
import Cipher.Key.SymmetricKey;
import Cipher.Rc4Cipher;
import Maths.*;
import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String args[])   {
        Cipher cipher = new PaillierCipher(new PaillierKey(512));
       // Cipher cipher = new RSACipher(new RSAKey(512));
        cipher.encrypt("src/Files/input/input.txt", "src/Files/output/output.txt");
        cipher.decrypt("src/Files/output/output.txt", "src/Files/output/output (1).txt");
    }


}
