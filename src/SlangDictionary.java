import java.io.*;
import java.util.*;

public class SlangDictionary {
    HashMap<String, List<String>> slangDictionary;
    HashMap<String, List<String>> searchHistory;
    public SlangDictionary() {
        slangDictionary = new HashMap<>();
        searchHistory = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("database.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("`")) {
                    String[] words = line.split("`");
                    String slang = words[0];
                    String[] definitions = words[1].split("\\|");
                    List<String> definitionList = new ArrayList<>();
                    for (String definition : definitions) {
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
    public void resetListSlang() {
        slangDictionary.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("slang.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("`")) {
                    String[] words = line.split("`");
                    String slang = words[0];
                    String[] definitions = words[1].split("\\| ");
                    List<String> definitionList = new ArrayList<>();
                    for (String definition : definitions) {
                        definitionList.add(definition);
                    }
                    slangDictionary.put(slang, definitionList);
                }
            }
            bufferedReader.close();
            this.saveToDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchBySlang(String slang) {
        List<String> definitions = slangDictionary.get(slang);
        if (definitions != null) {
            System.out.println("Slang: " + slang);
            System.out.println("Definitions: ");
            for (int i = 0; i < definitions.size(); i++) {
                System.out.println((i + 1) + ". " + definitions.get(i));
            }
            searchHistory.put(slang, definitions);
        } else {
            System.out.println("Slang not found!");
        }
        System.out.println("-----------------------------");
    }
    public void searchByDefinition(String definition) {
        for (String slang : slangDictionary.keySet()) {
            List<String> meanings = slangDictionary.get(slang);
            for (String meaning : meanings) {
                if (meaning.contains(definition)) {
                    System.out.println("Slang: " + slang);
                    System.out.println("Definitions: ");
                    for (int i = 0; i < meanings.size(); i++) {
                        System.out.println((i + 1) + ". " + meanings.get(i));
                    }
                    searchHistory.put(slang, meanings);
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
    public void addSlang(String slang, String definition) {
        List<String> definitions = slangDictionary.get(slang);
        if (definitions == null) {
            definitions = new ArrayList<>();
            definitions.add(definition);
            slangDictionary.put(slang, definitions);
        } else {
            System.out.println("Slang already exists!");
            System.out.println("Do you want to overwrite or add new definition?");
            System.out.println("1. Overwrite");
            System.out.println("2. Add new definition");
            System.out.print("Your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                definitions.clear();
                definitions.add(definition);
            } else if (choice == 2) {
                definitions.add(definition);
            }
        }
        this.saveToDatabase();
        System.out.println("Slang added!");
        System.out.println("-----------------------------");
    }
    public void editSlang(String slang, String definition) {
        List<String> definitions = slangDictionary.get(slang);
        if (definitions != null) {
            definitions.clear();
            definitions.add(definition);
            this.saveToDatabase();
            System.out.println("Slang edited!");
        } else {
            System.out.println("Slang not found!");
        }
        System.out.println("-----------------------------");
    }
    public void deleteSlang(String slang) {
        List<String> definitions = slangDictionary.get(slang);
        System.out.println("Do you want to delete this slang?");
        System.out.println("Slang: " + slang);
        System.out.println("Definitions: ");
        for (int i = 0; i < definitions.size(); i++) {
            System.out.println((i + 1) + ". " + definitions.get(i));
        }
        System.out.println("-----------------------------");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            slangDictionary.remove(slang);
            this.saveToDatabase();
            System.out.println("Slang deleted!");
        } else {
            System.out.println("Slang not deleted!");
        }
        System.out.println("-----------------------------");
    }
    public String randomSlang(){
        Random random = new Random();
        List<String> keys = new ArrayList<String>(slangDictionary.keySet());
        return keys.get(random.nextInt(keys.size()));
    }
    public void onThisDaySlangWord() {
        Random random = new Random();
        int index = random.nextInt(slangDictionary.size());
        int i = 0;
        for (String slang : slangDictionary.keySet()) {
            if (i == index) {
                System.out.println("Slang: " + slang);
                List<String> definitions = slangDictionary.get(slang);
                System.out.println("Definitions: ");
                for (int j = 0; j < definitions.size(); j++) {
                    System.out.println((j + 1) + ". " + definitions.get(j));
                }
                System.out.println("-----------------------------");
                break;
            }
            i++;
        }
    }
    public void slangQuiz() {
        String question = this.randomSlang();
        List<String> questionDefinitions = slangDictionary.get(question);
        String answer = questionDefinitions.get(0);
        List<String> choicesList = new ArrayList<>();
        choicesList.add(answer);
        for (int i = 0; i < 3; i++) {
            String choice = this.randomSlang();
            List<String> choiceDefinitions = slangDictionary.get(choice);
            String choiceDefinition = choiceDefinitions.get(0);
            if (!choicesList.contains(choiceDefinition)) {
                choicesList.add(choiceDefinition);
            } else {
                i--;
            }
        }
        Collections.shuffle(choicesList);
        System.out.println("Slang: " + question);
        System.out.println("Definitions: ");
        for (int i = 0; i < choicesList.size(); i++) {
            System.out.println((i + 1) + ". " + choicesList.get(i));
        }
        System.out.println("-----------------------------");
        System.out.print("Your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choicesList.get(choice - 1).equals(answer)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong! The correct answer is: " + answer);
        }
        System.out.println("-----------------------------");
    }
    public void definitionQuiz() {
        String slang = this.randomSlang();
        List<String> questionDefinitions = slangDictionary.get(slang);
        String question = questionDefinitions.get(0);
        List<String> choicesList = new ArrayList<>();
        choicesList.add(slang);
        for (int i = 0; i < 3; i++) {
            String choice = this.randomSlang();
            if (!choicesList.contains(choice)) {
                choicesList.add(choice);
            } else {
                i--;
            }
        }
        Collections.shuffle(choicesList);
        System.out.println("Definition: " + question);
        System.out.println("Slangs: ");
        for (int i = 0; i < choicesList.size(); i++) {
            System.out.println((i + 1) + ". " + choicesList.get(i));
        }
        System.out.println("-----------------------------");
        System.out.print("Your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choicesList.get(choice - 1).equals(slang)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong! The correct answer is: " + slang);
        }
        System.out.println("-----------------------------");
    }
    public void saveToDatabase() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("database.txt"));
            for (String slang : slangDictionary.keySet()) {
                bufferedWriter.write(slang + "`");
                List<String> definitions = slangDictionary.get(slang);
                for (int i = 0; i < definitions.size(); i++) {
                    bufferedWriter.write(definitions.get(i));
                    if (i != definitions.size() - 1) {
                        bufferedWriter.write("|");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
