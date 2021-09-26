package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.function.Supplier;

public class InsertionSortTimer <X extends Comparable<X>> extends InsertionSort {
    public static void main(String[] args) {

//        final Timer timer1 = new Timer();
//        final Timer timer2 = new Timer();
//        final Timer timer3 = new Timer();
//        final Timer timer4 = new Timer();
        final InsertionSortTimer ti = new InsertionSortTimer();
        for(int n = 800; n < 13000; n *=2){
             final Timer timer1 = new Timer();
             final Timer timer2 = new Timer();
             final Timer timer3 = new Timer();
             final Timer timer4 = new Timer();
            Integer[] randomArray = new Integer[n];
            for (int i = 0; i < randomArray.length; i++) {
                randomArray[i] = (int)(Math.random() * 1000);
              
            }

            double getRandomTime = timer1.repeat(10, () -> {
                ti.sort(randomArray, 0, randomArray.length - 1);
                return null;
            });
            System.out.println("当数组长度n=" + n + " randomArray排序的时间是" + getRandomTime + randomArray.length);



            Integer[] orderedArray = new Integer[n];
            for (int i = 0; i < orderedArray.length; i++) {
                orderedArray[i] = i + 1;
            }
            double getOrderedTime = timer2.repeat(10, () -> {
                ti.sort(orderedArray, 0, orderedArray.length - 1);
                return null;
            });
            timer2.resume();
            System.out.println("当数组长度n=" + n + " orderedArray排序的时间是" + getOrderedTime );



            Integer[] partiallyOrdered = new Integer[n];
            for(int i = 0; i < partiallyOrdered.length; i++) {
                if(i <= partiallyOrdered.length / 2) {
                    partiallyOrdered[i] = i + 1;
                }else{
                    partiallyOrdered[i] = (int)(Math.random() * 20000);
                }
            }
            double getPartiallyOrderedTime =   timer3.repeat(10,()->{
                ti.sort(partiallyOrdered,0,partiallyOrdered.length - 1);
                return  null;
            });
            System.out.println("当数组长度n=" + n + " partiallyOrdered排序的时间是" + getPartiallyOrderedTime );



            Integer[] reverseOrderedArray = new Integer[n];
            for(int i = 0; i < reverseOrderedArray.length; i++) {
                reverseOrderedArray[i] = n - i;
            }
            double getReverseOrderedArrayTime = timer4.repeat(10,() ->{ti.sort(reverseOrderedArray, 0,reverseOrderedArray.length - 1);
                return null;
            });
            System.out.println("当数组长度n=" + n +" reverseOrderedArray排序的时间是" + getReverseOrderedArrayTime);


        }


    }

  // repeat(int n, Supplier<T> supplier, Function<T, U> function, UnaryOperator<T> preFunction, Consumer<U> postFunction)

}
