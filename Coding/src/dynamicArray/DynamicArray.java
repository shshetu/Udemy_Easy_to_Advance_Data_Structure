package dynamicArray;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable {
    // fields
    // Generic static Array
    private T[] array;
    private int len = 0; // length user thinks array is
    private int capacity = 0; // Actual array size

    // default constructor: initial capacity is 16
    public DynamicArray() {
        this(16);
    }

    // parameterized constructor
    public DynamicArray(int capacity) {
        // if capacity < 0 throw IllegalArgument Exception
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        // initialize the arr
        array = (T[]) new Object[capacity];
    }

    // M1. size()
    public int size() {
        return len;
    }

    // M2. isEmpty()
    public boolean isEmpty() {
        return size() == 0;
    }

    // M3. get(int index)
    public T get(int index) {
        return array[index];
    }

    // M4. set(int index, T elem): It will set specific element in a specific index
    public void set(int index, T elem) {
        array[index] = elem;
    }

    // M5. clear(): Set all the elements in that array to null
    public void clear() {
        for (int i = 0; i < len; i++) array[i] = null;
        len = 0;
    }

    // M6. void add(T element):
    public void add(T element) {
        // resize the Array
        if (len + 1 >= capacity) {
            // default capacity is: 1
            if (capacity == 0) capacity = 1;
                // double the capacity / double the size.
            else capacity *= 2;
            // create new array of double size
            T[] newArray = (T[]) new Object[capacity];
            // take all the elements of the array into new array
            for (int i = 0; i < len; i++) newArray[i] = array[i];
            // copy the new Array to the old array
            // array has extra nulls padded. since we are doubling the size
            array = newArray;
        }
        // add the new element at the last index of the array
        array[len++] = element;
    }

    // M7. T removeAt(int removalIndex)
    public T removeAt(int removalIndex) {
        // if removal operation tries to remove: less than 0th index value or greater than or equals to, (len-1) th index value , throw an IndexOutOfBoundsException
        if (removalIndex >= len || removalIndex < 0) throw new IndexOutOfBoundsException();
        // copy the removalIndex th value to a variable named: removedItem
        T removedItem = array[removalIndex];
        // create a new array named: newArray of length: len-1
        T[] newArray = (T[]) new Object[len - 1];
        // using for loop, copy all the element of the array to the newArray, where newArray length = array length -1
        for (int i = 0, j = 0; i < len; i++, j++)
            if (i == removalIndex)
                j--; // Skip over removalIndex, so that this variable contains 1 less element which we are going to remove
            else newArray[j] = array[i];
        // copy back all the newArray elements to the array. Here this array is of length: len -1
        array = newArray;
        // decrease the capacity by 1
        capacity = --len;
        // return : removedItem
        return removedItem;
    }

    //M8. boolean remove(Object obj):
    public boolean remove(Object obj) {
        // find the index of the given object
        int index = indexOf(obj);
        // if the object is not found return false
        if (index == -1) return false;
        // remove the obj and return true
        removeAt(index);
        return true;
    }

    //M9. int indexOf(Object obj):
    public int indexOf(Object obj) {
        // traverse all the elements of the array and check whether matches or not. If matches return the matching index, else return -1
        for (int i = 0; i < len; i++) {
            if (obj == null) {
                if (array[i] == null) return i;
            } else {
                if (obj.equals(array[i])) return i;
            }
        }
        return -1;
    }

    //M10. boolean contains(Object obj):
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    //M11. Overriding iterator():
    @Override
    public java.util.Iterator<T> iterator() {
        // Override: boolean hasNext()
        // Override: T next()
        // Override: remove()
        return new Iterator<T>() {
            // create a variable: index
            int index = 0;
            @Override
            public boolean hasNext() {
                return index<len;
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
            throw  new UnsupportedOperationException();
            }
        };
    }

    //M12. Overriding toString():


    @Override
    public String toString() {
       // if the array is empty, return []
        if(len == 0) return "[]";
        // else convert all the array elements to String and return the string
    else {
        // create a String builder, of length: len and start with appending: [
            StringBuilder stringBuilder = new StringBuilder(len).append("[");
            // using for loop append all the element, delimited by: ,
            for (int i = 0; i <len-1 ; i++) stringBuilder.append(array[i]).append(", ");
            // return the string builder by converting it to string
            return stringBuilder.append(array[len-1]).append("]").toString();

        }
    }
}
