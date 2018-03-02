package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;


import java.util.HashMap;

public class PD_addPatternsToPattern extends PreDefinedProcedure {
    private final static ParameterDefinition PATTERNS_FROM = createParameter("patternsFrom");
    private final static ParameterDefinition PATTERN_TARGET = createParameter("patternTarget");

    public final static PreDefinedProcedure DEFINITION = new PD_addPatternsToPattern();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_addPatternsToPattern() {
        super();
        addParameter(PATTERNS_FROM);
        addParameter(PATTERN_TARGET);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternsFrom = args.get(PATTERNS_FROM);
        final Value patternTarget = args.get(PATTERN_TARGET);

        root.getMusicManager().addPatternsToPatternByName(patternTarget.getUnquotedString(state),
                patternsFrom.getUnquotedString(state).split(" ")); // Parses multiple names like "Pattern1 Pattern2".

        return SetlBoolean.TRUE;
    }
}
