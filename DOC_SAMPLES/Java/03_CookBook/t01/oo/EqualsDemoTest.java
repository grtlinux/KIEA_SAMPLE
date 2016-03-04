package kiea.priv.zzz.book.CookBook.t01.oo;

import kiea.priv.zzz.book.CookBook.t01.demo.oo.EqualsDemo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EqualsDemoTest
{
    /** an object being tested */
    EqualsDemo d1;
    /** another object being tested */
    EqualsDemo d2;

    /** Method to be invoked before each test method */
    @Before
    public void setUp() {
        d1 = new EqualsDemo();
        d2 = new EqualsDemo();
    }

    @Test
    public void testSymmetry() { 
        assertTrue(d1.equals(d1));
    }

    @Test
    public void testSymmetric() {
        assertTrue(d1.equals(d2) && d2.equals(d1));
    }

    @Test
    public void testCaution() {
        assertTrue(!d1.equals(null));
    }
}
