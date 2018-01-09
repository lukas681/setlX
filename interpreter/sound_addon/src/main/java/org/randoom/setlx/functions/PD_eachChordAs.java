package org.randoom.setlx.functions;

import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * This function can be used in order to manipulate a Chord Progression
 * It directly implements functionality {@link ChordProgression} eachChordAs()-Method.
 * <p>
 * Requires passing a string that has dollar signs followed by an index, in which case each dollar+index will be
 * replaced by the indexed note of the chord for each chord in the progression.
 * For example, given a ChordProgression of "I IV V" and a string of "$0q $1h $2w",
 * will return "Cq E4h G4w Fq A4h C5w Gq B4h D5w".
 * Using the underscore character instead of an index will result in the chord
 * itself added to the string. The final result will be returned from the getPattern() method.
 *
 * It can be used for example to create arpeggios and bass progressions like Alberti Bas
 */
public class PD_eachChordAs extends PreDefinedProcedure {

    /**
     * Because SetlX used the '$'-Symbol for its own purposed, we need to use another one for jfugue.
     * The problem is, that jfugue also uses the dollar for chord references like $1 $2...
     * Now we simple replace this char, so that there is no collision with setlx itself
     */
    private final static char referator = '#';

    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition CHORD_PROGRESSION = createParameter("chordProgression");

    public final static PreDefinedProcedure DEFINITION = new PD_eachChordAs();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_eachChordAs() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(CHORD_PROGRESSION);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME); //TODO Possible security issue on splitting the string
        final Value replacementString = args.get(CHORD_PROGRESSION);

        root.getSetlXPatternManager().eachChordAs(patternName.getUnquotedString(state), replacementString.getUnquotedString(state).replace('#', '$')); //TODO Catch possible exceptions
        return SetlBoolean.TRUE;
    }

    /**
     * Because optional Parameters just allow some predefined double values, we set {@link SetlDouble} ZERO to a defautl of 120 BPM
     */
    public int checkTempo(int tempo) {
        return tempo == 0 ? 120 : tempo;
    }

}
