package week04;

import java.util.Random;

class 设计跳表 {

    private final Node head;

    private final static int MAX_LEVEL = 32;

    private int currentLevel = 0;

    public 设计跳表() {
        head = new Node(MAX_LEVEL);
    }

    public boolean search(int target) {
        return findNode(target) != null;
    }

    public void add(int num) {
        int level = randomLevel();
        Node newNode = new Node(num, level);
        Node preNode = findPreNode(num, level);
        for (int i = currentLevel; i >= 0; i--) {
            if (i > level) {
                continue;
            }
            // 从上一层的位置往右找到 val > target 的前一个节点
            while (preNode.next[i] != null && num > preNode.next[i].val) {
                preNode = preNode.next[i];
            }
            newNode.next[i] = preNode.next[i];
            preNode.next[i] = newNode;
        }

        // 层数大于当前层数, 直接从head指向该node
        if (level > currentLevel) {
            for (int i = currentLevel + 1; i <= level; i++) {
                head.next[i] = newNode;
            }
            currentLevel = level;
        }
    }

    public boolean erase(int num) {
        NodeInfo preNodeInfo = findPreNode(num);
        if (preNodeInfo == null) {
            return false;
        }
        Node preNode = preNodeInfo.node;
        for (int i = preNodeInfo.level; i >= 0; i--) {
            // 后面没有节点, 直接处理下一层
            if (preNode.next[i] == null) {
                continue;
            }
            // 从上一层的位置往右找到 val > target 的前一个节点
            while (num > preNode.next[i].val) {
                preNode = preNode.next[i];
            }
            preNode.next[i] = preNode.next[i].next[i];
        }
        return true;
    }

    private int randomLevel() {
        int level = 0;
        Random random = new Random();
        while (level < MAX_LEVEL && random.nextInt(2) > 0) {
            level++;
        }
        return level;
    }

    /**
     * 在指定level层查找target前一个节点
     */
    private Node findPreNode(int target, int level) {
        Node node = head;
        while (node.next[level] != null && target > node.next[level].val) {
            node = node.next[level];
        }
        return node;
    }

    /**
     * 在所有的层查找target前一个节点
     */
    private NodeInfo findPreNode(int target) {
        Node node = head;
        int level = currentLevel;
        while (level >= 0) {
            while (node.next[level] != null && target > node.next[level].val) {
                node = node.next[level];
            }
            if (node.next[level] != null && node.next[level].val == target) {
                // 连同所在层级一块返回
                return new NodeInfo(node, level);
            }
            level--;
        }
        return null;
    }

    /**
     * 在指定所有的层查找target是否存在
     */
    private Node findNode(int target) {
        Node node = head;
        int level = MAX_LEVEL;
        while (level >= 0) {
            while (node.next[level] != null && target >= node.next[level].val) {
                node = node.next[level];
            }
            if (node.val == target) {
                return node;
            }
            level--;
        }
        return null;
    }

    public static void main(String[] args) {
//        Skiplist skiplist = new Skiplist();
//        skiplist.add(1);
//        skiplist.add(2);
//        skiplist.add(3);
//        boolean search = skiplist.search(0);
//        skiplist.add(4);
//        boolean search1 = skiplist.search(1);
//        skiplist.add(5);
//        boolean search2 = skiplist.search(3);
//        boolean search3 = skiplist.search(6);
//
//        System.out.println(search);

        设计跳表 skiplist = new 设计跳表();
        skiplist.add(9);
        skiplist.add(4);
        skiplist.add(5);
        skiplist.add(6);
        skiplist.add(9);
        boolean erase = skiplist.erase(2);
        boolean erase1 = skiplist.erase(1);
        skiplist.add(2);
        skiplist.search(7);
        skiplist.search(4);
        skiplist.add(5);
        boolean erase2 = skiplist.erase(6);
    }
}

class Node {
    int val;
    Node[] next;

    public Node(int val, int level) {
        this.val = val;
        this.next = new Node[level + 1];
    }

    public Node(int level) {
        this.val = -1;
        this.next = new Node[level + 1];
    }
}

class NodeInfo {
    Node node;
    int level;

    public NodeInfo(Node node, int level) {
        this.node = node;
        this.level = level;
    }
}

