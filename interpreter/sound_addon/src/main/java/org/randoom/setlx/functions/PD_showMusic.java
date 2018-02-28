package org.randoom.setlx.functions;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PD_showMusic extends PreDefinedProcedure {

    public final static PreDefinedProcedure DEFINITION = new PD_showMusic();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_showMusic() {
        super();
    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {

        print(state, "Showing all registered music patterns:\n");
        print(state, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        print(state, "[Name, Tempo, Voice, Instrument, Pattern\n");
        HashMap<String, Pattern> allPatterns = root.getSetlXPatternManager().getAllPatterns();
        HashMap<String, Rhythm> allRhythms = root.getSetlXPatternManager().getAllRhythms();
        HashMap<String, ChordProgression> allProgressions = root.getSetlXPatternManager().getAllChordProgressions();

        int i = 0;
            Iterator it = allPatterns.entrySet().iterator();
        for(int y = 0; y <3; y++) { //In order to save redundance
            if(y == 1) { //Iterating over Rhythms
                print(state, "\n~~~~~~RHYTHMS~~~~~\n");
                i=0;
                it = allRhythms.entrySet().iterator();
            }if(y == 2) { //Iterating over Chord Progressions
                i = 0;
                print(state, "\n~~~~~Progressions~~~~~\n");
                it = allProgressions.entrySet().iterator();
            }
            while (it.hasNext()) { //Patterns
                Map.Entry pair = (Map.Entry) it.next();
                print(state, ++i + ".)" + pair.getKey() + " " + pair.getValue() + "\n");
            }
        }
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
