package com.karol;

public class Application {
    private static final String AUDIO_PATH = "/home/karol/Documents/Anki/importFolder/audio/";
    private static final String IMPORT_FILE = "/home/karol/Documents/Anki/importFolder/import.txt";
    private static final String ANKI_CARDS = "/home/karol/Documents/Anki/importFolder/importAnki.txt";

    private final static AmazonService amazonService = new AmazonService();
    private AnkiFileGenerator ankiFileGenerator = new AnkiFileGenerator(amazonService, AUDIO_PATH, IMPORT_FILE, ANKI_CARDS);

    public static void main(String... s) throws Exception {
        new Application().ankiFileGenerator.generateImportData();
        System.out.println("done");
    }

}
