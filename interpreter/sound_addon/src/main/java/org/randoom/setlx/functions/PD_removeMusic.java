package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_removeMusic extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");

    public final static PreDefinedProcedure DEFINITION = new PD_removeMusic();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_removeMusic() {
        super();
        addParameter(PATTERN_NAME);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME);
        root.getSetlXPatternManager().removeElement(patternName.getUnquotedString(state));
        return SetlBoolean.TRUE;
    }
}
