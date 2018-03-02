package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;


import java.util.HashMap;

public class PD_addPatternsToPattern extends PreDefinedProcedure {
    private final static ParameterDefinition PATTERN_TARGET = createParameter("patternTarget");
    private final static ParameterDefinition PATTERNS_FROM = createParameter("patternsFrom");

    public final static PreDefinedProcedure DEFINITION = new PD_addPatternsToPattern();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_addPatternsToPattern() {
        super();
        addParameter(PATTERNS_FROM);
        addParameter(PATTERN_TARGET);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        

        return SetlBoolean.TRUE;
    }
}
