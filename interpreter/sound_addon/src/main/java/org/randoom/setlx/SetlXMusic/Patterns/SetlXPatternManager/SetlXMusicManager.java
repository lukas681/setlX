package org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager;

import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.pattern.Token;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;
import org.jfugue.tools.GetPatternStats;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.KeyAlreadyInUseException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.ProducerNotFoundExceptions.ChordProgressionNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.ProducerNotFoundExceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.ProducerNotFoundExceptions.RhythmNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.ProducerNotSupportedException;
import org.randoom.setlx.SetlXMusic.Patterns.Storages.PatternParameters;
import org.randoom.setlx.SetlXMusic.Patterns.Storages.SetlXMusicStorage;
import org.randoom.setlx.SetlXMusic.Patterns.Storages.iSetlXMusicStorage;
import org.randoom.setlx.exceptions.SetlException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXMusicManager implements iSetlXMusicManager {

    private iSetlXMusicStorage<Pattern> patternStorage;
    private iSetlXMusicStorage<ChordProgression> chordProgressionStorage;
    private iSetlXMusicStorage<Rhythm> rythmStorage;

    private Player player;
    private GetPatternStats stats;

    /**
     * Default constructor for {@link SetlXMusicManager}. Creates a new Patternmanager
     */
    public SetlXMusicManager() {
        patternStorage = new SetlXMusicStorage<>(); //Creates a new Pattern Storage for future music patterns
        chordProgressionStorage = new SetlXMusicStorage<>();
        rythmStorage = new SetlXMusicStorage<>();

        player = new Player();
        stats = new GetPatternStats();
    }

    @Override
    public void add(String name, PatternProducer pattern) throws NullArgumentsException, ProducerNotSupportedException, KeyAlreadyInUseException {
        if(KeyInUse(name)){
            throw new KeyAlreadyInUseException();
        }
        if(pattern instanceof Pattern)
            patternStorage.addElement(name, (Pattern)pattern);
        else if (pattern instanceof Rhythm)
            rythmStorage.addElement(name, (Rhythm)pattern);
        else if(pattern instanceof ChordProgression)
            chordProgressionStorage.addElement(name, (ChordProgression)pattern);
        else
            throw new ProducerNotSupportedException();
    }

    @Override
    public void addToPattern(String patternName, String notePattern) throws PatternNotFoundException {
        patternStorage.getElement(patternName).add(notePattern);
    }

    @Override
    public void addToPattern(String patternName, String notePattern, int repetitions) throws PatternNotFoundException {
        patternStorage.getElement(patternName).add(notePattern, repetitions);
    }

    @Override
    public void modifyPatternProperty(String patternName, PatternParameters param, int value) throws SetlException {
        if (!patternStorage.checkExisting(patternName)) {
            throw new PatternNotFoundException();
        }
        switch (param) {
            case TEMPO:
                patternStorage.getElement(patternName).setTempo(value);
                break;
            case INSTRUMENT:
                patternStorage.getElement(patternName).setInstrument(value);
                break;
            case VOICE:
                patternStorage.getElement(patternName).setVoice(value);
                break;
        }
    }

    @Override
    public void removeElement(String key) throws PatternNotFoundException {
        switch(getStorageWhereKeyIsUsed(key)){
            case -1: throw new PatternNotFoundException();
            case 1:
                patternStorage.deleteElement(key);
                break;
            case 2:
                rythmStorage.deleteElement(key);
                break;
            case 3:
                chordProgressionStorage.deleteElement(key);
                break;
        }
    }

    @Override
    public Pattern getPattern(String name) throws PatternNotFoundException {
        return patternStorage.getElement(name);
    }


    @Override
    public void duplicatePattern(String sourceName, String newName) throws PatternNotFoundException, NullArgumentsException, KeyAlreadyInUseException {
        Pattern copy = new Pattern(); //We want to copy the object

        List<Token> tokenList = getPattern(sourceName).getTokens();
        for (int i = 0; i < tokenList.size(); i++) { //iterates over all tokens of a pattern
            //A Tokenlist is parsed from the pattern string of the pattern
            if (i < 3 && tokenList.get(i).getType() != Token.TokenType.NOTE) { //Up to the first 4 entrys it can store
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
        try {
            add(newName, copy);
        } catch (ProducerNotSupportedException e) { //We can be sure
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, GetPatternStats.Stats> getDetailPatternStats(String patternName) throws PatternNotFoundException {
        HashMap<String, GetPatternStats.Stats> statistics = new HashMap<>(); //TODO Do not parse it every time again for general and details stats
        stats.parsePattern(patternStorage.getElement(patternName), true);
        statistics.put("Harmonic", stats.getHarmonicStats());
        statistics.put("Duration", stats.getDurationStats());
        statistics.put("Interval", stats.getIntervalStats());
        statistics.put("Pitch", stats.getPitchStats());
        statistics.put("Rest", stats.getRestStats());
        return statistics;
    }

    @Override
    public int[] getGeneralPatternStats(String patternName) throws PatternNotFoundException {
        stats.parsePattern(patternStorage.getElement(patternName), true);
        return stats.getGeneralStats();
    }

    @Override
    public Rhythm getRhythm(String rhythmName) throws PatternNotFoundException{
        return rythmStorage.getElement(rhythmName);
    }

    @Override
    public ChordProgression getChordProgression(String progressionName) throws PatternNotFoundException {
        return chordProgressionStorage.getElement(progressionName);
    }

    @Override
    public HashMap<String, Rhythm> getAllRhythms() {
        return rythmStorage.getAllElements();
    }

    @Override
    public HashMap<String, ChordProgression> getAllChordProgressions() {
        return chordProgressionStorage.getAllElements();
    }

    /**
     * Returns the storage, where this key is used.
     * @param key
     * @return
     *         1: Pattern Storage
     *         2: Rhythm Storage
     *         3. ChordProgression Storage
     *         -1: if the pattern could not be found in any of the storages
     */
    @Override
    public int getStorageWhereKeyIsUsed(String key) throws PatternNotFoundException {
        if(patternStorage.checkExisting(key))
            return 1;
        if(rythmStorage.checkExisting(key))
            return 2;
        if(chordProgressionStorage.checkExisting(key))
            return 3;
        else return -1;
    }

    @Override
    public HashMap<String, Pattern> getAllPatterns() {
        return patternStorage.getAllElements();
    }

    /**
     * Checks, weather a given key is already in use in on of the storages.
     * @param key
     * @return
     */
    private boolean KeyInUse(String key){
        return patternStorage.checkExisting(key)
                ||rythmStorage.checkExisting(key)
                ||chordProgressionStorage.checkExisting(key);
    }
}
