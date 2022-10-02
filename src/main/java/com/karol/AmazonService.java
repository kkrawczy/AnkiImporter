package com.karol;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.*;

import java.io.InputStream;
import java.util.Random;

public class AmazonService {
    private static final String VOICE_MATHIEU = "Mathieu";
    private static final String VOICE_CELINE = "Celine";

    private static final String VOICE_MATTHEW = "Matthew";
    private static final String VOICE_JOANNA = "Joanna";


    AmazonPolly client = AmazonPollyClientBuilder.standard()
        .withRegion(Regions.DEFAULT_REGION)
        .withCredentials( new ProfileCredentialsProvider("private"))
        .build();

    public InputStream generateAudioForText(String text) {
        SynthesizeSpeechRequest request = new SynthesizeSpeechRequest()

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
