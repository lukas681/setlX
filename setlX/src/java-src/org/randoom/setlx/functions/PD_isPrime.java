package org.randoom.setlx.functions;

import org.randoom.setlx.exceptions.IncompatibleTypeException;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Rational;

import java.util.List;

// isPrime(integer)              : Returns true if `integer' is a prime, false otherwise.
//                                 Unlike isProbablePrime() this functions actually
//                                 tests all possible factors, therefore has linear
//                                 complexity.

public class PD_isPrime extends PreDefinedFunction {
    public final static PreDefinedFunction DEFINITION = new PD_isPrime();

    private PD_isPrime() {
        super("isPrime");
        addParameter("n");
    }

    public Value execute(final List<Value> args, final List<Value> writeBackVars) throws IncompatibleTypeException {
        final Value integer  = args.get(0);
        if (integer.isInteger() != SetlBoolean.TRUE) {
            throw new IncompatibleTypeException("Argument '" + integer + "' is not an integer.");
        }

        return SetlBoolean.get( ((Rational) integer).isPrime() );

    }

}

