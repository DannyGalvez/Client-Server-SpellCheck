import java.io.*;
import java.util.HashSet;

public class SpellCheck {

    String recievedWord;

    public boolean SpellCheck(String word){

        recievedWord = word;

        return checkword(word);
    }
    private boolean checkword(String checked) {

        File dict = new File("/usr/share/dict/words");

        try {
            InputStream stream = new FileInputStream(dict);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));


            HashSet<String> Dictionary = new HashSet<>(100);

            while (reader.readLine() != null) {

                String word = reader.readLine();

                if (word == null) {
                    break;
                }

                word = word.toLowerCase();
                Dictionary.add(word);


            }

            checked = checked.toLowerCase();

            if (Dictionary.contains(checked))
                return true;
            else
                return false;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
