package com.tqs.lab1.ex1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<T> {

  private final ArrayList<T> list =  new ArrayList<>();
  private Integer bound = null;

  public TqsStack () {}

  public TqsStack(int bound) {
    this.bound = bound;
  }

  public void push(T x) {
    if (size() == bound) throw new IllegalStateException();

    list.add(x);
  }

  public T pop() {
    T elem;

    try {
      elem = list.remove(size() - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }

    return elem;
  }

  public T peek() {
    T elem;

    try {
      elem = list.get(size() - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }

    return elem;
  }

  public int size() {
    return list.size();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

}
