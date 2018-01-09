package org.randoom.setlx.SetlXMusic.Patterns;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;

public abstract class SetlXBasicStorage<T>{

    abstract void addPattern(String name, T d) throws NullArgumentsException;

    abstract  boolean checkExisting(String name);


}
