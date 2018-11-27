import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.log;
import static java.lang.Math.round;
import static java.nio.charset.StandardCharsets.UTF_8;

public class BloomFilter {

    private int numberOfExpectedElements;
    private double errorProbability;
    private int filterSize;
    private int numberOfHashFunctions;
    private boolean[] data;
    private int[] seedNumber;
    private int[] hashNumber;


    public BloomFilter(int expectedElements, double errorProbability) {
        //anz. Elemente
        this.numberOfExpectedElements = expectedElements;
        // Fehlerwahrscheinlichkeit
        this.errorProbability = errorProbability;
        filterSize = calculateFilterSize();
        data = new boolean[filterSize];
        Arrays.fill(data, false);

        numberOfHashFunctions = calculateNumberOfHashFunctions();
        System.out.println("Filtergrösse: " + filterSize);
        System.out.println("anz. Hashfunktionen: " + numberOfHashFunctions);


        String word = "Hallo";
        generateSeed();
        generateHash(word);
        System.out.println(contains(word));
        if(!contains(word)) add(word);
        String s = "Zahlen:  ";
        for (int hash : hashNumber) {
            s = s + hash + "    ";
        }
        System.out.println(s);


        generateHash(word);
        System.out.println(contains(word));
        if(!contains(word)) add(word);
        s = "Zahlen:  ";
        for (int hash : hashNumber) {
            s = s + hash + "    ";
        }
        System.out.println(s);

        word = "halo";

        generateHash(word);
        System.out.println(contains(word));
        if(!contains(word)) add(word);
        s = "Zahlen:  ";
        for (int hash : hashNumber) {
            s = s + hash + "    ";
        }
        System.out.println(s);

    }

    //Filtergrösse
    private int calculateFilterSize() {
        //m = -((n ln(p))/(ln(2)^2)
        //The java.lang.Math.log() method returns the natural logarithm (base e) of a double value as a parameter.
        //source: https://www.geeksforgeeks.org/java-math-log-method-example/
        return (int) round((-1) * ((numberOfExpectedElements * log(errorProbability)) / log(2)));
    }

    //anz. Hasfunktionen
    private int calculateNumberOfHashFunctions() {
        //k = (m/n)ln(2)
        //The java.lang.Math.log() method returns the natural logarithm (base e) of a double value as a parameter.
        //source: https://www.geeksforgeeks.org/java-math-log-method-example/
        return (int) round((filterSize / numberOfExpectedElements) * log(2));
    }

    public void add(String word) {
        for (int hash : hashNumber) {
            data[hash] = true;
        }
    }

    public boolean contains(String word) {
        int count = 0;
        for (int hash : hashNumber) {
            if (data[hash]);
            else count++;
        }
        if(count > 0) return false;
        else return true;
    }

    private void generateSeed() {
        seedNumber = new int[numberOfHashFunctions];
        for (int i = 0; i < numberOfHashFunctions; i++) {
            seedNumber[i] = new Random().nextInt();
            //Hashing.murmur3_128(seed);
        }
    }

    private void generateHash(String word) {
        hashNumber = new int[numberOfHashFunctions];
        for (int i = 1; i <= numberOfHashFunctions; i++) {
            HashFunction hf = Hashing.murmur3_128(seedNumber[i - 1]);
            //hashNumber[i] = hf.hashString("Hallo", UTF_8).asInt();
            long hashL = hf.hashString(word, UTF_8).asLong();
            int hash1 = (int) hashL;
            int hash2 = (int) (hashL >>> 32);

            int combinedHash = hash1 + (i * hash2);
            // Flip all the bits if it's negative (guaranteed positive number)
            if (combinedHash < 0) combinedHash = ~combinedHash;

            hashNumber[i - 1] = combinedHash % this.filterSize;

        }
    }

    public int getNumberOfExpectedElements() {
        return numberOfExpectedElements;
    }

    public void setNumberOfExpectedElements(int numberOfExpectedElements) {
        this.numberOfExpectedElements = numberOfExpectedElements;
    }

    public double getErrorProbability() {
        return errorProbability;
    }

    public void setErrorProbability(double errorProbability) {
        this.errorProbability = errorProbability;
    }

    public int getFilterSize() {
        return filterSize;
    }

    public void setFilterSize(int filterSize) {
        this.filterSize = filterSize;
    }

    public int getNumberOfHashFunctions() {
        return numberOfHashFunctions;
    }

    public void setNumberOfHashFunctions(int numberOfHashFunctions) {
        this.numberOfHashFunctions = numberOfHashFunctions;
    }

}
