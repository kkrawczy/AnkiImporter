package com.karol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.karol.FilesUtils.generateValidFileName;
import static java.lang.String.format;

@NoArgsConstructor
@Getter
@Setter
public class Exercise {
    private final static String INPUT_SEPARATOR = "\t";
    private final static String OUTPUT_SEPARATOR = ";";
    private String french;
    private String polish;
    private ExerciseType type;

    public Exercise(String exercise) {
        String[] data = exercise.split(INPUT_SEPARATOR);
        assertAllInfoIsPresent(data);
        french = data[0].replaceAll(OUTPUT_SEPARATOR, ",");
        polish = data[1].replaceAll(OUTPUT_SEPARATOR, ",");

        type = ExerciseType.getByNumberCode(data[2]);
    }

    private void assertAllInfoIsPresent(String[] data) {
        if (data.length != 3) {
            throw new RuntimeException(format("There are %d values. In the line: %s", data.length, data[0]));
        }
    }

    public String getAudioFileName() {
        return generateValidFileName(french) + ".mp3";
    }

    public String toAnkiLine() {
        return Arrays.stream(new String[]{polish, french, "[sound:" + getAudioFileName() + "]", isWrite()})
            .collect(Collectors.joining(OUTPUT_SEPARATOR));
    }

    @Override
    public String toString() {
        return format("Exercise(french=%s, polish=%s, type=%s)", french, polish, type);
    }

    private String isWrite() {
        return type == ExerciseType.WRITE ? "true" : "";
    }
}

enum ExerciseType {
    SAY, WRITE, UNDERSTAND;

    public static ExerciseType getByNumberCode(String code) {
        return values()[Integer.parseInt(code)];
    }
}