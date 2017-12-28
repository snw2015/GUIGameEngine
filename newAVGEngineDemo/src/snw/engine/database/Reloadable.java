package snw.engine.database;

public interface Reloadable {
    String save();

    void reload(String info);
}