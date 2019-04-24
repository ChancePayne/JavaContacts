package com.jakeesveld.javacontacts;

public class CircularDictionary<T> {
    private T[] data;
    private String[] keys;
    private int pointer, length;

    public CircularDictionary(int size){
        data = (T[]) new Object[size];
        keys = new String[size];
        length = size;
        pointer = 0;
    }

    public void put(String key, T item){
        keys[pointer] = key;
        data[pointer] = item;
        increasePointer();
    }

    public T get(){
        //return data[pointer];
        T element = data[pointer];
        increasePointer();
        return element;
    }

    public T get(String key){
        int index = -1;
        for(int i = 0; i < keys.length; ++i){
            if(key.equals(keys[i])){
                index = i;
                break;
            }
        }
        return index == -1 ? null : data[index];
    }

    public int getLength() {
        return length;
    }

    private void increasePointer(){
        ++pointer;
        if(pointer >= data.length){
            pointer = 0;
        }
    }
}
