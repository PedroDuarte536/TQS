package com.tqs.lab1.ex1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TqsStackTest {

	private TqsStack<String> tqsStack;

	@BeforeEach
	public void testBeforeEach() throws Exception {
		tqsStack = new TqsStack<>(10);
	}

	@Test
	public void testPush() throws Exception {
		int entries = 10;
		for(int i = 0; i < entries; i++) tqsStack.push("Test");
		
		assertThrows(IllegalStateException.class, () -> tqsStack.push("Test"));
	}

	@Test
	public void testPop() throws Exception {
		assertThrows(NoSuchElementException.class, () -> tqsStack.pop());

		String t = "Teste";
		tqsStack.push(t);
		assertEquals(tqsStack.pop(), t);

		String t2 = "Teste2";
		String t3 = "Teste3";
		String t4 = "Teste4";
		String t5 = "Teste5";
		tqsStack.push(t2);
		tqsStack.push(t3);
		tqsStack.push(t4);
		tqsStack.push(t5);

		assertEquals(tqsStack.size(), 4);

		assertEquals(tqsStack.pop(), t5);
		assertEquals(tqsStack.pop(), t4);
		assertEquals(tqsStack.pop(), t3);
		assertEquals(tqsStack.pop(), t2);

		assertEquals(tqsStack.size(), 0);
		
		assertThrows(NoSuchElementException.class, () -> tqsStack.pop());
	}

	@Test
	public void testPeek() throws Exception {
		assertThrows(NoSuchElementException.class, () -> tqsStack.peek());

		String t = "Teste";
		tqsStack.push(t);

		assertEquals(tqsStack.size(), 1);

		assertEquals(tqsStack.peek(), t);
		assertEquals(tqsStack.peek(), t);

		assertEquals(tqsStack.size(), 1);

		String t2 = "Teste2";
		tqsStack.push(t2);
		assertEquals(tqsStack.peek(), t2);
	}

	@Test
	public void testSize() throws Exception {
		assertEquals(tqsStack.size(), 0);

		int entries = 10;
		for(int i = 0; i < entries; i++) tqsStack.push("Test");
		assertEquals(tqsStack.size(), entries);

		for(int i = 0; i < entries; i++) tqsStack.pop();
		assertEquals(tqsStack.size(), 0);
	}

	@Test
	public void testIsEmpty() throws Exception {
		assertEquals(tqsStack.isEmpty(), true);

		tqsStack.push("Test");
		assertEquals(tqsStack.isEmpty(), false);

		tqsStack.pop();
		assertEquals(tqsStack.isEmpty(), true);
	}

}
