package tests;


import com.danielacraciun.models.stack.ArrayStack;
import com.danielacraciun.models.stack.IStack;
import junit.framework.TestCase;

@SuppressWarnings("unchecked")
public class ArrayStackTest extends TestCase {

    private IStack s;

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