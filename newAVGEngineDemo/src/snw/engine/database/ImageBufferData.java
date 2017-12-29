package snw.engine.database;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import snw.engine.core.Engine;
import snw.file.FileDirectReader;
import snw.structure.BufferData;

/*
   Warning!
   Have'nt added any thread synchronizing handling!
   Must be improved!
 */
public class ImageBufferData extends BufferData<Image> {
    private final static ImageBufferData INSTANCE = new ImageBufferData();

    public static ImageBufferData getInstance() {
        return INSTANCE;
    }

    private ImageBufferData(){}

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
    public Image get(String name) {
        Image image = super.get(name);
        return (image != null ? image : load(name));
    }

    private Image load(String name) {
        return (FileDirectReader.getImage(Engine.getProperty("images_path") + name + ".png"));
    }

    @Override
    protected void disposeData(Image data) {
        data.flush();
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.equals("") ? "no image stored" : s;
    }
}