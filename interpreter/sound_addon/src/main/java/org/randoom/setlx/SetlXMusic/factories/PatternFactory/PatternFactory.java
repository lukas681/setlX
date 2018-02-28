package org.randoom.setlx.SetlXMusic.factories.PatternFactory;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.factories.Exceptions.PatternValueOutOfRangeException;

public class PatternFactory implements iPatternFactory{

    private final int tempoLb = 0, tempoUb = 300; //TODO export constraints
    private final int voiceLb = 0, voiceUb = 16;
    private final int instrumentLb = 0, instrumentUb= 300;

    @Override
    public Pattern createPattern(String pattern, int instrument, int tempo, int voice) throws PatternValueOutOfRangeException {
        if(!checkConstraints(instrument, tempo, voice)){
            throw new PatternValueOutOfRangeException();
        }
        return new Pattern(pattern).setInstrument(instrument).setTempo(tempo).setVoice(voice);
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
     * @param instrument
     * @param tempo
     * @param voice
     * @return if they are allowed
     */
    private boolean checkConstraints(int instrument, int tempo, int voice){
        if(tempoLb<tempo&& tempo <=tempoUb
                &&voiceLb<=voice && voice <=voiceUb
                &&instrumentLb<=instrument&& instrument<=instrumentUb)
            return true; //Todo global configuration class
        return false;
    }
}
