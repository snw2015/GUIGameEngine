package snw.engine.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EngineProperties {
    private final static EngineProperties INSTANCE = new EngineProperties();

    private final HashMap<String, String> properties;

    private EngineProperties() {
        properties = new HashMap<>();
        loadAll(new String[]{
                "properties_path: file/properties.txt",
                "frame_size: 1680 x 1050",
                "etc: etc"});
    }

    public static EngineProperties getInstance() {
        return INSTANCE;
    }


    public HashMap<String, String> getProperties() {
        return properties;
    }

    public String[] getPropertiesStr() {
        ArrayList<String> listProperties = new ArrayList<>();

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            listProperties.add(entry.getKey() + ": " + entry.getValue());
        }

        String[] a = new String[0];
        return listProperties.toArray(a);
    }

    public String get(String name) {
        return properties.get(name);
    }

    /**
     * @param name
     * @param value
     * @return <tt>true<tt/> if overwrote (setData)
     */
    public boolean set(String name, String value) {
        boolean b = properties.containsKey(name);
        properties.put(name, value);
        return b;
    }

    /**
     * @param propertyStr
     * @return <tt>true</tt> if setData successfully
     */
    public boolean load(String propertyStr) {
        String[] propertyArray = propertyStr.split(":");
        if (propertyArray.length != 2) return false;
        set(propertyArray[0].trim(), propertyArray[1].trim());
        return true;
    }

    /**
     * @param propertiesStr
     * @return <tt>true</tt> if setData all successfully
     */
    public boolean loadAll(String[] propertiesStr) {
        boolean b = true;
        for (String propertyStr : propertiesStr) {
            b = load(propertyStr) && b;
        }
        return b;
    }

    public boolean loadAll(String propertiesStr) {
        return loadAll(propertiesStr.split("\n"));
    }

    /**
     * not recommended
     *
     * @param name
     * @return
     */
    public boolean remove(String name) {
        if (properties.containsKey(name)) {
            properties.remove(name);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";

        for (String s : getPropertiesStr()) {
            result += s + "\n";
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
        getInstance().set("a", "keyi");
        System.out.println(getInstance());
    }
}