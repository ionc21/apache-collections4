package com.pgs.comparators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;

import org.apache.commons.collections4.ComparatorUtils;
import org.junit.Test;

public class ComparatorsUtilsTest {
	
	    @Test
	    public void booleanComparator() {
	        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);
	        assertEquals(-1, comp.compare(Boolean.TRUE, Boolean.FALSE));
	        assertEquals(comp.compare(Boolean.TRUE, Boolean.TRUE), 0);
	        assertEquals(comp.compare(Boolean.FALSE, Boolean.TRUE), 1);
	 
	        comp = ComparatorUtils.booleanComparator(false);
	        assertTrue(comp.compare(Boolean.TRUE, Boolean.FALSE) > 0);
	        assertTrue(comp.compare(Boolean.TRUE, Boolean.TRUE) == 0);
	        assertTrue(comp.compare(Boolean.FALSE, Boolean.TRUE) < 0);
	    }
	 
	    @Test
	    public void chainedComparator() {
	        // simple test: chain 2 natural comparators
	        @SuppressWarnings("unchecked")
			Comparator<Integer> comp = ComparatorUtils.chainedComparator(ComparatorUtils.<Integer>naturalComparator(),
	                                                                     ComparatorUtils.<Integer>naturalComparator());
	        assertTrue(comp.compare(1, 2) < 0);
	        assertTrue(comp.compare(1, 1) == 0);
	        assertTrue(comp.compare(2, 1) > 0);
	    }
	 
	    @Test
	    public void max() {
	        Comparator<Integer> reversed =
	                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());
	 
	        assertEquals(Integer.valueOf(10), ComparatorUtils.max(1, 10, null));
	        assertEquals(Integer.valueOf(10), ComparatorUtils.max(10, -10, null));
	         
	        assertEquals(Integer.valueOf(1), ComparatorUtils.max(1, 10, reversed));
	        assertEquals(Integer.valueOf(-10), ComparatorUtils.max(10, -10, reversed));
	 
	        try {
	            ComparatorUtils.max(1, null, null);
	            fail("expecting NullPointerException");
	        } catch (NullPointerException npe) {
	            // expected
	        }
	 
	        try {
	            ComparatorUtils.max(null, 10, null);
	            fail("expecting NullPointerException");
	        } catch (NullPointerException npe) {
	            // expected
	        }
	    }
	 
	    @Test
	    public void min() {
	        Comparator<Integer> reversed =
	                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());
	 
	        assertEquals(Integer.valueOf(1), ComparatorUtils.min(1, 10, null));
	        assertEquals(Integer.valueOf(-10), ComparatorUtils.min(10, -10, null));
	         
	        assertEquals(Integer.valueOf(10), ComparatorUtils.min(1, 10, reversed));
	        assertEquals(Integer.valueOf(10), ComparatorUtils.min(10, -10, reversed));
	 
	        try {
	            ComparatorUtils.min(1, null, null);
	            fail("expecting NullPointerException");
	        } catch (NullPointerException npe) {
	            // expected
	        }
	 
	        try {
	            ComparatorUtils.min(null, 10, null);
	            fail("expecting NullPointerException");
	        } catch (NullPointerException npe) {
	            // expected
	        }
	    }
	 
	    @Test
	    public void nullLowComparator() {
	        Comparator<Integer> comp = ComparatorUtils.nullLowComparator(null);
	        assertEquals(comp.compare(null, 10), -1);
	        assertEquals(comp.compare(null, null), 0);
	        assertEquals(comp.compare(10, null), 1);
	    }
	 
	    @Test
	    public void nullHighComparator() {
	        Comparator<Integer> comp = ComparatorUtils.nullHighComparator(null);
	        assertTrue(comp.compare(null, 10) > 0);
	        assertTrue(comp.compare(null, null) == 0);
	        assertTrue(comp.compare(10, null) < 0);
	    }
	 
}
