package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class LruCache {
  private static class Node {
    Node(int k, int v, Node n, Node p) {
      key = k;
      value = v;
      next = n;
      prev = p ;
    }

    Integer value;
    Integer key;
    Node next;
    Node prev;
  }

  Node head, tail;
  Map<Integer, Node> hm;
  int capacity;

  LruCache(final int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException("cannot be empty");
    this.capacity = capacity;
    head = new Node(-1,-1,null, null);
    tail = new Node (-10,-10,null, null);
    head.next = tail;
    tail.prev = head;
    hm = new HashMap<>(capacity);
  }

  private void addFront(Node n){

    head.next.prev = n;
    n.next = head.next;
    head.next = n;
    n.prev = head;
  }

  private void removeAny(Node n){
    n.next.prev = n.prev;
    n.prev.next = n.next;
  }

  private void moveToFront(Node n){
    removeAny(n);
    addFront(n);
  }

  public Integer lookup(Integer key) {
    // TODO - you fill in here.
    if (!hm.containsKey(key)) return -1 ; //throw new NoSuchElementException("key not found");

    Node n = hm.get(key);
    moveToFront(n);
    return n.value;

  }

  public void insert(Integer key, Integer value) {
    // TODO - you fill in here.
    if (hm.containsKey(key)){
      moveToFront(hm.get(key));
      return;
      // update value if needed
//      Node n = hm.get(key);
//      n.value = value;
    }

    Node n = new Node(key, value, null, null);
    if(hm.size()==capacity) {
      hm.remove(tail.prev.key);
      removeAny(tail.prev);
    }
    hm.put(key, n);
    addFront(n);

  }

  public Boolean erase(Object key) {
    // TODO - you fill in here.
    if (!(key instanceof Integer)) return false;
    if (hm.isEmpty() || !hm.containsKey(key)) return false;
    Node n = hm.get(key);
    hm.remove(key);
    removeAny(n);
    return true;
  }

  @EpiUserType(ctorParams = {String.class, int.class, int.class})
  public static class Op {
    String code;
    int arg1;
    int arg2;

    public Op(String code, int arg1, int arg2) {
      this.code = code;
      this.arg1 = arg1;
      this.arg2 = arg2;
    }
  }

  @EpiTest(testDataFile = "lru_cache.tsv")
  public static void runTest(List<Op> commands) throws TestFailure {
    if (commands.isEmpty() || !commands.get(0).code.equals("LruCache")) {
      throw new RuntimeException("Expected LruCache as first command");
    }
    LruCache cache = new LruCache(commands.get(0).arg1);
    for (Op op : commands.subList(1, commands.size())) {
      int result;
      switch (op.code) {
      case "lookup":
        result = cache.lookup(op.arg1);
        if (result != op.arg2) {
          throw new TestFailure("Lookup: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      case "insert":
        cache.insert(op.arg1, op.arg2);
        break;
      case "erase":
        result = cache.erase(op.arg1) ? 1 : 0;
        if (result != op.arg2) {
          throw new TestFailure("Erase: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      default:
        throw new RuntimeException("Unexpected command " + op.code);
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LruCache.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
