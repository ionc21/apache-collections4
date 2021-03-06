package com.pgs.multimap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.MultiMapUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.Test;

public class MultiMapUtilsTest {

	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testEmptyUnmodifiableMultiValuedMap() {
		final MultiValuedMap map = MultiMapUtils.EMPTY_MULTI_VALUED_MAP;
		assertTrue(map.isEmpty());
		try {
			map.put("key", "value");
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void testTypeSafeEmptyMultiValuedMap() {
		final MultiValuedMap<String, String> map = MultiMapUtils.<String, String> emptyMultiValuedMap();
		assertTrue(map.isEmpty());
		try {
			map.put("key", "value");
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void testEmptyIfNull() {
		assertTrue(MultiMapUtils.emptyIfNull(null).isEmpty());

		final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
		map.put("item", "value");
		assertFalse(MultiMapUtils.emptyIfNull(map).isEmpty());
	}

	@Test
	public void testIsEmptyWithEmptyMap() {
		final MultiValuedMap<Object, Object> map = new ArrayListValuedHashMap<>();
		assertEquals(true, MultiMapUtils.isEmpty(map));
	}

	@Test
	public void testIsEmptyWithNonEmptyMap() {
		final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
		map.put("item", "value");
		assertEquals(false, MultiMapUtils.isEmpty(map));
	}

	@Test
	public void testIsEmptyWithNull() {
		final MultiValuedMap<Object, Object> map = null;
		assertEquals(true, MultiMapUtils.isEmpty(map));
	}

	@Test
	public void testGetCollection() {
		assertNull(MultiMapUtils.getCollection(null, "key1"));

		String values[] = { "v1", "v2", "v3" };
		final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
		for (String val : values) {
			map.put("key1", val);
		}

		Collection<String> col = MultiMapUtils.getCollection(map, "key1");
		for (String val : values) {
			assertTrue(col.contains(val));
		}
	}

	@Test
	public void testGetValuesAsList() {
		assertNull(MultiMapUtils.getValuesAsList(null, "key1"));

		String values[] = { "v1", "v2", "v3" };
		final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
		for (String val : values) {
			map.put("key1", val);
		}

		List<String> list = MultiMapUtils.getValuesAsList(map, "key1");
		int i = 0;
		for (String val : list) {
			assertTrue(val.equals(values[i++]));
		}
	}

	@Test
	public void testGetValuesAsSet() {
		assertNull(MultiMapUtils.getValuesAsList(null, "key1"));

		String values[] = { "v1", "v2", "v3" };
		final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
		for (String val : values) {
			map.put("key1", val);
			map.put("key1", val);
		}

		Set<String> set = MultiMapUtils.getValuesAsSet(map, "key1");
		assertEquals(3, set.size());
		for (String val : values) {
			assertTrue(set.contains(val));
		}
	}

	@Test
	public void testGetValuesAsBag() {
		assertNull(MultiMapUtils.getValuesAsBag(null, "key1"));

		String values[] = { "v1", "v2", "v3" };
		final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
		for (String val : values) {
			map.put("key1", val);
			map.put("key1", val);
		}

		Bag<String> bag = MultiMapUtils.getValuesAsBag(map, "key1");
		assertEquals(6, bag.size());
		for (String val : values) {
			assertTrue(bag.contains(val));
			assertEquals(2, bag.getCount(val));
		}
	}
}