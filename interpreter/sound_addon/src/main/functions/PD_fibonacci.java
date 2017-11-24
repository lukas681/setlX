package functions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.functions.PreDefinedProcedure;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Created on 02.03.17.
 * stat_beta(x, alpha, beta):
 *      Implements the Beta distribution. x != 0, x != 1; alpha,beta > 0
 */

public class PD_fibonacci extends PreDefinedProcedure {

    private final static ParameterDefinition N  = createParameter("n");

    /** Definition of the PreDefinedProcedure 'stat_beta' */
    public final static PreDefinedProcedure DEFINITION = new PD_fibonacci();

    private PD_fibonacci() {
        super();
        addParameter(N);
    }

    @Override
    public Value execute(State state, HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value n       = args.get(N);

        int i = n.toJIntValue(state);

        return SetlDouble.valueOf((double)fib(i));
    }

    public int fib(int n){
        if(n==0||n==1){
            return 1;
        }else{
            return fib(n-1)+fib(n-2);
        }
    }
}
