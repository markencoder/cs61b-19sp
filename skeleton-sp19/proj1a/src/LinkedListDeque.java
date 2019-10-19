public class LinkedListDeque<T> {
    public int size = 0;
    public IntNode sentinel;

    /**
     * constructor for cLass IntNode
     */
    public class IntNode {
        public T item;
        public IntNode next;
        public IntNode prev;

        public IntNode(IntNode p, T i, IntNode n) {
        prev = p;
        item = i;
        next = n;
    }

    }

    /**
     * constructor for class LinkedListDeque
     */
    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x){
        IntNode node = new IntNode(sentinel, x, sentinel);
        sentinel.next = node;
        sentinel.prev = node;
        size += 1;

    }

    /**
     * methods for LinkedListDeque
     */
    public void addFirst(T x) {
        size += 1;
        IntNode node = new IntNode(sentinel, x, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
    }

    public void addLast(T x) {
        size += 1;
        IntNode node = new IntNode(sentinel.prev, x, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = 0;
        IntNode p = sentinel;
        while (i <= size) {
            System.out.print(p.next.item + " ");
            p = p.next;
            i += 1;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last;
        }

    }

    public T get(int i) {
        int t = 0;
        IntNode p = sentinel;
        if(i > size){
            return null;
        }else {
            while (t != i) {
                t += 1;
                p = p.next;
            }
            return p.next.item;
        }
    }

    public LinkedListDeque(LinkedListDeque other) {
        IntNode sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        IntNode p = other.sentinel;
        while(p.next != other.sentinel){
            addLast(p.next.item);
            p = p.next;
            size +=1;
        }
    }

    public  T help_getRecursive(IntNode p, int index){
        if(isEmpty()){
            return null;
        }else {
            if (index == 0) {
                return p.next.item;
            } else {
                p = p.next;
                return help_getRecursive(p.next, index-1);
            }
        }

    }
    public T getRecursive(int index) {
        return help_getRecursive(sentinel,index);
    }
}