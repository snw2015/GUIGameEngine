package snw.engine.database;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import snw.file.FileDirectReader;

/*
   Warning!
   Have'nt add any thread synchronizing handling!
   Must be improved!
 */
public class ImageBufferData {
    private final static ImageBufferData DATA = new ImageBufferData();

    private final HashMap<String, Image> bufferData;
    private final HashMap<String, Integer> referenceCounters;

    private ImageBufferData() {
        bufferData = new HashMap<>();
        referenceCounters = new HashMap<>();
    }

    public static ImageBufferData getInstance() {
        return DATA;
    }

    /**
     * Store an image by its name.
     * The image file so far should be the png format.
     *
     * @param name the name tag used for this image
     * @return <tt>false</tt> if overwrote or the image is null
     */
    public boolean storeImage(String name) {
        if (bufferData.containsKey(name)) {
            referenceCounters.put(name, referenceCounters.get(name) + 1);
            return (false);
        } else {
            Image image = loadImage(name);
            if (image != null) {
                bufferData.put(name, loadImage(name));
                referenceCounters.put(name, 0);
                return (true);
            } else {
                // TODO throw errors
                return (false);
            }
        }
    }

    /**
     * Store an image by passing a specific name.
     * Note: Unsafe, recommend to use {@link #storeImage(String name) this method} instead.
     *
     * @param name  the name tag used for this image
     * @param image the image to be stored
     * @return <tt>True</tt> if overwritten; <tt>false</tt> otherwise.
     */
    public boolean storeImage(String name, Image image) {
        if (bufferData.containsKey(name)) {
            bufferData.put(name, image);
            referenceCounters.put(name, referenceCounters.get(name) + 1);
            return (false);
        } else {
            bufferData.put(name, image);
            referenceCounters.put(name, 0);
            return (true);
        }
    }

    /**
     * release an image data; The data will only be released if all of the references counting have been disposed.
     *
     * @param name the name tag of the image to be released
     * @return <tt>True</tt> if has such image; <tt>false</tt> otherwise.
     */
    public boolean releaseImage(String name) {
        if (bufferData.containsKey(name)) {
            if (referenceCounters.get(name) == 0) {
                bufferData.get(name).flush();
                bufferData.remove(name);
                referenceCounters.remove(name);
            } else {
                referenceCounters.put(name, referenceCounters.get(name) - 1);
            }
            return (true);
        } else {
            return (false);
        }
    }

    /**
     * Get an image (whether stored or not) by passing its name.
     * This method will not store the image data into the buffer data.
     *
     * @param name the name of the image
     * @return the image under the name
     * @see #attainImage(String name)
     */
    public Image getImage(String name) {
        Image image = bufferData.get(name);
        return (image != null ? image : loadImage(name));
    }

    /**
     * Get an image (whether stored or not) by passing its name,
     * while storing the image data into the buffer data if it hasn't been stored.
     * If it has been stored before, this method will increase the image's reference counter.
     *
     * @param name the name of the image
     * @return the image under the name
     * @see #getImage(String name)
     */
    public Image attainImage(String name) {
        storeImage(name);
        return (getImage(name));
    }

    /**
     * Returns <tt>true</tt> if an image with the specific name has been stored in the buffer data.
     *
     * @param name the name of the image
     */
    public boolean hasStored(String name) {
        return bufferData.containsKey(name);
    }

    private Image loadImage(String name) {
        if (name.contains(".")) {
            System.out.println(name);
            return (FileDirectReader.getImage("file/image/" + name));
        }
        return (FileDirectReader.getImage("file/image/" + name + ".png"));
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<String, Integer> entry : referenceCounters.entrySet()) {
            s += entry.toString() + ";\n";
        }
        return s.equals("") ? "no image stored" : s;
    }
}