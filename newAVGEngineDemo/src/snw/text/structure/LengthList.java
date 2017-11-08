package snw.text.structure;

import java.util.ArrayList;

public class LengthList<S> implements Cloneable {
    private ArrayList<S> contents;
    private ArrayList<Integer> indices;

    public LengthList() {
        contents = new ArrayList<>();
        indices = new ArrayList<>();
    }

    public LengthList(LengthList src) {
        this.contents = src.contents;
        this.indices = src.indices;
    }


    public void append(S object, int diff) {
        int endMark = endMark();

        this.contents.add(object);
        this.indices.add(endMark + diff);
    }

    public void append(LengthList<S> list) {
        int endMark = endMark();
        int begin = length(), end = begin + list.length();

        this.contents.addAll(list.contents);

        this.indices.addAll(list.indices);
        for (int i = begin; i < end; i++) {
            indices.set(i, indices.get(i) + endMark);
        }
    }


    public LengthList<S> add(S object, int diff) {
        LengthList<S> sumList = new LengthList<>(this);

        sumList.append(object, diff);

        return sumList;
    }

    public LengthList<S> add(LengthList<S> list) {
        LengthList<S> sumList = new LengthList<>(this);

        sumList.append(list);

        return sumList;
    }

    public void insert(S object, int index, int diff) {
        LengthList<S> laterList = subList(index);
        removeAll(index);

        append(object, diff);
        append(laterList);
    }

    public void insert(S object, int dis) {
        if (dis < 0) {
            //TODO throw error
            return;
        }

        if (length() == 0) {
            append(object, dis);
        } else {
            int index = firstOver(dis);

            index = index < 0 ? length() : index;

            ArrayList<Integer> laterIndices = new ArrayList<>(indices.subList(index, length()));
            indices = new ArrayList<>(indices.subList(0, index));
            indices.add(dis);
            indices.addAll(laterIndices);
            ArrayList<S> laterContents = new ArrayList<>(contents.subList(index, length()));
            contents = new ArrayList<>(contents.subList(0, index));
            contents.add(object);
            contents.addAll(laterContents);
        }
    }

    public void insert(LengthList<S> list, int index) {
        LengthList<S> laterList = subList(index);
        removeAll(index);

        append(list);
        append(laterList);
    }

    public void remove(int index) {
        removeAll(index, index + 1);
    }

    public void removeAll(int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            LengthList<S> laterList = subList(endIndex);
            removeAll(beginIndex);

            append(laterList);
        }
    }

    public void removeAllBy(int endIndex) {
        if (endIndex <= 0) return;
        if (endIndex > length()) {
            this.contents = new ArrayList<>();
            this.indices = new ArrayList<>();
            return;
        }

        int delta = -indices.get(endIndex - 1);
        indices = new ArrayList<>(indices.subList(endIndex, length()));
        for (int i = 0; i < indices.size(); i++) {
            indices.set(i, indices.get(i) + delta);
        }

        contents = new ArrayList<>(contents.subList(endIndex, length()));
    }

    public void removeAll(int beginIndex) {
        beginIndex = trimToBound(beginIndex);

        indices = new ArrayList<>(indices.subList(0, beginIndex));
        contents = new ArrayList<>(contents.subList(0, beginIndex));
    }

    public LengthList<S> subList(int beginIndex, int endIndex) {
        LengthList<S> sub = new LengthList<>();
        if (beginIndex < endIndex) {
            sub = this.subList(beginIndex);
            sub.removeAll(endIndex);
        }
        return sub;
    }

    public LengthList<S> subList(int beginIndex) {
        LengthList<S> sub = new LengthList<>(this);
        sub.removeAllBy(beginIndex);

        return sub;
    }

    public int length() {
        return contents.size();
    }

    public int endMark() {
        if (length() > 0) {
            return (indices.get(length() - 1));
        }
        return (0);
    }

    public ArrayList<S> getContents() {
        return contents;
    }

    public ArrayList<Integer> getIndices() {
        return indices;
    }

    public S contentsAt(int index) {
        if (!outOfBound(index)) {
            return contents.get(index);
        }
        return null;
    }

    public int indexAt(int index) {
        if (!outOfBound(index)) {
            return indices.get(index);
        }
        return -1;
    }

    public int indexOf(S obj) {
        for (int i = 0; i < length(); i++) {
            if (obj == contents.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public int firstOver(int dis) {
        //TODO use divide and conquer method for efficiency
        if (dis < 0) return 0;
        for (int i = 0; i < length(); i++) {
            if (dis < indices.get(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean outOfBound(int index) {
        return (index >= 0 && index < length());
    }

    private int trimToBound(int index) {
        if (index < 0) return 0;
        if (index >= length()) return length() - 1;
        return index;
    }

    @Override
    public LengthList<S> clone() {
        return new LengthList<>(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof LengthList) {
            return (this.contents.equals(((LengthList) other).contents) &&
                    this.indices.equals(((LengthList) other).indices));
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "LengthList: {\n";
        for (int i = 0; i < length(); i++) {
            s += "\t" + contents.get(i).toString() + " , " + indices.get(i).toString() + "\n";
        }
        s += "}\n";

        return (s);
    }
}
