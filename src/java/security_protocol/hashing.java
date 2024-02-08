package security_protocol;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hashing {

    public static void main(String[] args) {
        // Input value to be hashed
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message");
        String input = sc.next();

        // Hash the input value using SHA-256 1503eda8d9579434aeb8d217f5f03dcf
        String hashedValue = hashMD5(input);

        // Print the original and hashed values
        System.out.println("Original Value: " + input);
        System.out.println("Hashed Value (SHA-256): " + hashedValue);
    }

    public static String hashMD5(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = md5.digest(input.getBytes());
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}