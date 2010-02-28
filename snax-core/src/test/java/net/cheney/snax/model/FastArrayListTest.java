package net.cheney.snax.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class FastArrayListTest {
	
	@Test public void testCreationFromIterable() {
		List<? extends Node> n = Arrays.asList(new Element("foo"), new Element("bar"), new Element("baz"));
		FastArrayList l = FastArrayList.newInstance(n);
		int count = 0;
		Iterator<Node> i = l.iterator();
		while(i.hasNext()) {
			++count;
			assertNotNull(i.next());
		}
		assertEquals(3, count);
	}

	@Test public void testIterator() {
		FastArrayList l = FastArrayList.newInstance(new Node[] { new Element("foo"), new Element("bar"), new Element("baz") });
		int count = 0;
		Iterator<Node> i = l.iterator();
		while(i.hasNext()) {
			++count;
			assertNotNull(i.next());
		}
		assertEquals(3, count);
	}
	
	@Test public void testEmptyIterator() {
		FastArrayList l = FastArrayList.newInstance(new Node[] {  });
		int count = 0;
		Iterator<Node> i = l.iterator();
		while(i.hasNext()) {
			++count;
			assertNotNull(i.next());
		}
		assertEquals(0, count);
	}
}