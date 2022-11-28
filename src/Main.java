public class Main {
    public static void main(String[] args) {
        SlangDictionary slangDictionary = new SlangDictionary();
        slangDictionary.addSlang("TxxxI", "null");
        slangDictionary.searchBySlang("TxxxI");
        slangDictionary.searchByDefinition("God");
        slangDictionary.editSlang("TxxxI", "Duccc");
        slangDictionary.searchBySlang("TxxxI");
        slangDictionary.searchBySlang("SWAGG ");
    }
}
