package org.randoom.setlx.functions;

import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Adds a new {@link ChordProgression} to the storay.
 * A Chord Progression is a sequence of roman letters, that describes an musical progression.
 * Therefore, as known from musical theory, upper case letters are used for major Chords and
 * lower case letters for minor ones.
 * Additionally, you need a base key, which signalizes the tonality of the progression.
 * By default, 'C' is the base key.
 * For example, the progression "I IV V I" shows
 */
public class PD_addChordProgression extends PreDefinedProcedure {


    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition CHORD_PROGRESSION = createParameter("chordProgression");
    private final static ParameterDefinition KEY = createOptionalParameter("key", SetlString.newSetlStringFromSB(new StringBuilder("C"))); //The default base key is a C

    public final static PreDefinedProcedure DEFINITION = new PD_addChordProgression();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_addChordProgression() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(CHORD_PROGRESSION);
        addParameter(KEY);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME);
        final Value chordProgression = args.get(CHORD_PROGRESSION);
        final Value key = args.get(KEY);

        ChordProgression cp = root.getChordProgressionFactory()
                .createChordProgression(
                        chordProgression.getUnquotedString(state), key.getUnquotedString(state));
        root.getMusicManager().add(patternName.getUnquotedString(state), cp);
        return SetlBoolean.TRUE;
    }
}
