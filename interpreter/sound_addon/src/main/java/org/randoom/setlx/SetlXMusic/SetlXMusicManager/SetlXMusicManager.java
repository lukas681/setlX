package org.randoom.setlx.SetlXMusic.SetlXMusicManager;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.randoom.setlx.SetlXMusic.Patterns.PatternParameters;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXSetlXPatternStorage;
import org.randoom.setlx.SetlXMusic.Patterns.iSetlXPatternStorage;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXMusicManager implements iSetlXMusicManager{

    iSetlXPatternStorage patternStorage;
    Player player;

    public SetlXMusicManager(){
        patternStorage = new SetlXSetlXPatternStorage();
        player = new Player();
    }

    @Override
    public void addPattern(String name, Pattern pattern) {
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
    public void modifyPatternProperty(String patternName, PatternParameters param, int value) {
        if(!patternStorage.checkExisting(patternName)){
            return; //TODO Maybe method to boolean in order to cached not found exceptions? Or maybe impkement a item not found exception
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
}
