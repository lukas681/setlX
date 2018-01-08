package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_play extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_NAMES = createOptionalParameter("patternName", SetlString.NIL);

    public final static PreDefinedProcedure DEFINITION = new PD_play();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_play() {
        super();
        addParameter(PATTERN_NAMES);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {

        final StringBuilder out = new StringBuilder();
        final Value patternName = args.get(PATTERN_NAMES); //Extracts the name of the patterns
        patternName.appendUnquotedString(state, out, 0);
        String patternNames[] = out.toString().split("\\s+"); //We can play multiple patterns at once by seperating them
        //with a blank
        root.getSetlxMusicPlayer().play(patternNames);
        return SetlBoolean.TRUE;
    }

}
