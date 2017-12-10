package snw.tests;

import java.util.ArrayList;

public class TestAll {

    public static void main(String[] args) {
        ArrayList<String> t= new ArrayList<>();
        t.add("1");
        t.add("3");
        println(t);
        t.remove(1);
        println(t);
    }


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
