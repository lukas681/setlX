package org.randoom.setlx.functions;

import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_saveAsPattern extends PreDefinedProcedure {

    private final static ParameterDefinition ELEMENT_NAME = createParameter("patternName");

    public final static PreDefinedProcedure DEFINITION = new PD_saveAsPattern();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_saveAsPattern() {
        super();
        addParameter(ELEMENT_NAME);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value elementName = args.get(ELEMENT_NAME);

        root.getSetlXPatternManager().saveAsPattern(elementName.getUnquotedString(state));
        return SetlBoolean.TRUE;
    }

    /**
     * Because optional Parameters just allow some predefined double values, we set {@link SetlDouble} ZERO to a defautl of 120 BPM
     */
    public int checkTempo(int tempo) {
        return tempo == 0 ? 120 : tempo;
    }

}
