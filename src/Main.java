import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        // Initialize the variables and hasher
        Random r = new Random();
        Integer b = 12;
        String algoType = "SHA-256";

        // Step 1 Generate the random bits of number n
        String hexOfP = generateNRandomBits(b, r);
        boolean solved = false;
        int iterations = 0;

        // Brute force
        while (!solved) {

            iterations++;

            // Generate a random message M of random length between 1  to 10
            int lengthOfRandomMessage = ThreadLocalRandom.current().nextInt(1, 101);
            String randomMessage = RandomStringUtils.randomAlphanumeric(lengthOfRandomMessage);

            // Hash the random message
            String hexStringOf256Hash = Hasher.hash(randomMessage, algoType);


            // Since we know 4 bits is 2 hex string
            int numberOfHexStringsToCompare = b / 4;
            // extract out last bytes from hash using its hex string
            String lastHexValuesOfBBitsofHash = hexStringOf256Hash.substring(hexStringOf256Hash.length() - numberOfHexStringsToCompare);
            if (hexOfP.equals(lastHexValuesOfBBitsofHash)) {
                System.out.println("--------------------------------------------------------------------------------------------------------");
                System.out.println("Random message: " + randomMessage);
                System.out.println("Hash of the random message:" + hexStringOf256Hash);
                System.out.println("Solved in iteration " + iterations + "!!");
                solved = true;
            }
        }
    }

    public static String generateNRandomBits(Integer numberOfBits, Random r) {
        BigInteger randomBits = new BigInteger(numberOfBits, r);
        String randomBitsInHexString = Hex.encodeHexString(randomBits.toByteArray());
        System.out.println("Random " + numberOfBits + " bits are: " + randomBits.toString(2) + " i.e. " + randomBitsInHexString.substring(randomBitsInHexString.length() - numberOfBits / 4));
        return randomBitsInHexString.substring(randomBitsInHexString.length() - numberOfBits / 4);
    }

}


