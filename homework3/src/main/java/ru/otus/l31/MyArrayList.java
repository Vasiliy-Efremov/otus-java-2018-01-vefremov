package ru.otus.l31;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private int size;
    private Object[] content;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.content = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int size) {
        this.content = new Object[size];
    }

    public int size() {
        return size;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(content, size);
    }

    public boolean add(T t) {
        if (size < content.length) {
            content[size++] = t;
            return true;
        } else {
            Object[] array = new Object[((size * 3) / 2 + 1)];
            System.arraycopy(content, 0, array, 0, size);
            content = array;
            content[size++] = t;
            return true;
        }
    }

    private int calculateInputListSize(Collection<? extends T> c) {
        return c.toArray().length;
    }

    private int calculateNewListSize(Collection<? extends T> c) {
        int sizeOfInputArray = calculateInputListSize(c);
        return ((size + sizeOfInputArray) * 3) / 2 + 1;
    }

    public boolean addAll(Collection<? extends T> c) {
        Object[] inputArray = c.toArray();
        Object[] newArray = new Object[calculateNewListSize(c)];
        System.arraycopy(content, 0, newArray, 0, size);
        System.arraycopy(inputArray, 0, newArray, size, c.toArray().length);
        content = newArray;
        size += calculateInputListSize(c);
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        checkRange(index);
        Object[] inputArray = c.toArray();
        Object[] newArray = new Object[calculateNewListSize(c)];
        System.arraycopy(content, 0, newArray, 0, index);
        System.arraycopy(inputArray, 0, newArray, index, c.toArray().length);
        System.arraycopy(content, index, newArray, (calculateInputListSize(c) + (size - index)), size - index);
        content = newArray;
        size += calculateInputListSize(c);
        return true;
    }

    public T get(int index) {
        checkRange(index);
        return (T) content[index];
    }

    public T set(int index, T element) {
        checkRange(index);
        T oldElement = (T) content[index];
        content[index] = element;
        return oldElement;
    }

    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements ListIterator<T> {
        int cursor;
        int indexLastElement = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            cursor++;
            return (T) content[indexLastElement = cursor - 1];
        }

        @Override
        public void set(T t) {
            if (indexLastElement < 0) {
                throw new IllegalStateException();
            }
            try {
                MyArrayList.this.set(indexLastElement, t);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
        }

        @Override
        public void add(T t) {
        }
    }

    public Iterator<T> iterator() {
        return listIterator();
    }

    public boolean contains(Object o) {
        throw new NotImplementedException();
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new NotImplementedException();
    }

    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public T remove(int index) {
        throw new NotImplementedException();
    }

    public void add(int index, T element) {
        throw new NotImplementedException();
    }

    public int indexOf(Object o) {
        throw new NotImplementedException();
    }

    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
    }

    public ListIterator<T> listIterator(int index) {
        throw new NotImplementedException();
        //return null;
    }
}
