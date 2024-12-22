import org.example.consistenthashing.ConsistentHashing;

import java.util.Arrays;
import java.util.List;

public static void main(String[] args) {
    List<String> nodes = Arrays.asList("NodeA", "NodeB", "NodeC");
    ConsistentHashing ch = new ConsistentHashing(3, nodes);


    System.out.println("Initial Ring:");
    ch.displayHashRing();
    System.out.println(ch.getNodeCount());

    System.out.println("Key: key1 is mapped to: " + ch.getNode("key1"));
    System.out.println("Key: key2 is mapped to: " + ch.getNode("key2"));
    System.out.println("Key: key3 is mapped to: " + ch.getNode("key3"));

    System.out.println("Adding NodeD...");
    ch.addNode("NodeD");
    System.out.println(ch.getNodeCount());
    ch.displayHashRing();

    System.out.println("Removing NodeB...");
    ch.removeNode("NodeB");


    System.out.println("Key: key1 is mapped to: " + ch.getNode("key1"));
    System.out.println("Key: key2 is mapped to: " + ch.getNode("key2"));
    System.out.println("Key: key3 is mapped to: " + ch.getNode("key3"));
    ch.displayHashRing();
}