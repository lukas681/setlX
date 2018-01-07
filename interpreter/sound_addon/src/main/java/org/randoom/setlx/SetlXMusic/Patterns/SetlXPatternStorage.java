package org.randoom.setlx.SetlXMusic.Patterns;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.PatternNotFoundException;

import java.util.HashMap;

/**
 *
 */
public class SetlXPatternStorage implements iSetlXPatternStorage {

    HashMap<String, Pattern> patternStorage = new HashMap<>();

    @Override
        public void addPattern(String name, Pattern pattern) throws NullArgumentsException {
            if(name!=null&&pattern!=null) {
                patternStorage.put(name, pattern);
            }else{
                throw new NullArgumentsException();
            }
    }

    @Override
    public boolean checkExisting(String name) {
        return patternStorage.containsKey(name);
    }

    @Override
    public Pattern getPattern(String name)throws PatternNotFoundException {
        Pattern tmp = patternStorage.get(name);
        if(tmp==null){
            throw new PatternNotFoundException();
        }
        return tmp;
    }

    @Override
    public void deletePattern(String name) {
        patternStorage.remove(name);
    }

    @Override
    public HashMap<String, Pattern> getAllPatterns() {
        return patternStorage;
    }

}
