package snw.engine.database;

import snw.engine.core.Engine;
import snw.structure.BufferData;

import javax.sound.sampled.*;
import java.io.File;

public class AudioBufferData extends BufferData<AudioData> {
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

    @Override
    public AudioData get(String name) {
        AudioData data = super.get(name);
        return data != null ? data : load(name);
    }

    public Clip getClip(String name) {
        AudioData data = get(name);
        return data.getClip();
    }

    public Clip getNewClip(String name) {
        AudioData data = get(name);
        return data.getNewClip();
    }

    public AudioData load(String name) {
        File file = new File(Engine.getProperty("sounds_path") + name + ".wav");
        return new AudioData(file);
    }

    public Clip attainClip(String name) {
        store(name);
        return getClip(name);
    }

    @Override
    protected void disposeData(AudioData data) {
        data.setFile(null);
        data.releaseClip();
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.equals("") ? "no clip stored" : s;
    }
}