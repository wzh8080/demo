package com.test.java8.stream;

import java.util.concurrent.RecursiveTask;

class SumRecursiveTask extends RecursiveTask<Long> {
    private static final long THRESHOLD = 3000L;
    private final long start;
    private final long end;
    public SumRecursiveTask(long start, long end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            // 任务不用再拆分了.可以计算了
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println("计算: " + start + " -> " + end + ",结果为: " + sum);
            return sum;
        } else {
            // 数量大于预定的数量,任务还需要再拆分
            long middle = (start + end) / 2;
            System.out.println("拆分: 左边 " + start + " -> " + middle + ", 右边 " + (middle +
                    1) + " -> " + end);
            SumRecursiveTask left = new SumRecursiveTask(start, middle);
            left.fork();
            SumRecursiveTask right = new SumRecursiveTask(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
