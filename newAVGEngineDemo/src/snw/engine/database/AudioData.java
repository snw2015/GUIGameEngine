package snw.engine.database;

import snw.engine.core.Engine;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioData {
    private File audioFile;
    private Clip clip;

    private boolean hasPrepared = false;

    public AudioData() {
        this(null);
    }

    public AudioData(File audioFile) {
        this.audioFile = audioFile;
    }

    /**
     * @return <tt>true</tt> if has prepared
     */
    public boolean prepareClip() {
        boolean b = hasPrepared();
        if (audioFile == null) return b;
        getNewClip();
        return b;
    }

    public Clip getClip() {
        if (hasPrepared()) return clip;
        if (audioFile == null) return null;
        prepareClip();
        return clip;
    }

    public Clip getNewClip() {
        hasPrepared = false;

        while (!hasPrepared||!clip.isOpen()) {
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(audioFile));
                clip.addLineListener((LineEvent event) -> {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.close();
                });
                hasPrepared = true;
            } catch (Exception e) {
                Engine.log("can't create clip");
                e.printStackTrace();
            }
        }
        return clip;
    }

    public boolean releaseClip() {
        if (hasPrepared()) {
            clip.close();
            clip = null;
            hasPrepared = false;
            return true;
        }
        return false;
    }

    public void setFile(File audioFile) {
        this.audioFile = audioFile;
        hasPrepared = false;
    }

    public File getFile() {
        return this.audioFile;
    }

    public boolean hasPrepared() {
        return hasPrepared;
    }

    @Override
    public String toString() {
        return audioFile.getName();
    }
}