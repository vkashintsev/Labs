package Maths;

import java.math.BigInteger;

public class Converter {
    public static String byteToHex(byte[] message){
        StringBuffer buffer = new StringBuffer();
        for (byte symbol : message)
            buffer.append(String.format("%X ", symbol));
        return buffer.toString();
    }

    public static byte[] hexToByte(String message){
        String[] words = message.trim().split("\\s+");
        byte[] bytes = new byte[words.length];
        for (int i = 0; i < words.length; i++)
            bytes[i] = (byte) Integer.parseInt(words[i], 16);
        return bytes;
    }

    public static String byteToText(byte[] message){
        char[] charArray = new char[message.length];
        for(int i = 0; i < charArray.length; i++)
            charArray[i] = (char) (message[i]);
        return new String(charArray);
    }

    public static BigInteger[] textToBigArray(String message){
        int i;
        byte[] temp = new byte[1];
        byte[] digits = message.getBytes();
        BigInteger[] bigDigits = new BigInteger[digits.length];
        for(i = 0; i < bigDigits.length; i++) {
            temp[0] = digits[i];
            bigDigits[i] = new BigInteger(temp);
        }
        return bigDigits;
    }

    public static String bigArrayToHex(BigInteger[] message){
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < message.length; i++) {
            result.append(message[i].toString(16).toUpperCase() + " ");
            if (i != message.length - 1)
                result.append(" ");
        }
        return result.toString();
    }

    public static BigInteger[] hexToBigArray(String message){
        String[] words = message.trim().split("\\s+");
        BigInteger[] encrypted = new BigInteger[words.length];
        for(int i = 0; i < words.length; i++) {
            encrypted[i] = new BigInteger(words[i], 16);
        }
        return encrypted;
    }

    public static String bigArrayToText(BigInteger[] message){
        char[] charArray = new char[message.length];
        for(int i = 0; i < charArray.length; i++)
            charArray[i] = (char) (message[i].intValue());
        return(new String(charArray));
    }

}
