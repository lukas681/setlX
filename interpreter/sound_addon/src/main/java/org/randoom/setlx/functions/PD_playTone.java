package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.SetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.iSetlXRealTimePlayer;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_playTone extends PreDefinedProcedure {

    private final static ParameterDefinition INSTRUMENT = createOptionalParameter("instrument", SetlDouble.ONE);
    private final static ParameterDefinition NOTE = createOptionalParameter("note", SetlDouble.ZERO);
    private final static ParameterDefinition DURATION = createOptionalParameter("duration", SetlDouble.ZERO);

    public  final static PreDefinedProcedure DEFINITION = new PD_playTone();

    private iSetlXRealTimePlayer rtplayer = new SetlXRealTimePlayer();

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
        final Value instrument = args.get(INSTRUMENT); //TODO resolve instrument enum
    //TODO Cast Values
        rtplayer.changeInstrument(instrument.jIntValue());
        rtplayer.play(NoteFactory.getInstance().createNote(note.jIntValue(), duration.jDoubleValue())); //TODO Make instrument optional

        return SetlBoolean.TRUE;
    }

}
