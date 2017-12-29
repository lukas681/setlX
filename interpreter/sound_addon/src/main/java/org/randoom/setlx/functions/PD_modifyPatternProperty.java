package org.randoom.setlx.functions;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.Exceptions.ParameterNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.PatternParameters;
import org.randoom.setlx.SetlXMusic.SetlXSoundPlugin;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.parameters.ParameterDefinition;
import org.randoom.setlx.types.SetlBoolean;
import org.randoom.setlx.types.SetlDouble;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_modifyPatternProperty extends PreDefinedProcedure {

    private final static ParameterDefinition PATTERN_NAME = createParameter("patternName");
    private final static ParameterDefinition PROPERTY = createParameter("property");
    private final static ParameterDefinition VALUE = createParameter("value");

    public  final static PreDefinedProcedure DEFINITION = new PD_modifyPatternProperty();

    SetlXSoundPlugin root = SetlXSoundPlugin.getInstance();

    protected PD_modifyPatternProperty() {
        super();
        addParameter(PATTERN_NAME);
        addParameter(PROPERTY);
        addParameter(VALUE);

    }

    @Override
    protected Value execute(final State state, final HashMap<ParameterDefinition, Value> args) throws SetlException {

        final Value patternName = args.get(PATTERN_NAME);
        final Value patternProperty = args.get(PROPERTY);
        final Value patternValue = args.get(VALUE);

        PatternParameters property = parseProperty(patternProperty.getUnquotedString(state)); //Parses inputpropertyinput property

        if(property!=null) {
            root.getSetlXPatternManager().modifyPatternProperty(patternName.getUnquotedString(state), property, patternValue.toJIntValue(state));
        }else{
            //If we can not find the pattern parameter, we throw a little exception alerting it
            throw new ParameterNotFoundException();
        }
        return SetlBoolean.TRUE;
    }

    /**
     * We want to convert a argument, that says, what property we want to modify into an {@link PatternParameters} value.
     * Therefore, it just parses the input string.
     * @param s
     * @return
     */
    public PatternParameters parseProperty(String s){
        if(s.toUpperCase().contains("INSTRUMENT")||s.compareTo("0")==0){
            return PatternParameters.INSTRUMENT;
        }else if(s.toUpperCase().contains("TEMPO")||s.compareTo("1")==0){
            return PatternParameters.TEMPO;
        }else if(s.toUpperCase().contains("VOICE")||s.compareTo("2")==0){
            return PatternParameters.VOICE;
        }
        //default value
        return null;
    }

}
