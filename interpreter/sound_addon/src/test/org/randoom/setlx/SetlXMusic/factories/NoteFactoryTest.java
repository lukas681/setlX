package org.randoom.setlx.SetlXMusic.factories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteFactoryTest {
    iNoteFactory fac;
    @Before
    public void setUp() throws Exception {
        fac = new NoteFactory();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createNote() throws Exception {
        assertNotNull(fac.createNote(100,100));
    }
    @Test
    public void createNote1() throws Exception {
        assertNotNull("Negative Note Values", fac.createNote(-1,-1));
    }
    @Test
    public void createNote2() throws Exception {
        assertNotNull("Zero Values on Notes" ,fac.createNote(0,0));
    }


}