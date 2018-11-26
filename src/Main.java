import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        ReadFile file = new ReadFile("src/words.txt");
        BloomFilter bloomFilter = new BloomFilter(file.getWords(), 0.001);
    }
}
