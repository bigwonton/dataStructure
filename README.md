[TOC]

# 数据结构

## 数组 Array

`Array.Array`

 返回值类型 | 方法名 | 备注
---| --- |--
int | getSize()
boolean | isEmpty()
int | getCapacity()
void | add(int index, E e)
void | addFirst(E e)
void | addLast(E e)
E | remove(int index)
E | removeFirst()
E | removeLast()
E | get(int index)
set | set(int index, E e)
boolean | contains(E e)
int | find(E e)
String | toString()
void | resize(int newCapacity) | private

- 支持泛型
- 支持动态调整大小，默认大小为10
 
- ==时间复杂度==

 方法 | 时间复杂度 | 备注
---| --- |--
增 add | O(n)
增 addLast | O(1)
删 remove | O(n)
删 removeLast | O(1)
改 set  | O(1)
查 contains find | O(n)

- **数组删除元素一般来说是O(n)的复杂度，但如果是addLast()和removeLast()的操作，则是O(1)**

- resize()方法策略
    - 容量满，扩容为2倍
    - size小到容量1/4，减少为1/2
    - 所以addLast()和removeLast()操作的复杂度才是O(1)
    - 如果将策略改成比较着急调整的方式，那么addLast()和removeLast()的时间复杂度会变成O(n)，这种叫做**复杂度震荡**

## 栈 Stack 

- 线性结构
- 特点：同一端添加元素，删除元素（栈顶）
- 应用
    - Undo操作（撤销）
    - 括号匹配——编译器
    - 程序调用时的系统栈
- 栈的实现（只要符合栈的特性，底层可以有多种实现方式）
    - 数组实现
    - 链表实现

### Stack 接口
`Stack.Stack`
- interface


 返回值类型 | 方法名 | 备注
---| --- |--
int | getSize()
boolean | isEmpty()
void | push(E e)
E | pop()
E | peek()

### 基于数组实现栈

`Stack.ArrayStack`

- 数组尾部操作复杂度是O(1)，因此明显选择==数组尾部进栈，数组尾部出栈==是最佳方式


## 队列 Queue

- 线性结构
- 队尾入队，队首出队
- 队列的实现（只要符合队列的特性，底层可以有多种实现方式）
    - 数组实现
    - 循环数组实现
    - 链表实现
    

### Queue 接口

返回值类型 | 方法名 | 备注
---| --- |--
int | getSize()
boolean | isEmpty()
void | enqueue(E e)
E | dequeue()
E | getFront()

### 基于数组实现队列

`Queue.ArrayQueue`

- ==时间复杂度==

 方法 | 时间复杂度 | 备注
---| --- |--
增 enqueue | O(1) | 均摊
删 dequeue | O(n) | 
查 getFront | O(1)
 getSize  | O(1)
 isEmpty | O(1)

### 基于循环数组实现队列

`Queue.LoopQueue`

- 因为循环数组要用到的操作与`Array`不同，因此，自行在内部维护一个==data==数组、==head== 和 ==tail== 变量，而不是使用封装好的`Array`类。
- 循环队列的 head 和 tail 的位置+1或-1时，都需要进行 `%data.length（取余）操作`
- ==resize==调整内部数组大小的时候，不要忘记调整新数组的head和tail变量
- ==队列为空==的判断
    - `size == 0`
    - `head == tail`
- ==队列满==的判断
    - `(tail + 1) % length = head`
    - 上述说明，**循环队列会浪费1个空间**，`capacity = data.length - 1`

- ==时间复杂度==

 方法 | 时间复杂度 | 备注
---| --- |--
增 enqueue | O(1) | 均摊
删 dequeue | O(1) | 均摊
查 getFront | O(1)
 getSize  | O(1)
 isEmpty | O(1)

    从时间复杂度来看，循环数组可以解决普通数组实现队列出队时的O(n)问题


## 链表 LinkedList

`LinkedList.LinkedList`

- 线性结构
- 动态数组及底层依托于数组实现的栈和队列，都是依靠==resize==来解决固定容量问题，而链表，才是真正的==动态数据结构==，但与此同时，牺牲了随机访问的能力
- 与数组一样，链表可以作为基本的辅助数据结构，实现其他复杂数据结构

- 链表的实现
    - 依托于“节点”（Node，维护数据、相邻节点的引用）

- ==时间复杂度==


 方法 | 时间复杂度 | 备注
---| --- |--
增 add | O(n) | 均摊
增 addLast | O(n) | 
增 addFirst | O(1) 
删 remove | O(n) | 均摊
删 removeLast | O(n) | 
删 removeFirst | O(1) | 
查 get | O(n)
查 getLast | O(n)
查 getFirst | O(1)

    如果只对链表头部进行操作，那么复杂度为O(1)

### 基于链表实现栈 

`LinkedList.LinkedListStack`

- 链表头部操作复杂度 O(1)，栈只对栈顶操作，因此将链表头部作为栈顶


### 基于链表实现队列

- 因为队列需要对两头进行操作，所以不用之前实现的`LinkedList`，而是自行维护一个具有==头节点head==和==尾节点tail==的链表

- ==链表的尾部删除操作不方便==，因此
    - 链表头部 head 端 删除
    - 链表尾部 tail 端 添加

- 添加操作
    - 队列为空，需要同时维护 head 和 tail
- 删除操作
    - 队列删除后为空，需要额外维护 tail
    

##  链表中删除元素的应用

    Leetcode 203
    从链表中删除指定元素，如果该元素出现不止一次，也都要删除

    Example:

    Input:  1->2->6->3->4->5->6, val = 6
    Output: 1->2->3->4->5
    
### 常规思路

- 首先检查头节点开始的元素，是否有要删除的
- 然后，将头节点作为开始检查的prev节点，依次检查后续节点

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {

        // 头节点需要删除
        while (head != null && head.val == val) {
            ListNode node = head;
            head = head.next;
            node.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            // 如果是找到需要删除的元素了，不需要进行prev = prev.next 操作，否则会漏掉元素未检查
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }
}
```

上面的做法，其实有一些是在处理被删除的游离元素，其实这里可以不用处理，那么可以写成：
```
class Solution1 {
    public ListNode removeElements(ListNode head, int val) {

        // 头节点需要删除
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            // 如果是找到需要删除的元素了，不需要进行prev = prev.next 操作，否则会漏掉元素未检查
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }


}
```


### 使用虚拟头节点，简化特殊情况处理


```java
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {

        // 创建虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

}
```

## 二分搜索树 Binary Search Tree

### 树

- 常见的天然树结构
    - 文件夹
    - 公司组织架构
- 优点
    - 高效处理问题
- 几种树
    - 二分搜索树 BST
    - 平衡二叉树 AVL；红黑树
    - 堆； 并查集
    - 线段树；Trie（字典树，前缀树）

### 二叉树

- 动态数据结构
- 具有唯一根节点
- 和链表一样，依赖于“节点”Node，区别是二叉树节点里维护的是左子树和右子树


```java
class Node {
    E e;
    Node left;
    Node right;
}
```

- 二叉树不一定是满的
- ==二分搜索树==是特殊的二叉树
    - 每个节点的值满足
        - `> `其左子树的所有节点的值
        - `<` 其右子树的所有节点的值
    - 每一棵子树也是二分搜索树

### 二分搜索树

`BinarySearchTree.BST`

 返回值类型 | 方法名 | 备注
---| --- |--
int | getSize()
boolean | isEmpty()
void | add(E e)
boolean | contains(E e)
void | preOrder() | 前序遍历
void | inOrder() | 中序遍历
void | postOrder() | 后序遍历
void | preOrderNR() | 前序遍历（非递归，借助stack）
void | levelOrder() | 层序遍历（非递归，借助queue）
E | removeMin() | 终止条件 没有左子树
E | removeMax() | 终止条件 没有右子树
void | remove() | 值得看，直接看代码注释
E | getMin()
E | getMax()





- 几个约定
    - 这里二分搜索树默认不包含重复元素（也可以包含，看具体情况）
    - 这里的操作主要关注==递归实现==

- 递归
    - 递归都有==终止条件==，在具体写代码的时候，可以先将终止条件列出来，剩下情况再递归，这样思路会很清晰，以add方法为例：

- **add 写法一**
```java
 /**
 * 向树中添加元素
 * @param e
 */
public void add(E e) {
    if (isEmpty()) {
        root = new Node(e);
        size ++;
        return;
    }

    add(root, e);
}

// 内部使用递归添加元素
private void add(Node node, E e) {
    // 终止条件
    if (node.e.equals(e)) {
        return;
    } else if (e.compareTo(node.e) < 0 && node.left == null) {
        node.left = new Node(e);
        size ++;
        return;
    } else if (e.compareTo(node.e) > 0 && node.right == null){
        node.right = new Node(e);
        size ++;
        return;
    }

    // 递归
    if (e.compareTo(node.e) < 0) {
        add(node.left, e);
    } else {
        add(node.right, e);
    }
    
}
```

- **add 写法二**
    
    ==每次递归的时候，都将处理完成后的子树的根节点返回==

```java
public void add(E e) {
    if (isEmpty()) {
        root = new Node(e);
        size ++;
        return;
    }

    root = add(root, e);
}

// 每次递归，将处理完成后的子树的根结点返回
private Node add(Node node, E e) {
    // 终止条件 插入新节点
    if (node == null) {
        size ++;
        return new Node(e);
    }

    // 递归
    if (e.compareTo(node.e) < 0) {
        node.left = add(node.left, e);
    } else if (e.compareTo(node.e) > 0) {
        node.right =add(node.right, e);
    }

    return node;
}
```

