package snw.engine.database;

import snw.tests.TestAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EngineData {
    private static EngineData INSTANCE;

    private final HashMap<String, Object> data = new HashMap<>();

    private EngineData() {
    }

    public static EngineData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EngineData();
        }
        return INSTANCE;
    }

    public HashMap<String, Object> getDataMap() {
        return data;
    }

    public void clear() {
        removeAll();
    }

    public String[] getDataMapList() {
        ArrayList<String> dataList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            dataList.add(entry.getKey() + ": " + entry.getValue());
        }
        String[] a = new String[0];
        return dataList.toArray(a);
    }

    public String getDataMapStr() {
        String infoList = "";
        for (String info : getDataMapList()) {
            infoList += info + "\n";
        }
        return infoList;
    }

    public Object get(String name) {
        return data.get(name);
    }

    public Object[] get(String[] names) {
        Object[] dataList = new Object[names.length];
        for (int i = 0; i < names.length; i++) {
            dataList[i] = get(names[i]);
        }
        return dataList;
    }


    public boolean set(String name, Object value) {
        boolean b = data.containsKey(name);
        data.put(name, value);
        return b;
    }


    public boolean set(String name, long value) {
        return set(name, new Long(value));
    }

    public boolean set(String name, int value) {
        return set(name, new Integer(value));
    }

    public boolean set(String name, short value) {
        return set(name, new Short(value));
    }

    public boolean set(String name, char value) {
        return set(name, new Character(value));
    }

    public boolean set(String name, byte value) {
        return set(name, new Byte(value));
    }

    public boolean set(String name, double value) {
        return set(name, new Double(value));
    }

    public boolean set(String name, float value) {
        return set(name, new Float(value));
    }

    public boolean set(String name, boolean value) {
        return set(name, new Boolean(value));
    }


    public boolean set(String[] names, Object[] values) {
        boolean b = true;
        for (int i = 0; i < names.length; i++) {
            b = set(names[i], values[i]) && b;
        }
        return b;
    }


    public boolean load(String dataInfo) {
        String[] infoArray = dataInfo.split(":", 2);
        if (infoArray.length < 2) return false;
        String name = infoArray[0].trim();
        String valueStr = infoArray[1].trim();
        //TODO

        DataType type = DataType.getType(valueStr);
        switch (type) {
            case LONG:
                set(name, DataType.parseLong(valueStr));
                return true;
            case INT:
                set(name, DataType.parseInt(valueStr));
                return true;
            case SHORT:
                set(name, DataType.parseShort(valueStr));
                return true;
            case CHAR:
                set(name, DataType.parseChar(valueStr));
                return true;
            case BYTE:
                set(name, DataType.parseByte(valueStr));
                return true;
            case DOUBLE:
                set(name, DataType.parseDouble(valueStr));
                return true;
            case FLOAT:
                set(name, DataType.parseFloat(valueStr));
                return true;
            case BOOLEAN:
                set(name, DataType.parseBoolean(valueStr));
                return true;
            case STRING:
                set(name, DataType.parseString(valueStr));
                return true;
            case OBJECT:
                set(name, DataType.parseObject(valueStr));
                return true;
        }
        return false;
    }

    public boolean loadList(String[] dataInfoList) {
        boolean b = true;
        for (String info : dataInfoList) {
            b = load(info) && b;
        }
        return b;
    }

    public boolean loadDataListStr(String dataInfoList) {
        return loadList(dataInfoList.split("\n"));
    }


    public String getDataInfo(String name) {
        Object obj = data.get(name);
        if (obj instanceof Long) {
            return name + ": " + DataType.saveLong((Long) obj);
        }
        if (obj instanceof Integer) {
            return name + ": " + DataType.saveInt((Integer) obj);
        }
        if (obj instanceof Short) {
            return name + ": " + DataType.saveShort((Short) obj);
        }
        if (obj instanceof Character) {
            return name + ": " + DataType.saveChar((Character) obj);
        }
        if (obj instanceof Byte) {
            return name + ": " + DataType.saveByte((Byte) obj);
        }
        if (obj instanceof Double) {
            return name + ": " + DataType.saveDouble((Double) obj);
        }
        if (obj instanceof Float) {
            return name + ": " + DataType.saveFloat((Float) obj);
        }
        if (obj instanceof Boolean) {
            return name + ": " + DataType.saveBoolean((Boolean) obj);
        }
        if (obj instanceof String) {
            return name + ": " + DataType.saveString((String) obj);
        }
        if (obj instanceof Reloadable) {
            return name + ": " + DataType.saveObject((Reloadable) obj);
        }
        return name + ": " + obj;
    }

    public String[] getDataInfo(String[] names) {
        ArrayList<String> infoList = new ArrayList<>();
        for (String name : names) {
            infoList.add(getDataInfo(name));
        }
        String[] a = new String[0];
        return infoList.toArray(a);
    }

    public String getDataInfoStr(String[] names) {
        String infoList = "";
        for (String info : getDataInfo(names)) {
            infoList += info + "\n";
        }
        return infoList;
    }

    public String[] getAllDataInfo() {
        ArrayList<String> infoList = new ArrayList<>();
        for (String name : data.keySet()) {
            infoList.add(getDataInfo(name));
        }
        String[] a = new String[0];
        return infoList.toArray(a);
    }

    public String getAllDataInfoStr() {
        String infoList = "";
        for (String info : getAllDataInfo()) {
            infoList += info + "\n";
        }
        return infoList;
    }

    public boolean remove(String name) {
        if (data.containsKey(name)) {
            data.remove(name);
            return true;
        }
        return false;
    }

    public void removeAll() {
        data.clear();
    }

    @Override
    public String toString() {
        return getDataMapStr();
    }

    public static void main(String[] args) {
        EngineData ed = getInstance();

        ed.set("data1", 1);
        ed.set("data2", true);
        ed.set("data3", "12");
        ed.set("dataObj", new TestAll());

        System.out.println(ed);
        System.out.println(ed.getAllDataInfoStr());

        String info = ed.getAllDataInfoStr();

        ed.removeAll();

        System.out.println(ed);

        ed.loadDataListStr(info);

        System.out.println(ed);
    }

}
