package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class HashTable {

    private static class HashNode {

        String name;
        Type type;
        HashNode next;
    }

    private static final int HASH_TABLE_SIZE = 0x3fff;
    private static final HashNode[] TABLE = new HashNode[HASH_TABLE_SIZE];
    private static HashTable hashTable;

    private HashTable() {
    }

    public static HashTable getHashTable() {
        if (hashTable == null) {
            hashTable = new HashTable();
        }
        return hashTable;
    }

    private int getHashCode(String name) {
        int val = 0, i;
        for (char c : name.toCharArray()) {
            val = (val << 2) + (int) c;
            if ((i = (val & ~HASH_TABLE_SIZE)) != 0) {
                val = (val ^ (i >> 12)) & HASH_TABLE_SIZE;
            }
        }
        return val;
    }

    public Type getType(String name) {
        int index = getHashCode(name);
        HashNode node = TABLE[index];
        while (node != null) {
            if (node.name.equals(name)) {
                return node.type;
            }
            node = node.next;
        }
        return null;
    }

    public void addNode(Type type, String name) {
        HashNode newNode = new HashNode();
        newNode.type = type;
        newNode.name = name;
        newNode.next = null;
        int index = getHashCode(name);
        if (TABLE[index] == null) {
            TABLE[index] = newNode;
            return;
        }
        HashNode preNode = TABLE[index];
        while (preNode.next != null) {
            preNode = preNode.next;
        }
        preNode.next = newNode;
    }

    public void removeNode(String name) {
        int index = getHashCode(name);
        if (TABLE[index] == null) {
            return;
        }
        if (TABLE[index].name.equals(name)) {
            TABLE[index] = TABLE[index].next;
            return;
        }
        HashNode preNode = TABLE[index];
        while (preNode.next != null) {
            if (preNode.next.name.equals(name)) {
                preNode.next = preNode.next.next;
                break;
            }
            preNode = preNode.next;
        }
    }

    public boolean checkDuplicate(String name) {
        HashNode node = TABLE[getHashCode(name)];
        while (node != null) {
            if (node.name.equals(name)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }


}
