package org.randoom.setlx.functions;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_changeInstrument extends PreDefinedProcedure {

    private final static ParameterDefinition INSTRUMENT = createOptionalParameter("instrument", SetlString.NIL);

    public  final static PreDefinedProcedure DEFINITION = new PD_changeInstrument();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_changeInstrument() {
        super();
        addParameter(INSTRUMENT);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {

        final Value instrument = args.get(INSTRUMENT);
        //TODO Also Accepts String Representation of instrument
            root.getSetlXRealTimePlayer().changeInstrument(instrument.jIntValue());
        return SetlBoolean.TRUE;
    }
}

