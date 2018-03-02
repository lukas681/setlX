package org.randoom.setlx.functions;

import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.io.IOException;
import java.util.HashMap;

/**
 Exports an existing pattern to the filesystem.
 It will be saved as an *.mid file, which can then be played in various other programs, that support midi-codec
 */
public class PD_saveMidi extends PreDefinedProcedure {


    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition FILE_NAME = createOptionalParameter("fileName", SetlString.newSetlStringFromSB(new StringBuilder("export.mid"))); //The default base key is a C

    public final static PreDefinedProcedure DEFINITION = new PD_saveMidi();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_saveMidi() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(FILE_NAME);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME);
        final Value fileName = args.get(FILE_NAME);

        root.getMusicManager().saveAsMidi(patternName.getUnquotedString(state), fileName.getUnquotedString(state));
        return SetlBoolean.TRUE;
    }
}
