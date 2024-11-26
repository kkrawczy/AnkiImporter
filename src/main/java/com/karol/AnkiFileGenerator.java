package com.karol;

import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class AnkiFileGenerator {
    private AudioService amazonService;
    private String audioPath;
    private String importFile;
    private String ankiImportFile;

    public void generateImportData() throws IOException {
        List<Exercise> exercises = Files.lines(Paths.get(importFile)).map(Exercise::new).collect(toList());

        exercises.forEach(this::generateAudio);

        String ankiCards = exercises.stream()
            .map(Exercise::toAnkiLine)
            .collect(joining(System.getProperty("line.separator")));

        saveAnkiCards(ankiCards);
    }

    private void saveAnkiCards(String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(ankiImportFile))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateAudio(Exercise e) {
        InputStream stream = amazonService.generateAudioForText(e.getFrench());
        FilesUtils.saveInputStreamOnDisk(stream, audioPath + e.getAudioFileName());
    }
}
