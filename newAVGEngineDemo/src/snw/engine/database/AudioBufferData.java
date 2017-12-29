package snw.engine.database;

import snw.engine.core.Engine;
import snw.structure.BufferData;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioBufferData extends BufferData<Clip> {
    private final static AudioBufferData INSTANCE = new AudioBufferData();

    public static AudioBufferData getInstance() {
        return INSTANCE;
    }

    private AudioBufferData() {
    }

    public void clear() {
        releaseAll();
    }

    @Override
    public boolean store(String name) {
        if (!super.store(name)) {
            return store(name, load(name));
        }
        return true;
    }

    public Clip get(String name) {
        Clip clip = super.get(name);
        return (clip != null ? clip : load(name));
    }

    public Clip load(String name) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            //TODO
            e.printStackTrace();
        }

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Engine.getProperty("sounds_path")+name+".wav"));
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            //TODO
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        return clip;
    }

    @Override
    protected void disposeData(Clip data){
        data.close();
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.equals("") ? "no clip stored" : s;
    }
}