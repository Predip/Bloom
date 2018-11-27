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
    private double newErrorProbability;
    private int filterSize;
    private int numberOfHashFunctions;
    private boolean[] data;
    private int[] seedNumber;
    private int[] hashNumber;


    public BloomFilter(ReadFile file, double errorProbability) {
        //anz. Elemente
        this.numberOfExpectedElements = file.getHalfWordsNum();
        // Fehlerwahrscheinlichkeit
        this.errorProbability = errorProbability;
        //Filtergrösse
        filterSize = calculateFilterSize();
        //anz. Hashfunktionen
        numberOfHashFunctions = calculateNumberOfHashFunctions();

        //Filter erstellern
        data = new boolean[filterSize];
        Arrays.fill(data, false);

        System.out.println("Anz. Elemente:" + numberOfExpectedElements);
        System.out.println("Fehlerfunktion: " + errorProbability);
        System.out.println("Filtergrösse: " + filterSize);
        System.out.println("anz. Hashfunktionen: " + numberOfHashFunctions);
        System.out.println();


        //Number of wrong suggestion (Falsche Annahme)
        int nows = useFilter(file, 0);

        //zweiter Source
        ReadFile secondFile = new ReadFile("src/deutsch.txt", 1);
        nows = useFilter(secondFile, 1);
        System.out.println("Anzahl false positives: "+nows);
        System.out.println("Fehlerquote "+calculateErrorProbability(nows, secondFile));

    }


    //Filtergrösse
    private int calculateFilterSize() {
        //m = -((n ln(p))/(ln(2)^2)
        //The java.lang.Math.log() method returns the natural logarithm (base e) of a double value as a parameter.
        //source: https://www.geeksforgeeks.org/java-math-log-method-example/
        return (int) round((-1) * ((numberOfExpectedElements * log(errorProbability)) / Math.pow(log(2), 2)));
    }

    //anz. Hasfunktionen
    private int calculateNumberOfHashFunctions() {
        //k = (m/n)ln(2)
        //The java.lang.Math.log() method returns the natural logarithm (base e) of a double value as a parameter.
        //source: https://www.geeksforgeeks.org/java-math-log-method-example/
        return (int) round((filterSize / numberOfExpectedElements) * log(2));
    }

    private double calculateErrorProbability(int nows, ReadFile file) {
        // p = ((1- e^((-((m/n)ln(2)))(n/m)))^((m/n)ln(2))
        /*
        return Math.pow(
                (1 - Math.pow(
                        Math.E,
                        (-1) * ((filterSize / numberOfExpectedElements) * log(2)) * (numberOfExpectedElements / filterSize))),
                ((filterSize / numberOfExpectedElements) * log(2)));
                */
        //ergibt immer 0, jedoch habe ich die Berechnung von Wikipedia

        //Versuch 2
        return 1.0/(file.getWordsNum()/nows);

    }

    public void add(String word) {
        for (int hash : hashNumber) {
            data[hash] = true;
        }
    }

    public boolean contains(String word) {
        int count = 0;
        for (int hash : hashNumber) {
            if (data[hash]) ;
            else count++;
        }
        if (count > 0) return false;
        else return true;
    }

    private void generateSeed() {
        seedNumber = new int[numberOfHashFunctions];
        for (int i = 0; i < numberOfHashFunctions; i++) {
            seedNumber[i] = new Random().nextInt();
        }
    }

    private void generateHash(String word) {
        hashNumber = new int[numberOfHashFunctions];
        for (int i = 1; i <= numberOfHashFunctions; i++) {
            HashFunction hf = Hashing.murmur3_128(seedNumber[i - 1]);
            long hashL = hf.hashString(word, UTF_8).asLong();
            int hash1 = (int) hashL;
            int hash2 = (int) (hashL >>> 32);

            int combinedHash = hash1 + (i * hash2);
            // Negative Zahlen Positiv "machen"
            if (combinedHash < 0) combinedHash = ~combinedHash;

            hashNumber[i - 1] = combinedHash % this.filterSize;
        }
    }

    private int useFilter(ReadFile file, int i) {
        int count = 0;

        if (i == 0) {
            generateSeed();
            for (String word : file.getFirstWordList()) {
                generateHash(word);
                if (!contains(word)) add(word);
                else count++;
            }
        } else if (i == 1) {
            for (String word : file.getSecondWordList()) {
                generateHash(word);
                if (!contains(word)) add(word);
                else count++;
            }
        }

        return count;
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

    public double getNewErrorProbability() {
        return newErrorProbability;
    }

    public void setNewErrorProbability(double newErrorProbability) {
        this.newErrorProbability = newErrorProbability;
    }
}
