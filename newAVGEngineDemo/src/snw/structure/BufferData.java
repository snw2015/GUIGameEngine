package snw.structure;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BufferData<D> {
    private final HashMap<String, D> bufferData = new HashMap<>();
    private final HashMap<String, Integer> referenceCounters = new HashMap<>();

    public boolean store(String name) {
        if (bufferData.containsKey(name)) {
            referenceCounters.put(name, referenceCounters.get(name) + 1);
            return true;
        } else {
            return false;
        }
    }

    public boolean store(String name, D data) {
        if (bufferData.containsKey(name)) {
            bufferData.put(name, data);
            referenceCounters.put(name, referenceCounters.get(name) + 1);
            return (false);
        } else {
            bufferData.put(name, data);
            referenceCounters.put(name, 0);
            return (true);
        }
    }

    public boolean release(String name) {
        if (bufferData.containsKey(name)) {
            if (referenceCounters.get(name) == 0) {
                disposeData(bufferData.get(name));
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

    public D attain(String name) {
        store(name);
        return get(name);
    }

    public void releaseAll() {
        for (String name : bufferData.keySet()) {
            release(name);
        }
    }

    public D get(String name) {
        return (bufferData.get(name));
    }

    public boolean hasStored(String name) {
        return bufferData.containsKey(name);
    }

    public int getReferenceCount(String name) {
        return referenceCounters.get(name);
    }

    protected void disposeData(D data) {
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<String, Integer> entry : referenceCounters.entrySet()) {
            s += entry.getKey() + " = " + entry.getValue() + ";\n";
        }
        return s;
    }
}
