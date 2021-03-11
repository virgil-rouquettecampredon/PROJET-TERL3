package org.example;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
    private String path = "src/main/resources/org/example/sounds/";
    private HashMap<String, MediaPlayer> sounds;

    public SoundManager() {
        sounds = new HashMap<>();
        loadSounds();
    }

    private void addSound(String soundName, String soundExtention) {
        sounds.put(soundName, new MediaPlayer(new Media(new File(path+soundName+soundExtention).toURI().toString())));
    }

    private void loadSounds() {
        addSound("button-cancel", ".wav");
        addSound("button-click", ".wav");
        addSound("button-confirm", ".wav");
        addSound("button-hover", ".wav");
        addSound("check", ".wav");
        addSound("end", ".mp3");
        addSound("lose", ".wav");
        addSound("start", ".mp3");
        addSound("win", ".wav");
    }

    private void stopAllSound() {
        sounds.values().forEach(MediaPlayer::stop);
    }

    public void playSound(String sound) {
        stopAllSound();
        //sounds.get(sound).stop();
        sounds.get(sound).play();
    }
}
