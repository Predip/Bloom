import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class ReadFile {

    public ReadFile(String filePath) {
        Path path = Paths.get(filePath);
        List<String> contents = null;
        try {
            contents = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read from the stream
        int i=0;
        for(String content:contents){
            i++;
            //System.out.println(content);
        }
        System.out.println("Anzahl wörter: "+ i);
    }
}
