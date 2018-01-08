package org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;
import org.jfugue.player.Player;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.PatternParameters;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternStorage;
import org.randoom.setlx.SetlXMusic.Patterns.iSetlXPatternStorage;
import org.randoom.setlx.exceptions.SetlException;

import java.util.HashMap;
import java.util.List;

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
        patternStorage = new SetlXPatternStorage(); //Creates a new Pattern Storage for future music patterns
        player = new Player();
    }

    @Override
    public void addPattern(String name, Pattern pattern) throws NullArgumentsException {
        patternStorage.addPattern(name, pattern);
    }

    @Override
    public void addToPattern(String patternName, String notePattern) throws PatternNotFoundException {
        patternStorage.getPattern(patternName).add(notePattern);
    }
    @Override
    public void addToPattern(String patternName, String notePattern, int repetitions) throws PatternNotFoundException {
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
    public void removePattern(String patternName) throws PatternNotFoundException {
            patternStorage.deletePattern(patternName);
    }
    @Override
    public Pattern getPattern(String name) throws PatternNotFoundException {
    return patternStorage.getPattern(name);
    }


    @Override
    public void duplicatePattern(String sourceName, String newName) throws PatternNotFoundException, NullArgumentsException {
        Pattern copy  = new Pattern(); //We want to copy the object

        List<Token> tokenList = getPattern(sourceName).getTokens();
        for(int i = 0; i < tokenList.size();i++){ //iterates over all tokens of a pattern
                                            //A Tokenlist is parsed from the pattern string of the pattern
            if(i < 3&&tokenList.get(i).getType()!= Token.TokenType.NOTE){ //Up to the first 4 entrys it can store
                                                                //explicitly set properties via .setXX-method.
                                                                //In order to be able to set them in the copy
                                                                //we have to skip this preamble
                continue;
            }
            copy.add(tokenList.get(i));
        }
                                    //Unfortunately, the Pattern Class does not implement
                                    //the Clonable or Serializable interface making it impossible to (deep)
                                    //copy an instance. The trick is, that we just copy the information, that are stored in
                                    //a staccato string via the toString-method

        addPattern(newName, copy);
    }

    @Override
    public HashMap<String, Pattern> getAllPatterns() {
        return patternStorage.getAllPatterns();
    }
}
