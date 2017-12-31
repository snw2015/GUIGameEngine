package snw.tests;

import snw.engine.database.Reloadable;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
        System.out.println(Thread.currentThread());
        Thread t = new Thread(()->{
            System.out.println(Thread.currentThread());
        });
        t.start();
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
