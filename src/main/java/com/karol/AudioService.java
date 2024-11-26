package com.karol;

import java.io.InputStream;

public interface AudioService {
    InputStream generateAudioForText(String text);
}

