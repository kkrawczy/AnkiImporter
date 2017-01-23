package com.karol;

import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class AmazonService {
    private static final String VOICE_MATHIEU = "Mathieu";
    private static final String VOICE_CELINE = "Celine";

    AmazonPollyClient client = new AmazonPollyClient();

    public InputStream generateAudioForText(String text) {
        SynthesizeSpeechRequest request = new SynthesizeSpeechRequest()
            .withText(text)
            .withVoiceId(pickRandomVoice())
            .withOutputFormat(OutputFormat.Mp3);

        SynthesizeSpeechResult response = client.synthesizeSpeech(request);

        return response.getAudioStream();
    }

    private String pickRandomVoice() {
        return new Random().nextBoolean() ? VOICE_MATHIEU : VOICE_CELINE;
    }

    public static byte[] inputStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] byteArray = buffer.toByteArray();
        return byteArray;
    }

}
