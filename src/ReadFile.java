import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private int halfWordsNum;
    private int wordsNum;
    private List<String> firstWordList;
    private List<String> secondWordList;

    public ReadFile(String filePath, int num) {
        Path path = Paths.get(filePath);
        List<String> contents = null;
        try {
            contents = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read from the stream
        if(num == 0) {
            firstWordList = new ArrayList<String>();
            setHalfWordsNum(contents.size());

            //Versuch die Liste in 2 zu teilen und als 2 Source zu benutzen
            //glaube die Aufgabe war nicht so gedacht
        /*secondWordList = new ArrayList<String>();
        setWordsNum(contents.size());
        setHalfWordsNum(contents.size()/2);
        int count = 0;
        for(String content:contents){
            if (count < getHalfWordsNum()) firstWordList.add(content);
            else if (count < getWordsNum()) secondWordList.add(content);
            //System.out.println(content);
        }*/
            for (String content : contents) firstWordList.add(content);
        }
        else if (num == 1){
            secondWordList = new ArrayList<String>();
            setWordsNum(contents.size());
            for (String content : contents) secondWordList.add(content);
        }
    }

    public int getHalfWordsNum() {
        return halfWordsNum;
    }

    public void setHalfWordsNum(int halfWordsNum) {
        this.halfWordsNum = halfWordsNum;
    }

    public List<String> getFirstWordList() {
        return firstWordList;
    }

    public void setFirstWordList(List<String> firstWordList) {
        this.firstWordList = firstWordList;
    }

    public int getWordsNum() {
        return wordsNum;
    }

    public void setWordsNum(int wordsNum) {
        this.wordsNum = wordsNum;
    }

    public List<String> getSecondWordList() {
        return secondWordList;
    }

    public void setSecondWordList(List<String> secondWordList) {
        this.secondWordList = secondWordList;
    }
}
