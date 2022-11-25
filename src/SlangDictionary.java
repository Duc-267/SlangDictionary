import java.io.*;
import java.util.*;

public class SlangDictionary {
    HashMap<String, List<String>> slangDictionary;
    HashMap<String, List<String>> searchHistory;
    public SlangDictionary() {
        slangDictionary = new HashMap<>();
        searchHistory = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("slang.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("`")) {
                    String[] words = line.split("`");
                    String slang = words[0];
                    String[] definitions = words[1].split("\\|");
                    List<String> definitionList = new ArrayList<>();
                    for (String definition : definitions) {
                        definition.trim();
                        definitionList.add(definition);
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
        List<String> definitions = slangDictionary.get(slang);
        System.out.print("Meanings of " + slang + ":");
        List<String> definitionList = new ArrayList<>();
        for (String definition : definitions) {
            definitionList.add(definition);
            System.out.print(definition);
        }
        searchHistory.put(slang, definitionList);
        System.out.println();
        System.out.println("-----------------------------");
    }
    public void searchByDefinition(String definition) {
        for (String slang : slangDictionary.keySet()) {
            List<String> meanings = slangDictionary.get(slang);
            for (String meaning : meanings) {
                if (meaning.contains(definition)) {
                    System.out.println("Slang: " + slang);
                    System.out.println("Meaning: " + meaning);
                    System.out.println("-----------------------------");
                } 
            }
        }
    }
    public void showHistory() {
        if (searchHistory.isEmpty()) {
            System.out.print("No search history");
        } else {
            for (String slang : searchHistory.keySet()) {
                System.out.println("Slang: " + slang);
                List<String> definitions = searchHistory.get(slang);
                System.out.print("Meanings: ");
                for (String definition : definitions) {
                    System.out.print(definition);
                }
            }
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
