package org.randoom.setlx.functions;

import org.jfugue.pattern.Pattern;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Key;
import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_addChordProgression extends PreDefinedProcedure {


    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition CHORD_PROGRESSION = createParameter("chordProgression");
    private final static ParameterDefinition KEY = createOptionalParameter("key", SetlString.newSetlStringFromSB(new StringBuilder("C"))); //The default base key is a C

    public final static PreDefinedProcedure DEFINITION = new PD_addChordProgression();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_addChordProgression() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(CHORD_PROGRESSION);
        addParameter(KEY);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME); //TODO Possible security issue on splitting the string
        final Value chordProgression = args.get(CHORD_PROGRESSION);
        final Value key = args.get(KEY);
        //TODO warning if key not supported
            ChordProgression cp = new ChordProgression(chordProgression.getUnquotedString(state)).setKey(key.getUnquotedString(state));

        root.getSetlXPatternManager().add(patternName.getUnquotedString(state), cp);
        return SetlBoolean.TRUE;
    }
}
