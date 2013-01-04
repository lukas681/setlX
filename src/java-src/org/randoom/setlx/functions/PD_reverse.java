package org.randoom.setlx.functions;

import org.randoom.setlx.exceptions.IncompatibleTypeException;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.List;

// reverse(collectionValue)      : reverse the order of members in `collectionValue'

public class PD_reverse extends PreDefinedFunction {
    public final static PreDefinedFunction DEFINITION = new PD_reverse();

    private PD_reverse() {
        super("reverse");
        addParameter("collectionValue");
    }

    @Override
    public Value execute(final State state, final List<Value> args, final List<Value> writeBackVars) throws IncompatibleTypeException {
        return args.get(0).reverse();
    }
}
