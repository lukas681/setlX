package org.randoom.setlx.functions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_playTone extends PreDefinedProcedure {

    private final static ParameterDefinition INSTRUMENT = createOptionalParameter("instrument", SetlDouble.ONE);
    private final static ParameterDefinition NOTE = createOptionalParameter("note", SetlDouble.ZERO);
    private final static ParameterDefinition VELOCITY = createOptionalParameter("velocity", SetlDouble.ZERO);
    private final static ParameterDefinition VOLUME = createOptionalParameter("volume", SetlDouble.ZERO);

    public  final static PreDefinedProcedure DEFINITION = new PD_playTone();

    protected PD_playTone() {
        super();
        addParameter(NOTE);
        addParameter(VELOCITY);
        addParameter(VOLUME);
        addParameter(INSTRUMENT);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value note = args.get(NOTE);
        final Value velocity = args.get(VELOCITY);
        final Value volume = args.get(VOLUME);
        final Value instrument = args.get(INSTRUMENT);
        //SoundManagerImpl.getInstance().playTone(note.toJIntValue(state),velocity.toJIntValue(state),volume.toJIntValue(state), instrument.toJIntValue(state));

        return SetlBoolean.TRUE;
    }

}
