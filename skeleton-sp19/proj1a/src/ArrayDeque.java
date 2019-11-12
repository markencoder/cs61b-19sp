public class ArrayDeque<T> {
    public int size;
    public T[] items;
    public static int INIT_CAPACITY;
    public static final int FACTOR=2;
    public static final double MIN_USAGE_RATIO = 0.25;
    public int nextFirst;
    public int nextLast;
    public ArrayDeque(){
        T[] items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    public ArrayDeque( ArrayDeque other){
        T[] items = (T[]) Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        int p = other.nextFirst;
        for(int i=0; i<other.size;i++){
            items[plusone(p)]=other.items[plusone(p)];
            p=plusone(p);
        }
    }
    public void resize(int CAPACITY){
        T[] a = (T[]) new Object(CAPACITY);
        int p = plusone(nextFirst);
        for (int i =0;i<items.length;i++){
            a[i] = items[p];
            p = plusone(p);
        }
        nextFirst = CAPACITY -1;
        nextLast = size;
        items = a;
    }
    public int plusone(int nextFirst){
        return (nextFirst % items.length) + 1;
    }
    public int minusone(int nextLast){
        return (nextLast - 1 +items.length) % items.length;
    }
    public void addFirst(T x){
        if(size == items.length){
            resize(size*FACTOR);
        }
        items[nextFirst] = x;
        size +=1;
        nextFirst = minusone(nextFirst);
    }
    public void addLast(T x){
        if(size == items.length){
            resize(size*FACTOR);
        }
        items[nextLast] = x;
        size +=1;
        nextLast = plusone(nextLast);
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void printDeque(){
        int p =nextFirst;
        while (p!=minusone(nextLast)){
            System.out.print(items[plusone(p)]);
            p=plusone(p);
        }
        System.out.println();
    }
    public T get(int i){
        if (i>size){
            return null;
        }else {
            int index = (plusone(nextFirst) + i) % size;
            return items[i];
        }
    }
    public T getLast(){
        int last = minusone(nextLast);
        return items[last];
    }
    public int size(){

        return size;
    }
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }else {
            T first = items[plusone(nextFirst)];
            items[plusone(nextFirst)] = null;
            nextFirst = plusone(nextFirst);
            size -=1;
            return first;
        }
    }
    public T removeLast(){
        T last = items[minusone(nextLast)];
        items[minusone(nextLast)]=null;
        nextLast = minusone(nextLast);
        size -=1;
        return last;
    }
}
