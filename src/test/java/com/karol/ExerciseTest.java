package com.karol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExerciseTest {
    @Test
    public void toAnkiLine() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setFrench("bonjour");
        exercise.setPolish("dzień dobry");
        exercise.setType(ExerciseType.SAY);
        assertEquals("dzień dobry;bonjour;[sound:bonjour.mp3];", exercise.toAnkiLine());

        exercise.setType(ExerciseType.WRITE);
        assertEquals("dzień dobry;bonjour;[sound:bonjour.mp3];true", exercise.toAnkiLine());
    }
}