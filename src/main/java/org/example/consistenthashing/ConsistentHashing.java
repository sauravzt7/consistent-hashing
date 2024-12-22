package org.example.consistenthashing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {

    private int numberOfReplicas;
    private List<String> nodes;
    SortedMap<Integer, String> hashRing = new TreeMap<>();

    public ConsistentHashing(int numberOfReplicas, List<String> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        for(String node : nodes) {
            addNode(node);
        }
    }

    public int hash(String key) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(key.getBytes());
            return ((bytes[3] & 0xFF) << 24) | ((bytes[2] & 0xFF) << 16) | ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
    //add a node to the hashRing
    public void addNode(String node) {
        for(int i = 0; i < numberOfReplicas; i++) {
            int hash = hash(node + i);
            hashRing.put(hash, node);
        }
    }
    public void removeNode(String node) {
        for(int i = 0; i < numberOfReplicas; i++) {
            hashRing.remove(hash(node + i));
        }
    }
    public int getNodeCount() {
        return this.hashRing.size();
    }

    public String getNode(String key) {
        if(hashRing.isEmpty()) return null;
        int hash = hash(key);

        if(!hashRing.containsKey(hash)){
            SortedMap<Integer, String> tail = hashRing.tailMap(hash);
            hash = tail.isEmpty() ? hashRing.firstKey() : tail.firstKey();
        }
        return hashRing.get(hash);
    }

    public void displayHashRing() {
        System.out.println("Hash Ring: " + hashRing);
    }

}


