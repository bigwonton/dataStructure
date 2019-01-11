package PriorityQueueAndHeap.Leetcode347;
/* Leetcode 347. Top K Frequent Elements

    Given a non-empty array of integers, return the k most frequent elements.

        Example 1:

        Input: nums = [1,1,1,2,2,3], k = 2
        Output: [1,2]

        Example 2:

        Input: nums = [1], k = 1
        Output: [1]*/
import java.util.*;

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 先统计频次，存放在map中
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length ; i ++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        // 使用优先队列，先维护大小为k的优先队列，优先队列中存放map中的key
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (Integer key : map.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if (map.get(key).compareTo(map.get(priorityQueue.peek())) > 0) {
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }

        List<Integer> ret = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            ret.add(priorityQueue.remove());
        }

        return ret;

    }

    public static void main(String[] args) {
        int[] nums = {3,0,1,0};
        int k = 1;
        System.out.println(new Solution().topKFrequent(nums, k));
    }
}