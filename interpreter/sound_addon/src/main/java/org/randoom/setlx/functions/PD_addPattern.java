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

    private final static ParameterDefinition PATTERN_NAME = createOptionalParameter("patternName", SetlString.NIL); //TODO Autocount
    private final static ParameterDefinition PATTERN = createParameter("pattern");
    private final static ParameterDefinition TEMPO = createOptionalParameter("tempo", SetlDouble.ZERO);
    private final static ParameterDefinition INSTRUMENT = createOptionalParameter("instrument", SetlDouble.ONE);
    private final static ParameterDefinition VOICE = createOptionalParameter("voice", SetlDouble.ONE);

    public  final static PreDefinedProcedure DEFINITION = new PD_addPattern();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_addPattern() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(PATTERN);
        addParameter(TEMPO);
        addParameter(INSTRUMENT);
        addParameter(VOICE);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME); //TODO Possible security issue on splitting the string
        final Value pattern = args.get(PATTERN);
        final Value tempo = args.get(TEMPO);
        final Value instrument = args.get(INSTRUMENT);
        final Value voice = args.get(VOICE);

        Pattern patt = new Pattern(pattern.toString())
                .setVoice(voice.jIntValue())
                .setInstrument(instrument.jIntValue())
                .setTempo(tempo.jIntValue());
        root.getSetlXPatternManager().addPattern(patternName.toString().replaceAll("\"",""), patt);
        return SetlBoolean.TRUE;
    }

}
