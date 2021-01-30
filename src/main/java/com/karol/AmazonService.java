package com.karol;

import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.*;

import java.io.InputStream;
import java.util.Random;

public class AmazonService {
    private static final String VOICE_MATHIEU = "Mathieu";
    private static final String VOICE_CELINE = "Celine";

    private static final String VOICE_MATTHEW = "Matthew";
    private static final String VOICE_JOANNA = "Joanna";


    AmazonPollyClient client = new AmazonPollyClient();

    public InputStream generateAudioForText(String text) {
        SynthesizeSpeechRequest request = new SynthesizeSpeechRequest()
                .withEngine(Engine.Neural)
                .withTextType(TextType.Ssml)
                .withText("<speak><prosody rate=\"slow\">"+text+"</prosody></speak>")
                .withVoiceId(VOICE_MATTHEW)
                .withOutputFormat(OutputFormat.Mp3);

        SynthesizeSpeechResult response = client.synthesizeSpeech(request);

        return response.getAudioStream();
    }

    private String pickRandomVoice() {
        return new Random().nextBoolean() ? VOICE_MATHIEU : VOICE_MATHIEU;
    }

}
