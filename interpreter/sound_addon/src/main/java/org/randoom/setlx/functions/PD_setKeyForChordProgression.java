package org.randoom.setlx.functions;

import org.randoom.setlx.setlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Allows to set the base key for an existing chord progression.
 * Can be useful, when tonality must be changed or was set wrong at instantiation.
 */
public class PD_setKeyForChordProgression extends PreDefinedProcedure {

    private final static ParameterDefinition ELEMENT_NAME = createParameter("chordProgressionName"); //TODO Autocount
    /**
     * Defines the base key = tonality. So, let the key be 'C', the progression 'I IV V I' will
     * convert to 'C4MAJ F4MAJ G4MAJ C4MAJ' (major chords)
     */
    private final static ParameterDefinition BASE_KEY = createParameter("baseKey"); //TODO Autocount


    public final static PreDefinedProcedure DEFINITION = new PD_setKeyForChordProgression();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_setKeyForChordProgression() {
        super();
        addParameter(ELEMENT_NAME);
        addParameter(BASE_KEY);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value progressionName = args.get(ELEMENT_NAME); //TODO Possible security issue on splitting the string
        final Value base_key = args.get(BASE_KEY);

        root.getMusicManager().getChordProgression(progressionName.getUnquotedString(state)).setKey(base_key.getUnquotedString(state));
        return SetlBoolean.TRUE;
    }
}
