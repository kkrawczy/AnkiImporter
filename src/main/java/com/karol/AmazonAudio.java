package com.karol;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.OutputFormat;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.TextType;
import software.amazon.awssdk.services.polly.model.VoiceId;

import java.io.InputStream;

public class AmazonAudio implements AudioService {

    PollyClient client = PollyClient.builder()
        .region(Region.US_WEST_2)
        .credentialsProvider(ProfileCredentialsProvider.builder().profileName("private").build())
        .build();

    @Override
    public InputStream generateAudioForText(String text) {
        SynthesizeSpeechRequest request = SynthesizeSpeechRequest.builder()
            .textType(TextType.SSML)
            .text("<speak><break time=\"500ms\"/><prosody rate=\"slow\">" + text + "</prosody></speak>")
            //.voiceId(VoiceId.MATTHEW)
            .voiceId(VoiceId.MATHIEU)
            .outputFormat(OutputFormat.MP3)
            .build();

        return client.synthesizeSpeech(request);
    }
}
