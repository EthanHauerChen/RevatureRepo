import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordList {
    String[] list;
    public WordList(String filename) {
        list = new String[10000];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                list[i] = line;
                i++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        //System.out.println("pre shuffled: " + Arrays.toString(list));
        shuffleList();
    }

    private void shuffleList() {
        List<String> strList = Arrays.asList(list);
        Collections.shuffle(strList);
        list = strList.toArray(list);
        //System.out.println("post shuffle: " + Arrays.toString(list));
    }

    public String[] getList() { return list; }
}
