package com.lambdaschool.javacontacts;

public class CircularDictionary<E> {
    private E[] data;
    private String[] keys;
    private int pointer, length;

    public CircularDictionary(int size) {
        data = (E[]) new Object[size];
        keys = new String[size];
        length = size;
        pointer = 0;
    }

    public void put(String key, E item) {
        keys[pointer] = key;
        data[pointer] = item;
        increasePointer();
    }

    private void increasePointer() {
        ++pointer;
        if(pointer >= data.length) {
            pointer = 0;
        }
    }

    public int getLength() {
        return length;
    }
}
