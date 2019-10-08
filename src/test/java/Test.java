import Cipher.Key.AssymmetricKeys.RSAKey;
import Cipher.Key.SymmetricKeys.Rc4Key;
import Cipher.RSACipher;
import Cipher.Rc4Cipher;

public class Test {

    @org.junit.Test
    public void testRc4(){
        Rc4Key rc4Key = new Rc4Key("qwerty".toCharArray());
        Rc4Cipher cipher = new Rc4Cipher(rc4Key);
        cipher.encrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\input\\input.txt", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\output.txt");
        cipher.decrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\output.txt", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\output (1).txt");
        cipher.encrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\input\\anthem-choir.mp3", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\anthem-choir1.mp3");
        cipher.decrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\anthem-choir1.mp3", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\anthem-choir2.mp3");
        cipher.encrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\input\\sticker.webp", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\sticker1.webp");
        cipher.decrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\sticker1.webp", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\sticker2.webp");
    }

    @org.junit.Test
    public void testRSA(){
        RSAKey rsaKey = new RSAKey(1024);
        RSACipher rsaCipher = new RSACipher(rsaKey);
        rsaCipher.encrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\input\\input.txt", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\output.txt");
        rsaCipher.decrypt("C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\output.txt", "C:\\Users\\vkash\\IdeaProjects\\Labs\\src\\Files\\output\\output (1).txt");
    }
}
