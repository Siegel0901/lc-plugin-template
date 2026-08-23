package leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MedianOfTwoSortedArrays {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路五：二分查找
     * 核心思想：在两个数组中分别找一个分割点i和j，两个分割点左边为左半部分，右边为右半部分：
     * 1. 左半部分元素总数 = 右半部分元素总数（或差1）：i + j = (m + n + 1) / 2
     * 1.1. i表示nums1中有i个元素归入左半部分，m-i个元素归入右半部分，i∈[0,m]，共m+1种可能
     * 1.2. j表示nums2中有j个元素归入左半部分，n-j个元素归入右半部分，j∈[0,n]，共n+1种可能
     * 1.3. +1是为了让元素总数为奇数的情况时，左半部分比右半部分多1个元素，这样中位数为左半部分最大值
     * 1.4. 不+1的话也可以，元素总数为奇数时，右半部分比左半部分多1个元素，中位数为右半部分的最小值
     * 2. 左半部分最大值 <= 右半部分最小值：max(nums1[i-1], nums2[j-1]) <= min(nums1[i], nums2[j])
     * 时间复杂度：O(log(min(m,n)))
     * 空间复杂度：O(1)
     */
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            // 在长度小的数组上进行二分，缩小搜索范围
            if (m > n)
                return findMedianSortedArrays(nums2, nums1);
            // 左半部分的元素总数
            int leftTotal = (m + n + 1) / 2;
            // i∈[0,m+1)即i∈[0,m]
            int low = 0, high = m + 1;
            while (low < high) {
                // i表示nums1中有i个元素归入左半部分
                int i = low + (high - low) / 2;
                // j表示nums2中有j个元素归入左半部分
                int j = leftTotal - i;
                // 计算i左半部分的最大值
                int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
                // 计算i右半部分的最小值
                int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
                // 计算j左半部分的最大值
                int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
                // 计算j右半部分的最小值
                int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
                // if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin)
                if (Math.max(nums1LeftMax, nums2LeftMax) <= Math.min(nums1RightMin, nums2RightMin))
                    // 左半部分的最大值小于右半部分的最小值,说明找到了对的分割点
                    return (m + n) % 2 == 1
                            // 奇数返回左半部分的最大值
                            ? Math.max(nums1LeftMax, nums2LeftMax)
                            // 偶数返回左半部分最大值和右半部分最小值的平均值
                            : (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                else if (nums1LeftMax > nums2RightMin)
                    // i左半部分的最大值大于j右半部分的最小值,说明i需要偏左,将[low,high)的范围调整到[low,i)
                    high = i;
                else
                    // i左半部分的最大值小于j右半部分的最小值,说明i需要偏右,将[low,high)的范围调整到[i+1,high)
                    low = i + 1;
            }
            return Double.MIN_VALUE;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路四：大顶堆 + 小顶堆
     * 1. 比中位数大的元素加入小顶堆，比中位数小的元素加入大顶堆
     * 2. 设元素总数为n
     * 2.1. 若n为奇数，则两个堆的元素个数为n/2+1和n/2，元素多的那个堆的堆顶就是中位数
     * 2.2. 若n为偶数，则两个堆的元素个数为n/2和n/2，两个堆的堆顶取平均值就是中位数
     * 3. 需要保证大顶堆中的元素都要比小顶堆中的元素小，且两个堆的大小之差不能超过1
     * 时间复杂度：O((m+n)log(m+n))
     * 空间复杂度：O(m+n)
     */
    class Solution4 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            // 声明小顶堆放值较大的元素，大顶堆放值较小的元素
            PriorityQueue<Integer> largeMin = new PriorityQueue<>();
            PriorityQueue<Integer> smallMax = new PriorityQueue<>((a, b) -> b - a);
            // 向小顶堆和大顶堆中添加元素
            for (int i = 0; i < m + n; i++) {
                // 获取nums1或nums2中的元素
                int num = i < m ? nums1[i] : nums2[i - m];
                /*
                 * 1. 如何保证两个堆的大小之差不超过1？
                 * 1.1. 根据堆大小添加元素，优先添加到堆中元素个数少的堆里
                 * 1.2. 堆大小相等时，默认添加到largeMin(也可以是smallMax)
                 * 2. 如何保证smallMax中的元素一定比largeMin中的元素小？
                 * 2.1. 添加到堆大小较小的堆之前，先添加到另外一个堆中，再将另外一个堆的堆顶加入到堆大小较小的堆中。
                 * 2.2. 如要往largeMin中添加元素，则需要先添加到smallMax中，再将smallMax的堆顶（smallMax中最大的元素）加入largeMin
                 * 2.2. 如要往smallMax中添加元素，则需要先添加到largeMin中，再将largeMin的堆顶（largeMin中最小的元素）加入smallMax
                 * */
                if (smallMax.size() >= largeMin.size()) {
                    smallMax.offer(num);
                    largeMin.offer(smallMax.poll());
                } else {
                    largeMin.offer(num);
                    smallMax.offer(largeMin.poll());
                }
            }
            // 若堆大小不一样，元素多的堆顶是中位数
            if (largeMin.size() < smallMax.size())
                return smallMax.peek();
            if (largeMin.size() > smallMax.size())
                return largeMin.peek();
            // 若堆大小相等,则两个堆顶的平均数为中位数
            return (smallMax.peek() + largeMin.peek()) / 2.0;
        }
    }

    /**
     * 思路三：双指针合并数组+空间压缩
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(1)
     */
    class Solution3 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            // 合并数组的长度
            int len = (m + n) / 2 + 1;
            // 用两个变量记录前一个和当前元素
            int prev = 0, curr = 0;
            // 指针
            int i = 0, j = 0;
            // 合并两个数组直到中位数位置
            for (int k = 0; k < len; k++) {
                prev = curr;
                /*
                 * 条件                                含义                      操作
                 * i < m && j >= n                   nums2已空                 取nums1[i]
                 * i < m && nums1[i] <= nums2[j]     nums1更小                 取nums1[i]
                 * 其他情况                            nums2更小或nums1已空       取nums2[j]
                 * */
                if (i < m && (j >= n || nums1[i] <= nums2[j]))
                    curr = nums1[i++];
                else
                    curr = nums2[j++];
            }
            // 根据奇偶计算中位数
            return ((m + n) & 1) == 1 ? curr : (curr + prev) / 2.0;
        }
    }

    /**
     * 思路二：双指针合并数组
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(m+n)
     */
    class Solution2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            // 合并数组的长度
            int len = (m + n) / 2 + 1;
            int[] merge = new int[len];
            // 指针
            int i = 0, j = 0, idx = 0;
            // 合并两个数组
            while (idx < len) {
                if (i < m && (j >= n || nums1[i] <= nums2[j]))
                    merge[idx++] = nums1[i++];
                else
                    merge[idx++] = nums2[j++];
            }
            // 根据奇偶计算中位数
            return ((m + n) & 1) == 1 ? merge[len - 1] : (merge[len - 1] + merge[len - 2]) / 2.0;
        }
    }

    /**
     * 思路一：暴力排序
     * 1. 将nums1和nums2合并为一个有序数组
     * 2. 找到中位数
     * 时间复杂度：O((m+n)log(m+n))
     * 空间复杂度：O(m+n)
     */
    class Solution1 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            int[] mn = new int[m + n];
            System.arraycopy(nums1, 0, mn, 0, m);
            System.arraycopy(nums2, 0, mn, m, n);
            Arrays.sort(mn);
            if (((m + n) & 1) == 1)
                return mn[(m + n) / 2];
            else
                return (mn[(m + n) / 2 - 1] + mn[(m + n) / 2]) / 2.0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        // put your test code here
        solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
    }
}