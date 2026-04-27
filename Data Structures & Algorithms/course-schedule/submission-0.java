class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 建立两个核心数据结构：
        // indegree 数组：记录每门课还有几个前置条件没完成
        int[] indegree = new int[numCourses];
        
        // adjacency list (邻接表)：记录修完这门课，能解锁哪些后续课
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // 2. 遍历输入的先修课要求，填充入度表和邻接表
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            
            // 修完 prerequisite 才能修 course，所以箭头是 prerequisite -> course
            adj.get(prerequisite).add(course);
            // course 的门槛 + 1
            indegree[course]++;
        }
        
        // 3. 把所有没有任何门槛的课（入度为 0）加入队列，准备开学！
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 4. 开始上课，顺藤摸瓜解锁后续课程
        int finishedCoursesCount = 0; // 记录一共修完的课的数量
        
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            finishedCoursesCount++; // 修完一门，计数 + 1
            
            // 拿到当前这门课能解锁的所有后续课程
            for (int nextCourse : adj.get(currentCourse)) {
                // 后续课程的前置条件减 1
                indegree[nextCourse]--;
                
                // 如果门槛降到 0 了，说明可以上了，立刻加入队列！
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        // 5. 如果修完的课等于总课数，说明没有死循环，顺利毕业！
        return finishedCoursesCount == numCourses;
    }
}