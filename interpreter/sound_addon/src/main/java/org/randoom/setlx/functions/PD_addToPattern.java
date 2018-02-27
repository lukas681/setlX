package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Allows to add musical information to an already existing pattern.
 * This can be used for example to add another voice, notes and so on.
 */
public class PD_addToPattern extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition PATTERN_STRING = createParameter("patternString");

    public final static PreDefinedProcedure DEFINITION = new PD_addToPattern();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_addToPattern() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(PATTERN_STRING);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME);
        final Value pattern = args.get(PATTERN_STRING);

        root.getSetlXPatternManager().addToPattern(patternName.getUnquotedString(state), pattern.getUnquotedString(state));
        return SetlBoolean.TRUE;
    }
}
