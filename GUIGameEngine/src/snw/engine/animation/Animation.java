<<<<<<< HEAD:GUIGameEngine/src/snw/engine/animation/Animation.java
package snw.engine.animation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Animation {
    private int counter = 0;
    private int frameCounter = 0;
    private int speed = 0;
    private boolean isEnded = false;
    private boolean isLoop = true;
    private ArrayList<AnimationData> datas = new ArrayList<AnimationData>();

    public Animation(int speed, BufferedReader animationFile) {
        // TODO

        this.speed = speed;
        String line = null;
        try {
            line = animationFile.readLine();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while (line != null) {
            datas.add(new AnimationData(line));
            try {
                line = animationFile.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public Animation(int speed, String name) {
        this.speed = speed;
        String line = null;

        BufferedReader animationFile = null;
        File file = new File("file/" + name+".anm");
        try {
            animationFile = new BufferedReader(
                    (new InputStreamReader(new FileInputStream(file))));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            line = animationFile.readLine();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while (line != null) {
            datas.add(new AnimationData(line));
            try {
                line = animationFile.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            animationFile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public AnimationData getFirstFrame() {
        return (datas.get(0));
    }

    public AnimationData getNextFrame() {
        if (speed > 0 && !isEnded) {
            int maxmumFrame = 100 / speed;
            if (counter++ > maxmumFrame) {
                counter = 0;
                if (++frameCounter >= datas.size()) {
                    if (isLoop) {
                        frameCounter = 0;
                    } else {
                        frameCounter--;
                        isEnded = true;
                    }
                }
            }
        }
        return (datas.get(frameCounter));
    }

    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    @Override
    public String toString() {
        return (datas.toString());
    }
}
=======
package snw.engine.animation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import snw.engine.script.ScriptLine;
import snw.file.FileDirectReader;

public class Animation {
    private int counter = 0;
    private int frameCounter = 0;
    private int speed = 0;
    private boolean isEnded = false;
    private boolean isLoop = true;
    private ArrayList<AnimationData> datas = new ArrayList<AnimationData>();

    public Animation(int speed, BufferedReader animationFile) {
        // TODO

        this.speed = speed;
        String line = null;
        try {
            line = animationFile.readLine();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while (line != null) {
            datas.add(new AnimationData(line));
            try {
                line = animationFile.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public Animation(int speed, String name) {
        this.speed = speed;
        String line = null;

        BufferedReader animationFile = null;
        File file = new File("file/" + name+".anm");
        try {
            animationFile = new BufferedReader(
                    (new InputStreamReader(new FileInputStream(file))));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            line = animationFile.readLine();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while (line != null) {
            datas.add(new AnimationData(line));
            try {
                line = animationFile.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            animationFile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public AnimationData getFirstFrame() {
        return (datas.get(0));
    }

    public AnimationData getNextFrame() {
        if (speed > 0 && !isEnded) {
            int maxmumFrame = 100 / speed;
            if (counter++ > maxmumFrame) {
                counter = 0;
                if (++frameCounter >= datas.size()) {
                    if (isLoop) {
                        frameCounter = 0;
                    } else {
                        frameCounter--;
                        isEnded = true;
                    }
                }
            }
        }
        return (datas.get(frameCounter));
    }

    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    @Override
    public String toString() {
        return (datas.toString());
    }
}
>>>>>>> parent of 5b3b56b... version 0.1.0 pure reduction:newAVGEngineDemo/src/snw/engine/animation/Animation.java
