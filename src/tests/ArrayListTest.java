package tests;


import com.danielacraciun.models.list.ArrayList;
import com.danielacraciun.models.list.List;
import junit.framework.TestCase;

public class ArrayListTest extends TestCase {

    private List<Integer> l;

    public void setUp() throws Exception {
        super.setUp();
        l = new ArrayList<>();
    }

    public void testSize() throws Exception {
        l.add(1);
        assertEquals(l.size(), 1);
    }

    public void testIsEmpty() throws Exception {
        l.add(1);
        assertFalse(l.isEmpty());
    }

    public void testContains() throws Exception {
        l.add(2);
        assertTrue(l.contains(2));
    }

    public void testAdd() throws Exception {
        l.add(5);
        assertTrue(l.contains(5));
    }

    public void testGet() throws Exception {
        l.add(5);
        l.add(5);
        l.add(7);
        assertEquals(l.get(0), l.get(1));
    }
}