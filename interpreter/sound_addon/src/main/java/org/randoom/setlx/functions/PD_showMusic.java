package org.randoom.setlx.functions;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.setlXMusic.SoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Shows a list of all content, that is stored in the {@link sun.security.krb5.internal.rcache.DflCache.Storage}s
 */
public class PD_showMusic extends PreDefinedProcedure {

    public final static PreDefinedProcedure DEFINITION = new PD_showMusic();

    SoundPlugin root = SoundPlugin.getInstance();

    protected PD_showMusic() {
        super();
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {

        print(state, "Showing all registered music patterns:\n");
        print(state, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        print(state, "[Name, Tempo, Voice, Instrument, Pattern\n");
        HashMap<String, Pattern> allPatterns = root.getMusicManager().getAllPatterns();
        HashMap<String, Rhythm> allRhythms = root.getMusicManager().getAllRhythms();
        HashMap<String, ChordProgression> allProgressions = root.getMusicManager().getAllChordProgressions();

        allPatterns.forEach((x, y) -> print(state, "|] " + x.toString() + " " + y + "\n"));
        print(state, "\n~~~~~~RHYTHMS~~~~~\n");
        allRhythms.forEach((x, y) -> print(state, "|] " + x.toString() + " " + y + "\n"));
        print(state, "\n~~~~~Progressions~~~~~\n");
        allProgressions.forEach((x, y) -> print(state, "|> " + x.toString() + " " + y + "\n"));

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
