package snw.tests;

import snw.text.ExtendText;
import snw.text.structure.LengthList;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestAll {

    public static void main(String[] args) {
        ExtendText et = new ExtendText("abcd\\c{12,12,199}efg");
        et.insert(3, new ExtendText("3!\\f{Arial}Arial"));

        println(et);
    }

    public static void print(Object s) {
        System.out.print(s);
    }

    public static void println(Object s) {
        System.out.println(s);
    }

}
