import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        ReadFile file = new ReadFile("src/words.txt");
        int n = 6000; //anz. Elemente
        double p = 0.001; // Fehlerwahrscheinlichkeit

       double  m = ceil((n * log(p)) / log(1 / pow(2, log(2)))); //Filtergrösse
       long k = round((m / n) * log(2)); //anz. Hasfunktionen

        System.out.println("Filtergrösse: "+m);
        System.out.println("anz. Hashfunktionen: "+k);
    }
}
