package org.randoom.setlx.functions;

import org.jfugue.tools.GetPatternStats;
import org.randoom.setlx.SetlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Prints some interesting statistics about a musical pattern.
 */
public class PD_getPatternStats extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");

    public final static PreDefinedProcedure DEFINITION = new PD_getPatternStats();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_getPatternStats() {
        super();
        addParameter(PATTERN_NAME);
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {
        final Value patternName = args.get(PATTERN_NAME);
        HashMap<String, GetPatternStats.Stats> detailStats = root.getMusicManager().getDetailPatternStats(patternName.getUnquotedString(state));
        int[] generalStats = root.getMusicManager().getGeneralPatternStats(patternName.getUnquotedString(state));
        print(state, "Statistics for Pattern '" + patternName.getUnquotedString(state) + "'" //prints the stats
                + "\n~~~~~~~~~~GENERAL~~~~~~~~~~" +
                "\nNumber of notes: " + generalStats[0] +
                "\nNumber of rests: " + generalStats[1] +
                "\nNumber of measures: " + generalStats[2] +
                "\n~~~~~~~~~~DETAILS~~~~~~~~~~" +
                "\n        [N, Avg, SD, Range]");

        Iterator<Map.Entry<String, GetPatternStats.Stats>> it = detailStats.entrySet().iterator();
        while (it.hasNext()) { //Iterates over all detail stats
            Map.Entry pair = it.next();
            GetPatternStats.Stats s = (GetPatternStats.Stats) (pair.getValue());
            print(state, "\n" + pair.getKey().toString() + ": " + s.getN() + " | " + s.getAverage() + " | " + s.getSD() + " | " + s.getRange());
        }
        print(state, "\n");
        return SetlBoolean.TRUE;
    }

    /**
     * Print string to standard out; override with suitable function to print somewhere else.
     *
     * @param state Current state of the running setlX program.
     * @param txt   String to print.
     */
    protected void print(final State state, final String txt) {
        state.outWrite(txt);
    }
}
