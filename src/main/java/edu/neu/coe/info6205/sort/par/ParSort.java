package edu.neu.coe.info6205.sort.par;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;


    public static void sort(int[] array, int from, int to, ForkJoinPool myPool) {
        if (to - from < cutoff) Arrays.sort(array, from, to);
        else {
            // FIXME next few lines should be removed from public repo.
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2,myPool); // TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to,myPool); // TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                int[] result = new int[xs1.length + xs2.length];
                // TO IMPLEMENT
                int i = 0;
                int j = 0;
                for (int k = 0; k < result.length; k++) {
                    if (i >= xs1.length) {
                        result[k] = xs2[j++];
                    } else if (j >= xs2.length) {
                        result[k] = xs1[i++];
                    } else if (xs2[j] < xs1[i]) {
                        result[k] = xs2[j++];
                    } else {
                        result[k] = xs1[i++];
                    }
                }
                return result;
            });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
//            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to,ForkJoinPool myPool) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TO IMPLEMENT
                    System.arraycopy(array, from, result, 0, result.length);
                    sort(result, 0, to - from,myPool);
                    return result;
                },myPool);
    }
//    public static void sort(int[] array, int from, int to, int numberOfThread) {
//        if (to - from < cutoff) Arrays.sort(array, from, to);
//        else {
//            // FIXME next few lines should be removed from public repo.
//            List<CompletableFuture<int[]>> parsorts = new ArrayList<>();
//            for (int i = 1 ;i <= numberOfThread; i++) {
//                if (i == 1) {
//                    CompletableFuture<int[]> parsort = parsort(array, from, from + (to - from) / numberOfThread * i, numberOfThread);
//                    parsorts.add(parsort);
//                } else if (i > 1 && i < numberOfThread) {
//                    CompletableFuture<int[]> parsort = parsort(array, from + (to - from) / numberOfThread * (i - 1),
//                            from + (to - from) / numberOfThread * i, numberOfThread);
//                    parsorts.add(parsort);
//                } else {
//                    CompletableFuture<int[]> parsort = parsort(array, from + (to - from) / numberOfThread * (i - 1), to, numberOfThread);
//                    parsorts.add(parsort);
//                }
//            }
//
//            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
//                    parsorts.toArray(new CompletableFuture[parsorts.size()])
//            );
//            CompletableFuture<int[]>  parsort = allFutures.thenApply(v -> {
//                List<int[]> subArrays = new ArrayList<>();
//                for (int i = 0; i < numberOfThread; i++) {
//                    subArrays.add(parsorts.get(i).join());
//                }
//                int[] result = new int[to - from];
//                int index = 0;
//                Queue<Pair> minheap= new PriorityQueue<>((pair1, pair2) -> {return pair1.value - pair2.value;});
//                for (int i = 0; i < subArrays.size(); i++) {
//                    if (subArrays.get(i).length != 0) {
//                        minheap.offer(new Pair(subArrays.get(i)[0], i, 0));
//                    }
//                }
//                while (!minheap.isEmpty()) {
//                    Pair pair = minheap.poll();
//                    result[index++] = pair.value;
//                    if (pair.column < subArrays.get(pair.row).length - 1) {
//                        pair.column++;
//                        pair.value = subArrays.get(pair.row)[pair.column];
//                        minheap.offer(pair);
//                    }
//                }
//                return result;
//            });
//            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
////            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
//            parsort.join();
//        }
//    }
//    static class Pair {
//        private int value;
//        private int row;
//        private int column;
//        public Pair(int value, int row, int column) {
//            this.value = value;
//            this.row = row;
//            this.column = column;
//        }
//    }
}