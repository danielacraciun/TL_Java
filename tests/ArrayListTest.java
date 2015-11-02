package tests;

import domain.list.ArrayList;
import domain.list.List;
import junit.framework.TestCase;

/**
 * Created by dana on 12.10.2015.
 */
public class ArrayListTest extends TestCase {

    private List l;

    public void setUp() throws Exception {
        super.setUp();
        l = new ArrayList();
    }

    public void testSize() throws Exception {
        l.add("hello");
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
        l.add(6);
        l.add(7);
        assertEquals(l.get(0), 5);
    }
}