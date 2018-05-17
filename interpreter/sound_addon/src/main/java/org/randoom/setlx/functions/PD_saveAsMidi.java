package org.randoom.setlx.functions;

import org.randoom.setlx.setlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Saves an existing pattern to the filesystem.
 * It will be saved as an *.mid file, which can then be played in various other programs, that support midi-codec.
 */
public class PD_saveAsMidi extends PreDefinedProcedure {


    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition FILE_NAME = createOptionalParameter("fileName", SetlString.newSetlStringFromSB(new StringBuilder("export.mid"))); //The default base key is a C

    public final static PreDefinedProcedure DEFINITION = new PD_saveAsMidi();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_saveAsMidi() {
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
