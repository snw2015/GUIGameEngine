package snw.tests;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;

public class TestAll {

    public static void main(String[] args) {
        AffineTransform transform = AffineTransform.getTranslateInstance(-20, -20);
        double[] point = new double[2];
        transform.transform(new double[]{10, 10}, 0, point, 0, 1);
        println(Arrays.toString(point));
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
