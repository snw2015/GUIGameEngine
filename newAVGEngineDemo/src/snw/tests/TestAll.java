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

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        File file = new File("file/audio/lock.wav");

        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(file);
        AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(file);
        Clip clip1 = AudioSystem.getClip();
        Clip clip2 = AudioSystem.getClip();

        clip1.open(audioInputStream1);
        clip2.open(audioInputStream2);

        FloatControl control = (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(-30f);
        clip1.start();

        Thread.sleep(5000);

        control = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(1f);
        clip2.start();

        Thread.sleep(5000);
        clip2.stop();
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
