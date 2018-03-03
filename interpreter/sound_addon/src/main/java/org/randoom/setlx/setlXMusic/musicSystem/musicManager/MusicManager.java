package org.randoom.setlx.setlXMusic.musicSystem.musicManager;

import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.pattern.Token;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;
import org.jfugue.tools.GetPatternStats;
import org.randoom.setlx.setlXMusic.midiSystem.MidiManager;
import org.randoom.setlx.setlXMusic.midiSystem.iMidiManager;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.CanNotConvertException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.KeyAlreadyInUseException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.MidiExceptions.NotAPatternException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.MidiExceptions.SetlXIOException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.NullArgumentsException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotFoundExceptions.PatternNotFoundException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotSupportedException;
import org.randoom.setlx.setlXMusic.musicSystem.storages.MusicStorage;
import org.randoom.setlx.setlXMusic.musicSystem.storages.PatternParameters;
import org.randoom.setlx.setlXMusic.musicSystem.storages.StorageTypes;
import org.randoom.setlx.setlXMusic.musicSystem.storages.iMusicStorage;
import org.randoom.setlx.exceptions.SetlException;

import javax.sound.midi.InvalidMidiDataException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 28.12.2017.
 */
public class MusicManager implements iMusicManager {

    private iMusicStorage<Pattern> patternStorage;
    private iMusicStorage<ChordProgression> chordProgressionStorage;
    private iMusicStorage<Rhythm> rythmStorage;
    private iMidiManager midiManager;

    private Player player;
    private GetPatternStats stats;

    /**
     * Default constructor for {@link MusicManager}. Creates a new Patternmanager
     */
    public MusicManager() {
        patternStorage = new MusicStorage<>(); //Creates a new Pattern Storage for future music patterns
        chordProgressionStorage = new MusicStorage<>();
        rythmStorage = new MusicStorage<>();
        midiManager = new MidiManager();

        player = new Player();
        stats = new GetPatternStats();
    }

    @Override
    public void add(String name, PatternProducer pattern) throws NullArgumentsException, ProducerNotSupportedException, KeyAlreadyInUseException {
        if(name==null||pattern==null){
            throw new NullArgumentsException();
        }
        if (KeyInUse(name)) {
            throw new KeyAlreadyInUseException();
        }
        if (pattern instanceof Pattern)
            patternStorage.addElement(name, (Pattern) pattern);
        else if (pattern instanceof Rhythm)
            rythmStorage.addElement(name, (Rhythm) pattern);
        else if (pattern instanceof ChordProgression)
            chordProgressionStorage.addElement(name, (ChordProgression) pattern);
        else
            throw new ProducerNotSupportedException();
    }

    @Override
    public void addNotesToPattern(String patternName, String notePattern) throws PatternNotFoundException {
        patternStorage.getElement(patternName).add(notePattern);
    }

    @Override
    public void addPatternsToPatternByName(String patternTargetName, String... patternSourceNames) throws PatternNotFoundException {
        if(patternSourceNames==null||patternSourceNames==null||!allPatternsExists()) {
            throw new PatternNotFoundException();
        }
        for (String s : patternSourceNames) {
            patternStorage.getElement(patternTargetName).add(patternStorage.getElement(s));
        }
    }

    @Override
    public void addPatternsToPattern(String patternName, PatternProducer... patterns) throws PatternNotFoundException {
        patternStorage.getElement(patternName).add(patterns);
    }

    @Override
    public void addNotesToPattern(String patternName, String notePattern, int repetitions) throws PatternNotFoundException {
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
        switch (getStorageWhereKeyIsUsed(key)) {
            case UNSUPPORTED_TYPE:
                throw new PatternNotFoundException();
            case PATTERN_STORAGE:
                patternStorage.deleteElement(key);
                break;
            case RHYTHM_STORAGE:
                rythmStorage.deleteElement(key);
                break;
            case CHORD_PROGRESSION_STORAGE:
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
                continue; // TODO Refactor this code
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
        HashMap<String, GetPatternStats.Stats> statistics = new HashMap<>();
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
    public Rhythm getRhythm(String rhythmName) throws PatternNotFoundException {
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


    @Override
    public StorageTypes getStorageWhereKeyIsUsed(String key) throws PatternNotFoundException {
        if (patternStorage.checkExisting(key))
            return StorageTypes.PATTERN_STORAGE;
        if (rythmStorage.checkExisting(key))
            return StorageTypes.RHYTHM_STORAGE;
        if (chordProgressionStorage.checkExisting(key))
            return StorageTypes.CHORD_PROGRESSION_STORAGE;
        else return StorageTypes.UNSUPPORTED_TYPE;
    }

    @Override
    public ChordProgression eachChordAs(String progressionName, String sequence) throws PatternNotFoundException {
        return chordProgressionStorage.getElement(progressionName).eachChordAs(sequence);
    }

    @Override
    public ChordProgression allChordsAs(String progressionName, String sequence) throws PatternNotFoundException {
        return chordProgressionStorage.getElement(progressionName).allChordsAs(sequence);
    }

    @Override
    public void saveAsPattern(String elementName) throws PatternNotFoundException, NullArgumentsException, CanNotConvertException {
        switch (getStorageWhereKeyIsUsed(elementName)) {
            case PATTERN_STORAGE:
                break; //Already in Patterns...
            case CHORD_PROGRESSION_STORAGE:
                patternStorage.addElement(elementName + "_c", chordProgressionStorage.getElement(elementName).getPattern());
                break;
            case RHYTHM_STORAGE:
                patternStorage.addElement(elementName + "_c", rythmStorage.getElement(elementName).getPattern());
                break;
            default:
                throw new CanNotConvertException();
        }
    }

    @Override
    public void saveAsMidi(String elementName, String filename) throws PatternNotFoundException, CanNotConvertException, NotAPatternException, SetlXIOException {
        if (getStorageWhereKeyIsUsed(elementName) != StorageTypes.PATTERN_STORAGE) {
            throw new NotAPatternException();
        }
        try {
            midiManager.save(filename, patternStorage.getElement(elementName));
        } catch (IOException e) {
            throw new SetlXIOException();
        }
    }

    @Override
    public void loadMidi(String patternName, String filename) throws NullArgumentsException, SetlXIOException, org.randoom.setlx.setlXMusic.musicSystem.exceptions.MidiExceptions.InvalidMidiDataException {
        try {
            patternStorage.addElement(patternName, midiManager.load(filename));
        } catch (IOException e) {
            throw new SetlXIOException();
        } catch (InvalidMidiDataException e) {
            throw new org.randoom.setlx.setlXMusic.musicSystem.exceptions.MidiExceptions.InvalidMidiDataException();
        }
    }

    @Override
    public HashMap<String, Pattern> getAllPatterns() {
        return patternStorage.getAllElements();
    }

    /**
     * Checks, weather a given key is already in use in on of the storages.
     *
     * @param key
     * @return
     */
    private boolean KeyInUse(String key) {
        return patternStorage.checkExisting(key)
                || rythmStorage.checkExisting(key)
                || chordProgressionStorage.checkExisting(key);
    }
    public boolean allPatternsExists(String... patternNames){
        long numberOfExistingPatterns = Arrays.stream(patternNames)
                .filter(x -> patternStorage.checkExisting(x))
                .count(); // Ensure that it is a transaction.
        return numberOfExistingPatterns==patternNames.length;
    }
}
