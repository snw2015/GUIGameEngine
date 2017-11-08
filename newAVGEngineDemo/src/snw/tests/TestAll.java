package snw.tests;

import snw.text.structure.LengthList;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestAll {

    public static void main(String[] args) {
        LengthList<String> l1 = new LengthList<>();

        l1.append("aa", 3);

        l1.append("bb", 3);

        LengthList<String> l2 = new LengthList<>();

        l2.append("cc", 2);

        l2.append("dd", 1);

        LengthList<String> l3 = l1.add(l2);

        println(l3);
        //println(l3.subList(2));
        //println(l3.firstOver(5));
        //println(l3.subList(1, 3));
        //println(l3.indexOf("bb"));
        l3.insert("11", 1);
        println(l3);
        l3.insert("22", 10);
        println(l3);
        l3.insert("33", 4);
        println(l3);

        l3.insert("44", 3, 2);
        l3.insert("55", 0, 1);
        println(l3);

        l3.remove(4);
        println(l3);

        println(l3.subList(3));
        println(l3.subList(1, 5));

        println(l3.endMark());
    }

    public static void print(Object s) {
        System.out.print(s);
    }

    public static void println(Object s) {
        System.out.println(s);
    }

}
