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
    

###  链表中删除元素的应用

    Leetcode 203
    从链表中删除指定元素，如果该元素出现不止一次，也都要删除

    Example:

    Input:  1->2->6->3->4->5->6, val = 6
    Output: 1->2->3->4->5
    
#### 常规思路

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


#### 使用虚拟头节点，简化特殊情况处理


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

## 集合和映射 

`SetAndMap.Set`

- 不能添加重复元素
- 典型应用
    - 客户统计
    - 词汇量统计


返回值 | 方法名
-- | --
void | add(E)
void | remove()
boolean | contains(E)
int | getSize()
boolean | isEmpty()



### 基于二分搜索树实现集合 BSTSet

`SetAndMap.BSTSet`

### 基于链表实现集合 LinkedListSet
`SetAndMap.LinkedListSet`

### 两种集合实现的复杂度分析

- ==时间复杂度==

 方法 | LinkedListSet | BSTSet | BSTSet最差
---| --- |-- | --
增 add | O(n) | O(logn) | O(n)
删 remove | O(n) |O(logn) | O(n)
查 contains | O(n) | O(logn) | O(n)

    最差情况是，BST退化成链表，比如说按照，1,2,3,4,5……的顺序插入，形成二分搜索树
    

### 基于二分搜索树实现映射 BSTMap

`SetAndMap.BSTMap`

### 基于链表实现映射 LinkedListMap

`SetAndMap.LinkedListMap`

### 两种映射实现的复杂度分析

- ==时间复杂度==

 方法 | LinkedListMap | BSTMap | BSTSet最差
---| --- |-- | --
增 add | O(n) | O(logn) | O(n)
删 remove | O(n) |O(logn) | O(n)
改 set | O(n) |O(logn) | O(n)
查 get | O(n) | O(logn) | O(n)
查 contains | O(n) | O(logn) | O(n)


- `BSTMap`是**有序映射**
- `LinkedListMap`是**无序映射**（基于哈希表的实现，也是无序映射）

## 优先队列和堆

`Queue.PriorityQueue`

- 动态
- 可以使用不同的底层实现


- ==时间复杂度==


数据结构  | 入队 | 出队（拿出最大元素）
---| --- |--
普通线性结构 | O(1) | O(n)
顺序线性结构 | O(n) | O(1)
堆 | O(logn) | O(logn) 



### 堆的基本结构

- 是一棵树
- 堆中某个节点的值总是不大于其父节点的值（最大堆）或不小于其父节点的值（最小堆）


#### 二叉堆

- 是堆
- 是完全二叉树（把元素顺序排列成树的形状）
- 约定——这里都用最大堆
- 可以用数组实现二叉堆（父节点，孩子节点的索引很好计算）

`PriorityQueueAndHeap.MaxHeap`


返回值 | 方法名 | 备注
-- | -- | --
int | getSize()
boolean | isEmpty()
int | parent(int) | private 返回父节点的索引
int | leftChild(int) | private 返回左孩子的索引
int | rightChild(int) | private 返回右孩子的索引
void | add(E) 
E | extractMax() | 向堆中取出元素（最大值）
void | heapify() | 将任意数组整理成堆的形状
void | replace(E e) | 取出最大元素，放入一个新的元素


- 假设需要将具有n个元素的数组的元素插入一个空堆，有两种方式
    - 依次将n个元素插入空堆
    - 对数组进行heapify()

    
**时间复杂度对比：**

将n个元素依次插入空堆  | heapify()
---| --- 
O(nlogn) | O(n) 

- **因此在用数组传入构造函数构造堆的时候，就可以利用heapify()来初始化**



### 基于堆的优先队列

`PriorityQueueAndHeap.PriorityQueue`

- 这么做的原因在上面介绍有点队列时说了，出队和入队的时间复杂度都是O(logn)
- 关于优先级，



## 线段树 Segment Tree

- 区间树
- 经典问题
    - 区间染色
    - 区间查询（基于区间的统计查询）

- 对于**给定区间**
    - 更新——更新区间中的一个元素或者一个区间的值
    - 查询——查询一个区间[i,j]的最大值、最小值或者求和

- ==时间复杂度==

操作 | 使用数组实现 | 使用线段树
-- | -- | --
更新区间 | O(n) | O(logn)
查询区间 | O(n) | O(logn)


- 线段树不是完全二叉树
- 是平衡二叉树（最大深度与最小深度之差最多为1）
- 可以用数组表示，看作满二叉树（没有值的地方标记为null即可）


### 线段树内部数组的大小

- 如果区间有n个元素，数组表示需要有多少个节点？
    - 最好情况 2n-1（约为2n）
    - 最坏情况 4n
    - 我们的线段树不考虑添加元素，即**区间固定**，使用==4n==的静态空间即可

### 线段树的实现

`SegmentTree.SegmentTree`

- 内部使用数组实现
    - data数组保存构建线段树的元素
    - tree中则保存按照线段树的索引的顺序，保存的线段树的所有节点

- 关于容量
    - 这里初始化创建数组的时候，tree的容量设置为4*n，其中n为元素个数。
```
private E[] data;
private E[] tree;
```

- 需要借助`Merger`接口的`merge`方法，它定义了线段树处理的具体业务（两个孩子节点如何融合成父节点）

返回值 | 方法名 | 备注
-- | -- | --
int | getSize()
E | get()
int | leftChild(int) | private 返回左孩子节点的索引
int | rightChild(int) | private 返回右孩子节点的索引
E | query(int queryL, int queryR) | 返回区间[queryL, queryR]
void | buildSegmentTree(int index, int l, int r) | private 构建线段树的内部方法，终止条件为`l==r`
void | set(int index, E e) | 更新index位置的值


### 懒惰更新

- 如果线段树有若干节点需要更新，那么与他相关的子节点和父节点都要更新，如果每次都全部更新，会非常慢，所以可以采取懒惰更新的方法
    - 使用lazy数组记录未更新的内容
    - 下次还要更新的时候，查询lazy数组中是否有该节点



### 与线段树有关的练习题

- Leetcode 303
    - 使用线段树
    - 不使用线段树，sum[] 数组预处理即可


- Leetcode 307
    - 线段树，主要考虑到需要update
    - 不使用线段树，如果使用sum[]预处理，结果会超时，不是一个好方法

## Trie 字典树 前缀树

`Trie.Trie`

- 适用场景，如：通讯录

通讯录 | 字典 | Trie
-- | --|--
n个条目|如果有n个条目使用树结构，查询的时间复杂度是O(n)| 查询每个条目的时间复杂度，和字典中一个有多少个条目无关。时间复杂度为O(w)，其中w为查询单词的长度

- 内部Node类
    - 每个节点都有若干个指向下个节点的指针，放在map里

```
class Node {
    boolean isWord; //标注是否为单词结尾
    Map<Character, Node> next;  //子节点的集合
}

```

返回值 | 方法名 | 备注
-- | -- | --
int | getSize()
void | add(String word)
boolean | contains(String word)
boolean | isPrefix(String prefix)


#### Trie相关练习题
- Leetcode 211 Add and Search Word

```
1、利用Trie结构
2、search() 方法利用递归来做
```

- Leetcode 677
```
/**
 * Trie中使用isWord的布尔类型来表示一个单词的结尾
 * 这里，使用value，来表示单词的结尾，同时表示单词的权重
 * 只要找到单词，然后将权重相加就行
 ```

### 基于哈希表或者数组的Trie

- 上面Trie的实现，是基于`TreeMap`的
- 它也可以基于哈希表或者是数组实现

- 在数据量较大的情况下，`TreeMap`的性能是很好的（底层是红黑树），但是在数据量较小的情况下，使用数组实现，比`TreeMap`和`HashMap`更快，因为他不需要复杂的旋转操作，也不需要计算哈希值。


## 并查集 Union Find


- 奇怪的树结构，孩子指向父亲
- 解决连接问题
    - 网络中节点间的连接状态
    - 数学中的集合类的实现
- 连接问题和路径问题
    - 连接问题比路径问题要求包含的内容少

`UnionFind.UF`

返回值 | 方法名 | 备注
-- | -- | --
int | getSize()
boolean | isConnected(int p, int q)
void | unionElements(int p, int q)


### Quick Find

`UnionFind.UnionFind1`

- 内部使用一个数组，存储id值，id相同，则表示connected

- ==时间复杂度==

方法 | 时间复杂度 | 备注
---| --- |--
isConnected | O(1) | 查找很快
unionElements | O(n) | 


### Quick Union

`UnionFind.UnionFind2`


- 使用数组，存储父节点的位置
- 可以理解为：构建一棵指向父节点的树
- 牺牲了find的时间复杂度，但union变快
- 查找和合并的时间复杂度均与树的高度h有关

- unionElements时，`parent[p] = qRoot;`,直接让p指向q的根节点
- 但这么粗暴的做法，可能会导致树的不平衡，具体优化见下


### 并查集的优化

#### 基于size的优化

`UnionFind.UnionFind3`


- 增加sz[i]参数，表示以i为根的集合中的元素个数
- 合并时，将sz小的集合挂到sz大的集合上
- 合并式，不要忘记维护sz

#### 基于rank的优化

`UnionFind.UnionFind4`

- rank[i]表示以i为根的集合所表示的树的层数
- rank 其实指示一种排名的参考，并不一定等于树的层数，不需要特别精确（尤其是在维护rank需要耗费比较大的成本的情况下）
- 将rank低的集合合并到rank高的集合上

#### 路径压缩

##### 查找过程中将高度压缩
`UnionFind.UnionFind5`

- 查找过程中，将元素挂在父亲的父亲节点上
`parent[p] = parent[parent[p]];`




##### 路径压缩中使用递归
`UnionFind.UnionFind6`

- 查找过程中，直接使用递归，将元素挂到根节点上
`parent[p] = find(parent[p]);`

#### 时间复杂度分析

- 严格意义上，时间复杂度为 O(log*n)  
    - log*n = 0                   (if n <=1)
    - log*n = 1 + log*(logn)      (if n > 1)

- 比O(logn)快
- ==**似乎是O(1)级别的**==


## 平衡二叉树与AVL

- 二分搜索树——可能会退化成链表形式
- 解决办法——**平衡二叉树**
- **AVL树**——最早提出的自平衡二分搜索树结构

### 什么是平衡二叉树

- 对于任意一个节点，左子树和右子树的高度差不能超过1
- 平衡二叉树的高度和节点数量之间的关系是O(logn)的
- 如何计算平衡二叉树的左右子树高度呢？
    - 标注节点的高度
    - 计算平衡因子（左右子树高度差）


### AVL树

- 是平衡二叉树
- 是二分搜索树

`AVLTree.AVLTree`

- 内部类Node需要变量存储
    - key/value
    - 左子树，右子树
    - 高度


返回值 | 方法名 | 备注
-- | -- | --
int | getSize()
boolean | isEmpty()
int | getHeight() | private 计算高度
int | getBalanceFactor() | private 计算平衡因子
void | add(K key, V value)
boolean | contains(K key)
V | get(K key)
void | set(K key, V value)
V | remove(K key)
boolean | isBST() | 该二叉树是否是一棵二分搜索树
boolean | isBalanced() | 该二叉树是否是一棵平衡二叉树




- 复习一下**二分搜索树删除某个节点**的步骤
    - 终止条件 `node == null`
    - key < node.key
    - key > node.key
    - key = node.key 找到了待删除节点
        - 待删除节点的左子树为空，返回右子树
        - 待删除节点的右子树为空，返回左子树
        - 待删除节点的左右子树都不为空
            - 1. 找到右子树的最小节点 successor
            - 2. 删除右子树的最小节点
            - 3. 用successor替代待删除节点
            - 4. 处理待删除节点（游离节点）
            - 5. 返回 successor


#### AVL树的左旋和右旋

- 左旋和右旋都是为了维护平衡
- 什么时候维护平衡？
    - 加入节点后，沿着节点向上维护平衡性
        - 插入的元素在不平衡的节点的左侧的左侧LL——**右旋**
        - 插入的元素在不平衡的节点的右侧的右侧RR——**左旋**
        - 插入的元素在不平衡的界定的左侧的右侧LR——**先左旋后右旋**
        - 插入的元素在不平衡的界定的右侧的左侧RL——**先右旋后左旋**
    - 删除元素后，沿着节点向上维护平衡性(同加入节点)

 
### 基于AVL的集合和映射

- `AVLTree.AVLSet`
- `AVLTree.AVLMap`
- 增删改查的时间复杂度都是O(logn)



## 红黑树

- 是二分搜索树
- 红黑树与2-3数具有等价性

### 2-3树

- 满足二分搜索树的基本性质
- 节点可以存放一个元素或者两个元素
- 每个节点有2个孩子或3个孩子
- 是一棵 ==**绝对平衡的树**==
- 向2-3树种添加节点——==**永远不会添加到一个空节点**==

### 红黑树 RBTree

`RedBlackTree.RBTree`

- 【人为约定】这里讨论的是**左倾**红黑树，一位置所有的红色节点都是左倾斜的
- 与2-3数具有等价性
- 初始化时节点为RED（添加节点时总是先融合）
- 空节点默认为BLACK
- 是一棵 ==“黑平衡”== 的二叉树
- 严格意义上，不是平衡二叉树，最大高度2logn,是O(logn)级别的

- 《算法导论》
    - 每个节点都是红色的，或者是黑色的
    - 根节点是黑色的
    - 每一个叶子结点（最后的空节点）是黑色的
    - 如果一个节点是红色的，那么他的孩子节点都是黑色的
    - 从任意一个节点到叶子结点，经过的黑色节点是一样的（黑平衡）


#### 红黑树添加新元素

- 2-3树种添加一个新元素
- 或者添加进行2-节点，形成一个3-节点
- 或者添加进3-节点，暂时形成一个4-节点
- 永远添加红色节点

- 添加节点涉及到三个操作维持平衡
    - 左旋 leftRotate
    - 右旋 rightRotate
    - 颜色翻转 flipColor

#### 红黑树的性能

- 对于完全随机的数据，普通的二分搜索树很好用
- 缺点：极端情况退化成链表（或者高度不平衡）
- 对于查询较多的使用情况，AVL树很好用
- 红黑树牺牲了平衡型（2logn的高度）
- 统计性能更优（综合增删改查所有的操作）


## 哈希表

- 哈希函数——将“键”转换为“索引”
- 哈希冲突——很难保证每一个“键”通过哈希函数的转换对应不同的“索引”
- 哈希表充分体现了算法设计领域的经典思想——空间换时间
- 哈希表是时间和空间之间的平衡
- “键”通过哈希函数得到的“索引”分布越均匀越好
- 这里主要关注一般的哈希函数的设计原则

### 哈希函数的设计

- 整形
    - 小范围正整数直接使用
    - 小范围负数进行偏移  -100～100  --> 0~200
    - 大整数
        - 通常做法：取模，但取模会出现一个问题，没有利用所有的信息
        - 解决办法：模一个素数
- 浮点型
    - 转成整形处理
    - 在计算机中都是32位或者64位的二进制表示，只不过计算机解析成了浮点数
- 字符串
    - 转成整形处理

```
code = c * B^3 + o * B^2 + d * B ^1 + e * B^0

hash(code) = (c * B^3 + o * B^2 + d * B ^1 + e * B^0) % M

hash(code) = ((((c % M ) * B) + o ) % M * B + d) % M * B + e) % M

// 写成代码
int hash = 0;
for (int i = 0; i < s.length(); i ++) {
    hash = (hash * B + s.charAt(i)) % M;
}
```

- 复合类型
    - 转成整形处理

```
Date: year, month, day

hash(date) = (((date.year%M) * B + date.month) %M * B + date.day) % M
```

- 哈希函数的设计
    - 转成整数处理，但并不是唯一方法
    - 原则
        - 一致性：如果`a==b`，那么`hash(a)==hash(b)`
        - 高效性：计算高效简便
        - 均匀性：哈希值均匀分布

- 哈希冲突的处理
    - 链地址法 Seperate Chaining
        - `(hash(key) & 0x7fffffff) % M`
        - 内部维护一个查找表，查找吧里存储着哈希值等于这个键的所对应的元素
        - 这个查找表，可以使用TreeMap、TreeSet、平衡树……
        - 需要动态空间处理
            - 总共有M个地址
            - 放入哈希表的元素为N个
            - 平均每个地址承载的元素多过一定程度，即扩容
                - `N/M >= upperTol`
            - 平均每个地址承载的元素少过一定程度，即缩容
                - `N/M < lowerTol`

    - Java中的`HashMap`的内部就维护了一个`TreeMap`
    - Java中的`HashSet`的内部就维护了一个`TreeSet`
    - Java8 之前，每一个位置对应一个链表
    - Java8 之后，当哈希冲突达到一定程度时，每一个位置从链表转为红黑树


- 哈希表的时间复杂度分析
    - 平均时间复杂度 ==**O(1)**==
    - 牺牲了——==**顺序性**==
