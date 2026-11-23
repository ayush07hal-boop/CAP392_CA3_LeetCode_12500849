class Solution {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        return nums;
    }

    // Alternative: Using a new array
    public int[] runningSumNewArray(int[] nums) {
        int[] result = new int[nums.length];

        if (nums.length == 0) {
            return result;
        }

        result[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }

        return result;
    }
}
