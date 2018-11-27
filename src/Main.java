import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        ReadFile file = new ReadFile("src/words.txt", 0);
        BloomFilter bloomFilter = new BloomFilter(file, 0.001);
    }
}
