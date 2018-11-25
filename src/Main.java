import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Random r = new Random(System.currentTimeMillis());
        Hashing.murmur3_128(r.nextInt());
    }
}
