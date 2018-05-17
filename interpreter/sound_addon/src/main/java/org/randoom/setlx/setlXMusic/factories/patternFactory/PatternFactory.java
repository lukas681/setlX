package org.randoom.setlx.setlXMusic.factories.patternFactory;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.setlXMusic.factories.exceptions.PatternValueOutOfRangeException;

public class PatternFactory implements iPatternFactory {


    private final int tempoLb = -1, tempoUb = 300; //TODO export constraints
    private final int voiceLb = -1, voiceUb = 16;
    private final int instrumentLb = -1, instrumentUb = 300;

    @Override
    public Pattern createPattern(String pattern, int instrument, int tempo, int voice) throws PatternValueOutOfRangeException {
        if (!checkConstraints(instrument, tempo, voice)) {
            throw new PatternValueOutOfRangeException();
        }
        Pattern patt = new Pattern(pattern);
            if(instrument != -1){
                patt.setInstrument(instrument);
            }
            if(tempo != -1){
                patt.setTempo(tempo);
            }
            if(voice != -1){
                patt.setVoice(voice);
            }
        return patt;
    }

    @Override
    public Pattern createPattern(String pattern, int instrument, int tempo) throws PatternValueOutOfRangeException {
        return createPattern(pattern, instrument, tempo, 1);
    }

    @Override
    public Pattern createPattern(String pattern, int instrument) throws PatternValueOutOfRangeException {
        return createPattern(pattern, instrument, 120); //If one of the properties is modified, we will delegate up
    }

    @Override
    public Pattern createPattern(String pattern) {
        return new Pattern(pattern);
    }

    /**
     * Checks, weather given pattern properties are allowed.
     *
     * @param instrument
     * @param tempo
     * @param voice
     * @return if they are allowed
     */
    private boolean checkConstraints(int instrument, int tempo, int voice) {
        return tempoLb <= tempo && tempo <= tempoUb
                && voiceLb <= voice && voice <= voiceUb
                && instrumentLb <= instrument && instrument <= instrumentUb;
    }
}
