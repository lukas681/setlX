package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

import jm.audio.Instrument;
import jm.audio.RTMixer;
import jm.music.data.Note;
import jm.music.rt.RTLine;
import org.randoom.setlx.MusicPlayer.Factories.NoteFacImpl;
import org.randoom.setlx.MusicPlayer.Factories.NoteFacIntf;
import org.randoom.setlx.MusicPlayer.RealTimeProcessing.instrumentSets.RTPluckInst;

import java.util.HashSet;

public class SetlXRTMixerImpl extends RTMixer implements ISetlXRTMixer {

    //Sample Rate in hz
    private final int sampleRate = 44100;
    HashSet<SetlXRTLine> lines = new HashSet<>();

    public SetlXRTMixerImpl() {
        super(null); //not tested.
    }

    public SetlXRTMixerImpl(SetlXRTLine[] rtlines) {
        super( (SetlXRTLine[]) rtlines);
        addLinesToList(rtlines);
    }

    public SetlXRTMixerImpl(SetlXRTLine rtline) {
        this(new SetlXRTLine[]{(SetlXRTLine) rtline});
    }

    /**
     * Adds an array of lines to this mixer
     * @param lines
     */
    @Override
    public void addNewSetlXRTLines(SetlXRTLine[] lines) {
        System.out.println(lines);
        this.addLines(lines) ;
        addLinesToList(lines);
    }

    /**
     * Overrides @addNewSetlXRTLine for adding just one single @{@link RTLine}
     *
     * @param line
     */
    public void addNewSetlXRTLines(SetlXRTLine line) {
        SetlXRTLine[] lines = {line};
        this.addNewSetlXRTLines(lines);
    }

    /**
     * Allows to add SetlX lines to the local storage
     * @param rtlines
     */
    private void addLinesToList(SetlXRTLine[] rtlines){
        System.out.println(rtlines);
        for(SetlXRTLine line: rtlines){
            lines.add(line);
        }
}

    public static void main(String[] args) throws InterruptedException {
        //int sampleRate = 44100;
        Instrument inst = new RTPluckInst(44100);
        Instrument[] insts = new Instrument[]{inst};

        Instrument inst1 = new RTPluckInst(44100);
        Instrument[] insts1 = new Instrument[]{inst1};


        SetlXRTLine t = new SetlXRTLine(insts);
        SetlXRTLine y = new SetlXRTLine(insts1);

        final SetlXRTMixerImpl rtm = new SetlXRTMixerImpl(t);
        rtm.addNewSetlXRTLines(y);

        System.out.println("GO");

        NoteFacIntf n = new NoteFacImpl();

        rtm.begin();

        t.addNote(new Note((int) (Math.random() * 60 + 30), 10,
                (int) (Math.random() * 100 + 27)));
y.addNote(new Note((int) (Math.random() * 60 + 30), 10,
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
