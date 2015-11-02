package tests;


import com.danielacraciun.models.stack.ArrayStack;
import com.danielacraciun.models.stack.Stack;
import junit.framework.TestCase;

/**
 * Created by dana on 12.10.2015.
 */
public class ArrayStackTest extends TestCase {

    private Stack s;

    public void setUp() throws Exception {
        super.setUp();
        s = new ArrayStack();
    }

    public void testPush() throws Exception {
        s.push("hello");
        assertEquals(s.pop(), "hello");
    }

    public void testPop() throws Exception {
        s.push("foo");
        s.push("bar");
        assertEquals(s.pop(), "bar");
        assertEquals(s.pop(), "foo");
    }

    public void testIsEmpty() throws Exception {
        s.push("hi");
        assertFalse(s.isEmpty());
        s.pop();
        assertTrue(s.isEmpty());
    }

    public void testTop() throws Exception {
        s.push("test");
        assertEquals(s.top(), "test");
        assertFalse(s.isEmpty());
    }
}