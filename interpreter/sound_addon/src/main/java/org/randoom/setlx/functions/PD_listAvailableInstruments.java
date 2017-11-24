package org.randoom.setlx.functions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_listAvailableInstruments extends PreDefinedProcedure {


    public  final static PreDefinedProcedure DEFINITION = new PD_listAvailableInstruments();

    protected PD_listAvailableInstruments() {
        super();
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
       // state.prompt(SoundManagerImpl.getInstance().listInstruments());
        //state.outWrite(SoundManagerImpl.getInstance().listInstruments());
        return SetlBoolean.TRUE;
    }

}
