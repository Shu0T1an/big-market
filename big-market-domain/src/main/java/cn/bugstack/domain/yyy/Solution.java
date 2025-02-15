package cn.bugstack.domain.yyy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n-k+1];

        Queue<int[]> queue = new PriorityQueue<>(k,new Comparator<int[]>(){

            public int compare(int[] t1, int[] t2){
                return t2[1] == t1[1]?t1[0] - t2[0] : t2[1] - t1[1];
            }
        });

        for(int i = 0; i < k;i++){
            queue.offer(new int[]{i,nums[i]});
        }
        res[0] = queue.peek()[1];
        for(int i = k;i < nums.length;i++){

            while(!queue.isEmpty() &&queue.peek()[0] <= i-k){
                queue.poll();
            }
            queue.offer(new int[]{i,nums[i]});
            res[i-k+1] = queue.peek()[1];
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = solution.maxSlidingWindow(nums, k);
        for (int i : res) {
            System.out.println(i);
        }
    }

}