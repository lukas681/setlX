package org.randoom.setlx.SetlXMusic.SetlXPatternManager;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.randoom.setlx.Exceptions.NullArgumentsException;
import org.randoom.setlx.Exceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.PatternParameters;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXSetlXPatternStorage;
import org.randoom.setlx.SetlXMusic.Patterns.iSetlXPatternStorage;
import org.randoom.setlx.exceptions.SetlException;

import java.util.HashMap;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXPatternManager implements iSetlXPatternManager {

    iSetlXPatternStorage patternStorage;
    Player player;

    /**
     * Default constructor for {@link SetlXPatternManager}. Creates a new P
     */
    public SetlXPatternManager(){
        patternStorage = new SetlXSetlXPatternStorage(); //Creates a new Pattern Storage for future music patterns
        player = new Player();
    }

    @Override
    public void addPattern(String name, Pattern pattern) throws NullArgumentsException {
        patternStorage.addPattern(name, pattern);
    }

    @Override
    public void addToPattern(String patternName, String notePattern) {
        patternStorage.getPattern(patternName).add(notePattern);
    }
    @Override
    public void addToPattern(String patternName, String notePattern, int repetitions) {
        patternStorage.getPattern(patternName).add(notePattern, repetitions);
    }

    @Override
    public void modifyPatternProperty(String patternName, PatternParameters param, int value) throws SetlException{
        if(!patternStorage.checkExisting(patternName)){
            throw new PatternNotFoundException();
        }
        switch(param){
            case TEMPO:
                patternStorage.getPattern(patternName).setTempo(value);
                break;
            case INSTRUMENT:
                patternStorage.getPattern(patternName).setInstrument(value);
                break;
            case VOICE:
                patternStorage.getPattern(patternName).setVoice(value);
                break;
        }
    }

    @Override
    public void removePattern(String patternName) {
        if(patternStorage.checkExisting(patternName)){
            patternStorage.deletePattern(patternName);
        }
    }
    @Override
    public Pattern getPattern(String name){
    return patternStorage.getPattern(name);
    }

    @Override
    public HashMap<String, Pattern> getAllPatterns() {
        return patternStorage.getAllPatterns();
    }
}
