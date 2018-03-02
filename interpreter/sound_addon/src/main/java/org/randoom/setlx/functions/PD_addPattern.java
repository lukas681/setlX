package org.randoom.setlx.functions;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Adds a new Pattern to the storage. A musical pattern can container all information, a jFugue pattern can container.
 * This includes notes, instruments, voices and tempos.
 */
public class PD_addPattern extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_NAME = createOptionalParameter("patternName", SetlString.NIL);
    private final static ParameterDefinition PATTERN = createParameter("pattern");
    private final static ParameterDefinition VOICE = createOptionalParameter("voice", SetlDouble.ZERO);
    private final static ParameterDefinition TEMPO = createOptionalParameter("tempo", SetlDouble.ZERO); //Zero if we use default value
    private final static ParameterDefinition INSTRUMENT = createOptionalParameter("instrument", SetlDouble.ZERO);
    // private final static ParameterDefinition REPEAT = createOptionalParameter("repeat", SetlDouble.ONE); //TODO Implement That!

    private final int defaultVoice = 0;
    private final int defaultTempo = 120;
    private final int defaultInstrument = 1;


    public final static PreDefinedProcedure DEFINITION = new PD_addPattern();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_addPattern() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(PATTERN);
        addParameter(VOICE);
        addParameter(TEMPO);
        addParameter(INSTRUMENT);
        //  addParameter(REPEAT);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME);
        final Value pattern = args.get(PATTERN);
        final Value voice = args.get(VOICE);
        final Value tempo = args.get(TEMPO);
        final Value instrument = args.get(INSTRUMENT);

        Pattern ptrn;
        if (instrument.toJIntValue(state) != 0 || tempo.toJIntValue(state) != 0 || voice.toJIntValue(state) != 0) {
            // If there are given values for instrument, tempo or voice, then we need to call a special factory method
            ptrn = root.getPatternFactoy().createPattern(
                    pattern.getUnquotedString(state), // TODO Proof correctness...
                    (instrument.toJIntValue(state)==0?-1:instrument.toJIntValue(state)),
                    (tempo.toJIntValue(state)==0?-1:tempo.toJIntValue(state)),
                    (voice.toJIntValue(state)==0?-1:voice.toJIntValue(state)) //If no property is set, then we will set them to -1
            );
        } else {
            // This is the standard factory call if no extra properties are set
            ptrn = root.getPatternFactoy().createPattern(pattern.getUnquotedString(state));
        }

        root.getMusicManager().add(patternName.getUnquotedString(state), ptrn);
        return SetlBoolean.TRUE;
    }
}
