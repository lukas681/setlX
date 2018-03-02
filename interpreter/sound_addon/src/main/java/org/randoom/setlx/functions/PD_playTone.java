package org.randoom.setlx.functions;

import org.randoom.setlx.Exceptions.NotInByteRangeException;
import org.randoom.setlx.SetlXMusic.RealTimeSystem.iRealTimePlayer;
import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

/**
 * Plays a single tone.
 */
public class PD_playTone extends PreDefinedProcedure {

    private final static ParameterDefinition NOTE = createOptionalParameter("note", SetlDouble.ZERO); //TODO Also accept an String for note value
    private final static ParameterDefinition DURATION = createOptionalParameter("duration", SetlDouble.ZERO);
    private final static ParameterDefinition INSTRUMENT = createOptionalParameter("instrument", SetlDouble.ONE);
    private final static ParameterDefinition VOICE = createOptionalParameter("voice", SetlDouble.ONE);
    private final static ParameterDefinition LAYER = createOptionalParameter("layer", SetlDouble.ONE);

    public final static PreDefinedProcedure DEFINITION = new PD_playTone();

    private iRealTimePlayer rtplayer = SoundPlugin.getInstance().getSetlXRealTimePlayer();

    protected PD_playTone() {
        super();
        addParameter(NOTE);
        addParameter(DURATION);
        addParameter(INSTRUMENT);
        addParameter(VOICE);
        addParameter(LAYER);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        //Extracts input values
        final Value note = args.get(NOTE);
        final Value duration = args.get(DURATION);
        final Value instrument = args.get(INSTRUMENT);
        final Value voice = args.get(VOICE);
        final Value layer = args.get(LAYER);

        if (!isInByteRange(voice.toJIntValue(state), layer.toJIntValue(state), instrument.toJIntValue(state))) {
            throw new NotInByteRangeException(); // Cancel, if not in byte range.
        }
        rtplayer.play((byte) voice.toJIntValue(state), (byte) layer.toJIntValue(state), (byte) instrument.toJIntValue(state),
                note.toJIntValue(state), duration.toJDoubleValue(state));
        return SetlBoolean.TRUE;
    }

    /**
     * Checks, weather the given long values are in the range of an byte array
     *
     * @param valueN
     * @return
     */
    public static boolean isInByteRange(long... valueN) { //TODO Test!
        for (long x : valueN) {
            if (!(x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE)) {
                return false;
            }
        }
        return true;
    }
}
