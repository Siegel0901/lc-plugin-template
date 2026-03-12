package leetcode.editor.cn;

import java.util.TreeMap;

public class MyCalendarI {

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyCalendar {

        TreeMap<Integer, Integer> calendar;

        public MyCalendar() {
            calendar = new TreeMap<>();
        }

        /**
         * 利用TreeMap提供的API
         * floorKey用于查找小于等于Key的最大值
         * ceilingKey用于查找大于等于Key的最小值
         * 无法安装日程的情况：
         * 1. startTime在某个已有日程时间段之间
         * 2. endTime在某个已有日程时间段之间
         */
        public boolean book(int startTime, int endTime) {
            if (calendar.containsKey(startTime))
                return false;
            // earlier < startTime
            Integer earlier = calendar.floorKey(startTime);
            // startTime < later
            Integer later = calendar.ceilingKey(startTime);
            // earlier < startTime < earlierEndTime：上个日程还没结束，本次日程就要开始了
            if (earlier != null && startTime < calendar.get(earlier))
                return false;
            // startTime < later < endTime：本次日程还没结束，下个日程就要开始了
            if (later != null && later < endTime)
                return false;
            calendar.put(startTime, endTime);
            return true;
        }
    }

    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(startTime,endTime);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        MyCalendar solution = new MyCalendarI().new MyCalendar();
        // put your test code here

    }
}