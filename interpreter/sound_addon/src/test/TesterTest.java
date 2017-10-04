import static org.junit.Assert.*;

public class TesterTest {

    public Tester t1;
    @org.junit.Before
    public void setUp() throws Exception {
    t1 = new Tester();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testSimpleAddition() throws Exception {
        assertEquals(t1.add(1,2),3);
    }
    @org.junit.Test
    public void add2() throws Exception {
        assertEquals(t1.add(-1,1),0);
    }
    @org.junit.Test
    public void add3() throws Exception {
        assertEquals(t1.add(0,0),0);
    }
    @org.junit.Test
    public void add4() throws Exception {
        assertEquals(t1.add(-1,-1),-2);
    }

}