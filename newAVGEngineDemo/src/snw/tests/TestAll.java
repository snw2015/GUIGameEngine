package snw.tests;

import java.util.ArrayList;

public class TestAll {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<String> as = new ArrayList<>();

        as.add("12");
        as.add(1,"123");

        for (String s : as) {
            println(s);
        }
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

}
