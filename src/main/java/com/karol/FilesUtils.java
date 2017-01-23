package com.karol;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilesUtils {
    public static String[] FORBIDDEN_CHARS = {"/", "<", ">", ":", "\\\"", "|", "\\?", "\\*", ";"};

    public static String generateValidFileName(String s) {
        int toIndex = s.length() > 30 ? 30 : (s.length());
        s = s.substring(0, toIndex)
            .replaceAll(" ", "_").trim();
        return replaceForbiddenChars(s);
    }

    private static String replaceForbiddenChars(String s) {
        for (String forbiddenChar : FORBIDDEN_CHARS) {
            s = s.replaceAll(forbiddenChar, "");
        }
        return s;
    }

    public static void saveInputStreamOnDisk(InputStream stream, String filename) {
        try {
            byte[] data = inputStreamToByteArray(stream);
            saveByteArrayOnDisk(data, filename);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Not able to save audio file " + filename);
        }
    }

    private static byte[] inputStreamToByteArray(InputStream inStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inStream.read(buffer)) > 0) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    private static void saveByteArrayOnDisk(byte[] byteArray, String outFilePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(outFilePath);
        fos.write(byteArray);
        fos.close();
    }
}