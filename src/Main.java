import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SlangDictionary slangDictionary = new SlangDictionary();
        System.out.println("Wellcome to Slang Dictionary");
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Search by slang word");
            System.out.println("2. Search by definition");
            System.out.println("3. Show history");
            System.out.println("4. Add new slang");
            System.out.println("5. Edit slang");
            System.out.println("6. Delete slang");
            System.out.println("7. Reset slang list");
            System.out.println("8. On this day slang word");
            System.out.println("9. Slang word quiz");
            System.out.println("10. Definition quiz");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("-----------------------------");
                    System.out.println("1. Search by slang word");
                    System.out.print("Enter slang word: ");
                    String slangWord = scanner.nextLine();
                    slangDictionary.searchBySlang(slangWord);
                    break;
                case 2:
                    System.out.println("-----------------------------");
                    System.out.println("2. Search by definition");
                    System.out.print("Enter definition: ");
                    String definition = scanner.nextLine();
                    slangDictionary.searchByDefinition(definition);
                    break;
                case 3:
                    System.out.println("-----------------------------");
                    System.out.println("3. Show history");
                    slangDictionary.showHistory();
                    break;
                case 4:
                    System.out.println("-----------------------------");
                    System.out.println("4. Add new slang");
                    System.out.print("Enter slang word: ");
                    slangWord = scanner.nextLine();
                    System.out.print("Enter definition: ");
                    definition = scanner.nextLine();
                    slangDictionary.addSlang(slangWord, definition);
                    break;
                case 5:
                    System.out.println("-----------------------------");
                    System.out.println("5. Edit slang");
                    System.out.print("Enter slang word: ");
                    slangWord = scanner.nextLine();
                    System.out.print("Enter definition: ");
                    definition = scanner.nextLine();
                    slangDictionary.editSlang(slangWord, definition);
                    break;
                case 6:
                    System.out.println("-----------------------------");
                    System.out.println("6. Delete slang");
                    System.out.print("Enter slang word: ");
                    slangWord = scanner.nextLine();
                    slangDictionary.deleteSlang(slangWord);
                    break;
                case 7:
                    System.out.println("-----------------------------");
                    System.out.println("7. Reset slang list");
                    slangDictionary.resetListSlang();
                    break;
                case 8:
                    System.out.println("-----------------------------");
                    System.out.println("8. On this day slang word");
                    slangDictionary.onThisDaySlangWord();
                    break;
                case 9:
                    System.out.println("-----------------------------");
                    System.out.println("9. Slang word quiz");
                    slangDictionary.slangQuiz();
                    break;
                case 10:
                    System.out.println("-----------------------------");
                    System.out.println("10. Definition quiz");
                    slangDictionary.definitionQuiz();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (choice != 0);
    }

}
