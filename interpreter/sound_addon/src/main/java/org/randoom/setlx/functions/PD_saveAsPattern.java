package org.randoom.setlx.functions;

import org.randoom.setlx.setlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Converts a {@link org.jfugue.theory.ChordProgression} or a {@link org.jfugue.rhythm.Rhythm} into a normal pattern.
 * This will be saved with a '_c' postfix.
 * For example, a ChordProgression "cp1" will become "cp1_c" in patterns.
 */
public class PD_saveAsPattern extends PreDefinedProcedure {

    private final static ParameterDefinition ELEMENT_NAME = createParameter("patternName");

    public final static PreDefinedProcedure DEFINITION = new PD_saveAsPattern();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_saveAsPattern() {
        super();
        addParameter(ELEMENT_NAME);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value elementName = args.get(ELEMENT_NAME);

        root.getMusicManager().saveAsPattern(elementName.getUnquotedString(state));
        return SetlBoolean.TRUE;
    }

}
