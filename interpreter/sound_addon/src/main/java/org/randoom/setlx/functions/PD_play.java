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

public class PD_addPattern extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_NAME = createOptionalParameter("patternName", SetlString.NIL);
    private final static ParameterDefinition PATTERN = createOptionalParameter("pattern", SetlDouble.ONE);
    private final static ParameterDefinition TEMPO = createOptionalParameter("tempo", SetlDouble.ZERO);
    private final static ParameterDefinition INSTRUMENT = createOptionalParameter("instrument", SetlDouble.ZERO);
    private final static ParameterDefinition VOICE = createOptionalParameter("voice", SetlDouble.ZERO);

    public  final static PreDefinedProcedure DEFINITION = new PD_addPattern();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_addPattern() {
        super();
        addParameter(PATTERN);
        addParameter(PATTERN_NAME);
        addParameter(TEMPO);
        addParameter(INSTRUMENT);
        addParameter(VOICE);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        System.out.println("Calle!");
        final Value patternName = args.get(PATTERN_NAME);
        final Value pattern = args.get(PATTERN);
        final Value tempo = args.get(TEMPO);
        final Value instrument = args.get(INSTRUMENT);
        final Value voice = args.get(VOICE);

        Pattern patt = new Pattern(pattern.toString());
        patt = patt.setVoice(voice.jIntValue());
        patt = patt.setInstrument(instrument.jIntValue());
        patt = patt.setTempo(tempo.jIntValue());

        root.getSetlXPatternManager().addPattern(patternName.toString(), patt);

        return SetlBoolean.TRUE;
    }

}
