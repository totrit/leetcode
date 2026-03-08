package subarray_sums_divisible_by_k_description

class Solution {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        val shift = ((10_000 / k) + 1) * k
        var ans = 0
        val mods = hashMapOf(0 to 1)
        var accMod = 0
        for (i in nums.indices) {
            accMod = (accMod + nums[i] + shift) % k
            val num = mods.getOrDefault(accMod, 0)
            ans += num
            mods[accMod] = num + 1
        }

        return ans
    }
}