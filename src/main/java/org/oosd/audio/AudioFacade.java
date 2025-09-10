package org.oosd.audio;

import org.oosd.audio.SoundEffects.Effect;

/**
 * Facade to keep audio usage super simple across the app.
 */
public class AudioFacade {

    private static final AudioFacade INSTANCE = new AudioFacade();

    public static AudioFacade get() {
        return INSTANCE;
    }

    private final MusicPlayer music = new MusicPlayer();
    private final SoundEffects sfx = new SoundEffects();

    private AudioFacade() {
    }

    // ===== Background music (MP3) =====
    public void playBgm() {
        playBgm("/audio/background.mp3", true);
    }

    public void playBgm(String resourcePath, boolean loop) {
        music.start(resourcePath, loop);
    }

    public void pauseBgm() {
        music.pause();
    }

    public void resumeBgm() {
        music.resume();
    }

    public void stopBgm() {
        music.stop();
    }

    public void setBgmVolume(double volume) {
        music.setVolume(volume);
    }

    public boolean isBgmPlaying() {
        return music.isPlaying();
    }

    // ===== Sound effects (WAV/AIFF) =====
    public void playEatFood() {
        sfx.play(Effect.EAT_FOOD);
    }

    public void playGameFinish() {
        sfx.play(Effect.GAME_FINISH);
    }

    public void playChangeDir() {
        sfx.play(Effect.CHANGE_DIR);
    }

    public void playNewFood() {
        sfx.play(Effect.NEW_FOOD);
    }

    public void setSfxVolume(double volume) {
        sfx.setVolume(volume);
    }
}
