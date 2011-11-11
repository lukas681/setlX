package interpreter.functions;

import interpreter.exceptions.SetlException;
import interpreter.types.Value;

import java.util.List;

public class PD_rnd extends PreDefinedFunction {
    public final static PreDefinedFunction DEFINITION = new PD_rnd();

    private PD_rnd() {
        super("rnd");
        addParameter("compoundValue");
    }

    public Value execute(List<Value> args, List<Value> writeBackVars) throws SetlException {
        return args.get(0).randomMember();
    }
}
