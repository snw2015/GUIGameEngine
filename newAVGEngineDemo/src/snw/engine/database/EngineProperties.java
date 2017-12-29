package snw.engine.database;

import snw.engine.core.Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EngineProperties {
    private final static EngineProperties INSTANCE = new EngineProperties();

    private final HashMap<String, String> properties;

    private EngineProperties() {
        properties = new HashMap<>();

        clear();

        readPropertiesFile();
    }



    public static EngineProperties getInstance() {
        return INSTANCE;
    }

    public void clear(){
        properties.clear();
        loadAllDefault(new String[]{
                "!properties_path: properties.txt",
                "!images_path    : file/image/",
                "!sounds_path    : file/audio/",
                "!data_path      : file/data/",
                "!data_form      : .txt",
                "!user_data_path : file/data/",
                "!user_data_form : .sav"});
    }

    public String[] getAllProperties() {
        ArrayList<String> listProperties = new ArrayList<>();

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            if (!entry.getKey().startsWith("!"))
                listProperties.add(entry.getKey() + ": " + entry.getValue());
        }

        String[] a = new String[0];
        return listProperties.toArray(a);
    }

    public String getAllPropertiesStr() {
        String propertiesList = "";
        for (String property : getAllProperties()) {
            propertiesList += property + "\n";
        }
        return propertiesList;
    }

    public String[] getDefaultProperties() {
        ArrayList<String> listProperties = new ArrayList<>();

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            if (entry.getKey().startsWith("!"))
                listProperties.add(entry.getKey().substring(1) + ": " + entry.getValue());
        }

        String[] a = new String[0];
        return listProperties.toArray(a);
    }

    public String getDefaultPropertiesStr() {
        String propertiesList = "";
        for (String property : getDefaultProperties()) {
            propertiesList += property + "\n";
        }
        return propertiesList;
    }

    public String get(String name) {
        String value = properties.get(name);
        if (value == null || value.equals("")) {
            return getDefault(name);
        }
        return value;
    }

    public String getDefault(String name) {
        return properties.get("!" + name);
    }

    /**
     * @param name
     * @param value
     * @return <tt>true<tt/> if overwrote (setData)
     */
    public boolean set(String name, String value) {
        if (name.startsWith("!")) return false;
        boolean b = properties.containsKey(name);
        properties.put(name, value);
        return b;
    }

    /**
     * @param propertyStr
     * @return <tt>true</tt> if setData successfully
     */
    public boolean load(String propertyStr) {
        String[] propertyArray = propertyStr.split(":", 2);
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

    private boolean setDefault(String name, String value) {
        boolean b = properties.containsKey(name);
        properties.put(name, value);
        return b;
    }

    private boolean loadDefault(String propertyStr) {
        String[] propertyArray = propertyStr.split(":", 2);
        if (propertyArray.length != 2) return false;
        setDefault(propertyArray[0].trim(), propertyArray[1].trim());
        return true;
    }

    private boolean loadAllDefault(String[] propertiesStr) {
        boolean b = true;
        for (String propertyStr : propertiesStr) {
            b = loadDefault(propertyStr) && b;
        }
        return b;
    }

    private boolean loadAllDefault(String propertiesStr) {
        return loadAllDefault(propertiesStr.split("\n"));
    }

    /**
     * not recommended
     * edit: now use it whatever. can't remove a default property entry
     *
     * @param name
     * @return
     */
    public boolean remove(String name) {
        if (name.startsWith("!")) {
            //TODO throw error
            return false;
        }
        if (properties.containsKey(name)) {
            properties.remove(name);
            return true;
        }
        return false;
    }

    public void readPropertiesFile() {
        readPropertiesFile(get("properties_path"));
    }

    public void readPropertiesFile(String fileName) {
        String propertiesStr = Engine.readFileStr(fileName);
        if (propertiesStr != null)
            loadAll(propertiesStr);
    }

    public void savePropertiesFile() {
        savePropertiesFile(get("properties_path"));
    }

    public void savePropertiesFile(String fileName) {
        String propertiesStr = getAllPropertiesStr();
        Engine.writeFile(fileName, propertiesStr);
    }

    @Override
    public String toString() {
        return "Properties: \n" + getAllPropertiesStr() + "\nDefault Properties: \n" + getDefaultPropertiesStr();
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
        getInstance().load("a:a");
        getInstance().savePropertiesFile();
    }
}