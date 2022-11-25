import java.io.*;
import java.util.*;

public class SlangDictionary {
    HashMap<String, List<String>> slangDictionary;
    public SlangDictionary() {
        slangDictionary = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("slang.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("`")) {
                    String[] words = line.split("`");
                    String slang = words[0];
                    String definitions = words[1];
                    String[] definition = definitions.split("\\|");
                    List<String> definitionList = new ArrayList<>();
                    for (String def : definition) {
                        def.trim();
                        definitionList.add(def);
                    }
                    slangDictionary.put(slang, definitionList);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void searchBySlang(String slang) {
        List<String> meanings = slangDictionary.get(slang);
        System.out.print("Meanings of " + slang + ":");
        for (String meaning : meanings) {
            System.out.print(meaning);
        }
        System.out.println();
        System.out.println("-----------------------------");
    }
    public void printAll() {
        for (String slang : slangDictionary.keySet()) {
            List<String> meanings = slangDictionary.get(slang);
            System.out.print(slang + ":");
            for (String meaning : meanings) {
                System.out.print(meaning);
            }
            System.out.println();
        }
    }
    
}
