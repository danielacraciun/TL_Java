package tests;

import com.danielacraciun.models.dictionary.ArrayDictionary;
import com.danielacraciun.models.dictionary.Dictionary;
import junit.framework.TestCase;

public class ArrayDictionaryTest extends TestCase {

    private Dictionary<String, Integer> d;

    public void setUp() throws Exception {
        super.setUp();
        d = new ArrayDictionary<>();
    }

    public void testSize() throws Exception {
        d.put("var1", 1);
        d.put("var2", 8);
        assertEquals(d.size(), 2);

    }

    public void testIsEmpty() throws Exception {
        assertTrue(d.isEmpty());
        d.put("var", 1);
        assertFalse(d.isEmpty());
    }

    public void testContainsKey() throws Exception {
        d.put("var", 1);
        d.put("bar", 2);
        assertTrue(d.containsKey("var"));
    }

    public void testContainsValue() throws Exception {
        d.put("var", 1);
        d.put("bar", 2);
        assertTrue(d.containsValue(1));
    }

    public void testPut() throws Exception {
        d.put("foo", 1);
        d.put("bar", 1);
        assertEquals(d.get("foo"), d.get("bar"));
    }
}