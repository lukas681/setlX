package org.randoom.setlx.functions;

import org.jfugue.pattern.Atom;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.SetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.iSetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_playTone extends PreDefinedProcedure {

    private final static ParameterDefinition NOTE = createOptionalParameter("note", SetlDouble.ZERO);
    private final static ParameterDefinition DURATION = createOptionalParameter("duration", SetlDouble.ZERO);
    //TODO implement
     private final static ParameterDefinition INSTRUMENT = createOptionalParameter("duration", SetlDouble.ONE);
    private final static ParameterDefinition VOICE = createOptionalParameter("duration", SetlDouble.ZERO);
    private final static ParameterDefinition LAYER = createOptionalParameter("duration", SetlDouble.ZERO);

    public  final static PreDefinedProcedure DEFINITION = new PD_playTone();

    private iSetlXRealTimePlayer rtplayer = SetlXSoundPlugin.getInstance().getSetlXRealTimePlayer();

    protected PD_playTone() {
        super();
        addParameter(NOTE);
        addParameter(DURATION);
        addParameter(INSTRUMENT);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        //Extracts input values
        final Value note = args.get(NOTE);
        final Value duration = args.get(DURATION);
        final Value instrument = args.get(INSTRUMENT);

        rtplayer.play(new Atom((byte)1,(byte)1,(byte)instrument.toJIntValue(state),NoteFactory.getInstance().createNote(note.jIntValue(), duration.jDoubleValue()))); //TODO Make instrument optional
        //TODO Make it more beautiful
        return SetlBoolean.TRUE;
    }

}
