import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class Hasher {
    public static String hash(String message, String hashingAlgorithm) {
        String algorithm = hashingAlgorithm;
        String encodedHashString = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashValue = digest.digest(message.getBytes());
            encodedHashString = Hex.encodeHexString(hashValue);
            return encodedHashString;
        } catch (Exception e) {
            System.out.println(e);
        }
        return encodedHashString;
    }

}
