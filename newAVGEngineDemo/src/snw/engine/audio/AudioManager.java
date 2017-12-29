package snw.engine.audio;

import org.omg.CORBA.MARSHAL;
import snw.engine.core.Engine;

import javax.sound.sampled.*;
import java.util.ArrayList;

//Need restructured to allow multi-bgm
//  and individual volume control
//  and set loop points
public class AudioManager {
    private final static AudioManager INSTANCE = new AudioManager();

    public static final float MIN_GAIN = -30;

    private float BGMVol = 1;
    private float SEVol = 1;
    private float masterVol = 1;
    private String currentBGM = null;

    public static AudioManager getInstance() {
        return INSTANCE;
    }

    private AudioManager() {
    }

    public float getBGMVol() {
        return BGMVol;
    }

    public void setBGMVol(float BGMVol) {
        this.BGMVol = BGMVol;
    }

    public float getSEVol() {
        return SEVol;
    }

    public void setSEVol(float SEVol) {
        this.SEVol = SEVol;
    }

    public float getMasterVol() {
        return masterVol;
    }

    public void setMasterVol(float masterVol) {
        this.masterVol = masterVol;
    }

    public String getCurrentBGM() {
        return currentBGM;
    }

    public void playBGM(String name) {
        playBGM(name, -1);
    }

    public void playBGM(String name, int loopTime) {
        if (loopTime == 0) return;
        stopBGM();

        Clip clipBGM = Engine.getClip(name);
        setClipVol(clipBGM, BGMVol*masterVol);

        clipBGM.addLineListener((LineEvent event) -> {
            if (event.getType() == LineEvent.Type.STOP)
                currentBGM = null;
        });
        currentBGM = name;

        clipBGM.setFramePosition(0);

        if (loopTime > 0) {
            clipBGM.loop(loopTime);
        } else {
            clipBGM.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopBGM() {
        if (currentBGM != null)
            stopBGM(currentBGM);
    }

    public void stopBGM(String name) {
        Engine.getClip(name).stop();
    }

    public void fadeInBGM(String name) {
        fadeInBGM(name, 1.0f);
    }

    public void fadeInBGM(String name, float speed) {
        if (speed <= 0) return;
        stopBGM();

        Clip clipBGM = Engine.getClip(name);
        setClipVol(clipBGM, BGMVol*masterVol);
        FloatControl control = (FloatControl) clipBGM.getControl(FloatControl.Type.MASTER_GAIN);

        Thread threadControl = new Thread(() -> {
            float max = control.getValue();

            for (float i = MIN_GAIN; i < max; i += speed) {
                control.setValue(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            control.setValue(max);
        });


        threadControl.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentBGM = name;
        clipBGM.setFramePosition(0);
        clipBGM.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void fadeOutBGM() {
        fadeOutBGM(currentBGM);
    }

    public void fadeOutBGM(String name) {
        fadeOutBGM(name, 1.0f);
    }

    public void fadeOutBGM(float speed) {
        fadeOutBGM(currentBGM, speed);
    }

    public void fadeOutBGM(String name, float speed) {
        if (speed <= 0) return;
        Clip clipBGM = Engine.getClip(name);
        FloatControl control = (FloatControl) clipBGM.getControl(FloatControl.Type.MASTER_GAIN);

        Thread threadControl = new Thread(() -> {
            for (float i = control.getValue(); i > MIN_GAIN; i -= speed) {
                control.setValue(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            control.setValue(MIN_GAIN);
            clipBGM.stop();
        });

        currentBGM = name;
        threadControl.start();
    }

    public void playSE(String name) {
        playSE(name, 1);
    }

    public void playSE(String name, int loopTime) {
        if (loopTime == 0) return;

        Clip clipSE = Engine.getClip(name);
        setClipVol(clipSE, SEVol*masterVol);

        clipSE.setFramePosition(0);

        if (loopTime > 0) {
            clipSE.loop(loopTime);
        } else {
            clipSE.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopSE(String name) {
        Engine.getClip(name).stop();
    }

    private void setClipVol(Clip clip, float vol) {
        if (vol <= 0) {
            BooleanControl control = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            control.setValue(true);
        } else {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume2gain(vol));
        }
    }

    public static float volume2gain(float volume) {
        return (float) ((Math.exp(volume) - 1) / (Math.E - 1) * (1 - MIN_GAIN) + MIN_GAIN);
    }

    public static float gain2volume(float gain) {
        return (float) Math.log((gain - MIN_GAIN) / (1 - MIN_GAIN) * (Math.E - 1) + 1);
    }

    public static void main(String[] args) {
        Engine.storeAudio("lock");
        getInstance().setBGMVol(0.2f);
        getInstance().fadeInBGM("lock");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getInstance().playSE("lock2");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}