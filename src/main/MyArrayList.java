package main;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public  class MyArrayList<T> extends AbstractCollection<T> {
    private int size;
    T[] arr;

    public MyArrayList( Collection<T> collection) {
        this.size = collection.size();
//        arr = new [size];
    }



    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
