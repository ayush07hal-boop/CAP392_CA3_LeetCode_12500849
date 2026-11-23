import java.util.*;

class LFUCache {

    // Static nested class
    private static class Node {
        final int key;
        int value;
        int frequency;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    private final int capacity;
    private int minFrequency;

    private final Map<Integer, Node> nodes;
    private final Map<Integer, LinkedHashSet<Integer>> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;

        nodes = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!nodes.containsKey(key)) {
            return -1;
        }

        Node node = nodes.get(key);
        increaseFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (nodes.containsKey(key)) {
            Node node = nodes.get(key);
            node.value = value;
            increaseFrequency(node);
            return;
        }

        if (nodes.size() == capacity) {
            LinkedHashSet<Integer> set = freqMap.get(minFrequency);

            int removeKey = set.iterator().next();
            set.remove(removeKey);

            if (set.isEmpty()) {
                freqMap.remove(minFrequency);
            }

            nodes.remove(removeKey);
        }

        Node newNode = new Node(key, value);

        nodes.put(key, newNode);

        freqMap.computeIfAbsent(
            1, k -> new LinkedHashSet<>()
        ).add(key);

        minFrequency = 1;
    }

    private void increaseFrequency(Node node) {
        int oldFreq = node.frequency;

        LinkedHashSet<Integer> oldSet = freqMap.get(oldFreq);
        oldSet.remove(node.key);

        if (oldSet.isEmpty()) {
            freqMap.remove(oldFreq);

            if (minFrequency == oldFreq) {
                minFrequency++;
            }
        }

        node.frequency++;

        freqMap.computeIfAbsent(
            node.frequency, k -> new LinkedHashSet<>()
        ).add(node.key);
    }
}
