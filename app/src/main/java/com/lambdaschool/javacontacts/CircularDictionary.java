package com.lambdaschool.javacontacts;

public class CircularDictionary<E> {
    private E[] data;
    private String[] keys;
    private int pointer,length;

    public CircularDictionary(int size){
        data = (E[]) new Object[size];
        keys = new String[size];
        length = size;
        pointer = 0;
    }

    public void put(String key, E item){
        keys[pointer] = key;
        data[pointer] = item;
    }


    public E get(){
        E element = data[pointer];
        increasePointer();
        return element;
    }

    public E get(String key){
        int index = -1;
        for(int i = 0; i < keys.length; ++i){
            if(key.equals(keys[i])){
                index = i;
                break;
            }
        }
        return index == -1 ? null : data[index];
    }

    private void increasePointer() {
        ++pointer;
        if (pointer >= data.length) {
            pointer = 0;
        }
    }
}
