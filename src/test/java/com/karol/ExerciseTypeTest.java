package com.karol;

import org.junit.Test;

import static com.karol.ExerciseType.SAY;
import static com.karol.ExerciseType.UNDERSTAND;
import static org.assertj.core.api.Assertions.assertThat;

public class ExerciseTypeTest {
    @Test
    public void getByNumberCode() throws Exception {
        assertThat(ExerciseType.getByNumberCode("0")).isEqualTo(SAY);
        assertThat(ExerciseType.getByNumberCode("2")).isEqualTo(UNDERSTAND);
    }

}