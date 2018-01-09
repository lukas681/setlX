package org.randoom.setlx.functions;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_addRhythm extends PreDefinedProcedure {

    private final static ParameterDefinition RHYTHM_NAME = createParameter("patternName");
    private final static ParameterDefinition PATTERN = createParameter("pattern");
    private final static ParameterDefinition TEMPO = createOptionalParameter("tempo", SetlDouble.ZERO);
    private final static ParameterDefinition LENGTH= createOptionalParameter("length", SetlDouble.ONE);

    public final static PreDefinedProcedure DEFINITION = new PD_addRhythm();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_addRhythm() {
        super();
        addParameter(RHYTHM_NAME);
        addParameter(PATTERN);
        addParameter(TEMPO);
        addParameter(LENGTH);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(RHYTHM_NAME);
        final Value pattern = args.get(PATTERN);
        final Value length = args.get(LENGTH);
        Rhythm rhythm = new Rhythm(pattern.getUnquotedString(state)).setLength(length.toJIntValue(state)); //Fucking 3hours of debugging... just to see, that there were "" put in...

        root.getSetlXPatternManager().add(patternName.getUnquotedString(state), rhythm);
        return SetlBoolean.TRUE;
    }
}
