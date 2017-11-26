package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

import jm.audio.Instrument;
import jm.audio.RTMixer;
import jm.music.data.Note;
import jm.music.rt.RTLine;
import org.randoom.setlx.MusicPlayer.Factories.NoteFacImpl;
import org.randoom.setlx.MusicPlayer.Factories.NoteFacIntf;
import org.randoom.setlx.MusicPlayer.RealTimeProcessing.instrumentSets.RTPluckInst;

public class SetlXRTMixerImpl extends RTMixer implements SetlXRTMixerIntf {

    //Sample Rate in hz
    private final int sampleRate = 44100;

    public SetlXRTMixerImpl() {
        super(null); //not tested.
    }

    public SetlXRTMixerImpl(SetlXRTLineIntf[] rtlines) {
        super((RTLine[]) (SetlXRTLineImpl[]) rtlines);
    }

    public SetlXRTMixerImpl(SetlXRTLineIntf rtline) {
        this(new SetlXRTLineImpl[]{(SetlXRTLineImpl) rtline});
    }

    @Override
    public void addNewSetlXRTLines(SetlXRTLineIntf[] lines) {

    }

    /**
     * Overrides @addNewSetlXRTLine for adding just one single @{@link RTLine}
     *
     * @param line
     */
    public void addNewSetlXRTLines(SetlXRTLineIntf line) {
        SetlXRTLineIntf[] lines = {line};
        this.addNewSetlXRTLines(lines);
    }


    public static void main(String[] args) throws InterruptedException {
        //int sampleRate = 44100;
        Instrument inst = new RTPluckInst(44100);
        Instrument[] insts = new Instrument[]{inst};


        SetlXRTLineIntf t = new SetlXRTLineImpl(insts);

        final SetlXRTMixerIntf rtm = new SetlXRTMixerImpl(t);

        System.out.println("GO");

        NoteFacIntf n = new NoteFacImpl();

        rtm.begin();
        t.addNote(new Note((int) (Math.random() * 60 + 30), 10,
                (int) (Math.random() * 100 + 27)));
        Thread.sleep(5000);

        System.out.println(System.currentTimeMillis());
        t.addNote(new Note((int) (Math.random() * 60 + 30), 10,
                (int) (Math.random() * 100 + 27)));
        System.out.println(System.currentTimeMillis());
        Thread.sleep(10000);
        rtm.stop();

    }

}
