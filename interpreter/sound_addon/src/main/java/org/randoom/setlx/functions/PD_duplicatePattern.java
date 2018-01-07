package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_duplicatePattern extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_SOURCE_NAME = createParameter("patternSourceName");
    private final static ParameterDefinition PATTERN_NEW_NAME = createParameter("patternNewName");

    public  final static PreDefinedProcedure DEFINITION = new PD_duplicatePattern();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_duplicatePattern() {
        super();
        addParameter(PATTERN_SOURCE_NAME);
        addParameter(PATTERN_NEW_NAME);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternSource = args.get(PATTERN_SOURCE_NAME);
        final Value patternNewName = args.get(PATTERN_NEW_NAME);

        System.out.println(patternSource.getUnquotedString(state));
        System.out.println(patternNewName.getUnquotedString(state));
        root.getSetlXPatternManager().duplicatePattern(patternSource.getUnquotedString(state), patternNewName.getUnquotedString(state));
        return SetlBoolean.TRUE;
    }
}
