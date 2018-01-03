package snw.engine.audio;

import snw.engine.core.Engine;
import snw.engine.database.AudioData;

import javax.sound.sampled.*;
import java.io.File;
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
    private ArrayList<String> BGMList = new ArrayList<>();

    private class BGMLineListener implements LineListener {
        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP)
                endBGM();
        }
    }

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

    public void storeBGM(String name) {
        AudioData audio = Engine.attainAudio(name);
        if (!BGMList.contains(name)) {
            audio.getClip().addLineListener(new BGMLineListener());
            BGMList.add(name);
        }
    }

    public void storeBGM(String name, int loopStart, int loopEnd) {
        storeBGM(name);
        Engine.getClip(name).setLoopPoints(loopStart, loopEnd);
    }

    public void releaseBGM(String name) {
        if (BGMList.contains(name)) {
            Engine.releaseAudio(name);
            BGMList.remove(name);
        }
    }

    public void playBGM(String name) {
        playBGM(name, -1);
    }

    public void playBGM(String name, int loopTime) {
        if (loopTime == 0 || !BGMList.contains(name)) return;
        stopBGM();

        Clip clipBGM = Engine.getClip(name);
        setClipVol(clipBGM, BGMVol * masterVol);

        currentBGM = name;

        clipBGM.setFramePosition(0);

        if (loopTime > 0) {
            clipBGM.loop(loopTime - 1);
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
        endBGM();
    }

    private void endBGM() {
        currentBGM = null;
    }

    public void fadeInBGM(String name) {
        fadeInBGM(name, 1.0f);
    }

    public void fadeInBGM(String name, float speed) {
        if (speed <= 0) return;
        stopBGM();

        Clip clipBGM = Engine.getClip(name);
        setClipVol(clipBGM, BGMVol * masterVol);
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
                System.out.println(i);
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

        Clip clipSE = Engine.getNewClip(name);
        //Edit: I found that you should open a new File otherwise you can't play the same sound simultaneously
        // (at lease can't play it with a short interval), sigh
        //TODO due to the reason above, this class and the AudioBufferData should be restructured

        setClipVol(clipSE, SEVol * masterVol);

        clipSE.setFramePosition(0);

        if (loopTime > 0) {
            clipSE.loop(loopTime - 1);
        } else {
            clipSE.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void playSEQuickly(String name) {
        Clip clipSE = null;
        try {
            clipSE = AudioSystem.getClip();
            clipSE.open(AudioSystem.getAudioInputStream(new File(Engine.getProperty("sounds_path") + name + ".wav")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setClipVol(clipSE, SEVol * masterVol);

        clipSE.setFramePosition(0);

        clipSE.start();
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
}