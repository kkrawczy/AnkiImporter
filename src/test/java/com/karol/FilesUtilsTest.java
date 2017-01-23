package com.karol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FilesUtilsTest {
    @Test
    public void generateValidFileName() throws Exception {
        assertEquals("karol", FilesUtils.generateValidFileName("karol?"));
        assertEquals("karol", FilesUtils.generateValidFileName("kar*ol"));
        assertEquals("karol", FilesUtils.generateValidFileName("karo/l?"));
        assertEquals("karol_jest_cool", FilesUtils.generateValidFileName("karol jest cool"));
        assertEquals("karol__jest_cool", FilesUtils.generateValidFileName("karol  jest cool"));
    }
}