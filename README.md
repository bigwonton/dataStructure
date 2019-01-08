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
 


## 栈 Stack 

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

- 数组尾部进栈，数组尾部出栈

- 时间复杂度

## 队列 Queue

### Queue 接口

