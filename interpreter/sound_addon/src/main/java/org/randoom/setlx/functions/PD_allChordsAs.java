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
public class PD_allChordsAs extends PreDefinedProcedure {

    /**
     * Because SetlX used the '$'-Symbol for its own purposed, we need to use another one for jfugue.
     * The problem is, that jfugue also uses the dollar for chord references like $1 $2...
     * Now we simple replace this char, so that there is no collision with setlx itself
     */
    private final static char referator = '#';

    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition CHORD_PROGRESSION = createParameter("chordProgression");

    public final static PreDefinedProcedure DEFINITION = new PD_allChordsAs();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_allChordsAs() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(CHORD_PROGRESSION);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME);
        final Value replacementString = args.get(CHORD_PROGRESSION);

        state.outWrite(replacementString.getUnquotedString(state).replace('#', '$'));
        root.getSetlXPatternManager().allChordsAs(patternName.getUnquotedString(state), replacementString.getUnquotedString(state).replace('#', '$'));
        return SetlBoolean.TRUE;
    }

}