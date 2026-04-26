class MedianFinder {

    // 左边的桶：装较小的一半。使用最大堆（改变排序规则，大的在上面）
    private PriorityQueue<Integer> maxHeap;
    // 右边的桶：装较大的一半。使用最小堆（默认就是最小堆，小的在上面）
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        // Java 默认是最小堆。要改成最大堆，需要传入降序 Comparator
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }
    
    // 【核心微操】：每次来一个新数字，怎么保证两个桶的平衡？
    public void addNum(int num) {
        // 1. 盲目塞进左边：不管三七二十一，先让新来的进左边的桶（最大堆）
        maxHeap.offer(num);
        
        // 2. 净化左边：左边的桶顶现在可能是个非常大的数，不该待在较小半区。
        // 所以我们把左边最大的拿出来，扔进右边的桶（最小堆）里。
        minHeap.offer(maxHeap.poll());
        
        // 3. 维护平衡：我们之前规定过，左边的桶可以比右边多 1 个，但绝对不能比右边少！
        // 如果右边桶的人数超过了左边，就从右边拿一个最小的，还给左边。
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    // 瞬间给出中位数
    public double findMedian() {
        // 如果总人数是奇数（左边多 1 个）
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        // 如果总人数是偶数（两边一样多）
        else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}