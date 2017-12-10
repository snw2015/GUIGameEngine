package snw.engine.debug;

public class Log {
    public static void print(Object... s) {
        for (Object o : s) {
            System.out.print(o);
        }
    }

    public static void println(Object... s) {
        for (Object o : s) {
            System.out.println(o);
        }
    }
}