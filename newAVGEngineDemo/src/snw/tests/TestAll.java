package snw.tests;

import snw.engine.core.Engine;
import snw.engine.database.Reloadable;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestAll implements Reloadable {

    private String name;

    public TestAll() {
        System.out.println("new test all");
        name = "default";
    }

    public TestAll(String name) {
        System.out.println("new test all with name");
        this.name = name;
    }

    public static void main(String[] args) {
        int i = 0;
        System.out.println("i++ " + i++);
        System.out.println("++i " + ++i);
        System.out.println("true||i++>0 " + (true || i++ > 0));
        System.out.println("i " + i);
        System.out.println("false&&++i>0 " + (false && ++i > 0));
        System.out.println("i " + i);
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

    @Override
    public String save() {
        return name;
    }

    @Override
    public void reload(String info) {
        name = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test: name: " + name;
    }
}
