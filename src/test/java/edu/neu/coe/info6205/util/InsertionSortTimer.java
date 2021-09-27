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
        final int a = 58;
        final InsertionSortTimer ti = new InsertionSortTimer();
        for(int n = 10000; n < 200000; n *=2){
            Timer timer1 = new Timer();
            Timer timer2 = new Timer();
            Timer timer3 = new Timer();
            Timer timer4 = new Timer();
            Integer[] randomArray = new Integer[n];
            for (int i = 0; i < randomArray.length; i++) {
                randomArray[i] = (int)(Math.random() * 1000);
              
            }
            double getRandomTime = timer1.repeat(10,() -> a, t -> {
                ti.sort(randomArray, 0, randomArray.length - 1);
                return null;
            },(null),(null));
            timer1.resume();
            System.out.println("when n=" + n + " randomArray's time is " + getRandomTime);



            Integer[] orderedArray = new Integer[n];
            for (int i = 0; i < orderedArray.length; i++) {
                orderedArray[i] = i + 1;
            }
            double getOrderedTime = timer2.repeat(10, () -> a, t -> {
                ti.sort(orderedArray, 0, orderedArray.length - 1);
                return null;
            },(null),(null));
            timer2.resume();
            System.out.println("when n=" + n + " orderedArray's time is " + getOrderedTime );



            Integer[] partiallyOrdered = new Integer[n];
            for(int i = 0; i < partiallyOrdered.length; i++) {
                if(i <= partiallyOrdered.length / 2) {
                    partiallyOrdered[i] = i + 1;
                }else{
                    partiallyOrdered[i] = (int)(Math.random() * 20000);
                }
            }
            double getPartiallyOrderedTime =   timer3.repeat(10, () -> a, t ->{
                ti.sort(partiallyOrdered,0,partiallyOrdered.length - 1);
                return  null;
            },(null),(null));
            timer3.resume();
            System.out.println("when n=" + n + " partiallyOrdered's time is " + getPartiallyOrderedTime );



            Integer[] reverseOrderedArray = new Integer[n];
            for(int i = 0; i < reverseOrderedArray.length; i++) {
                reverseOrderedArray[i] = n - i;
            }
            double getReverseOrderedArrayTime = timer4.repeat(10,() -> a,t ->{ti.sort(reverseOrderedArray, 0,reverseOrderedArray.length - 1);
                return null;
            },(null),(null));
            timer4.resume();
            System.out.println("when n=" + n +" reverseOrderedArray's time is " + getReverseOrderedArrayTime);


        }


    }

  // repeat(int n, Supplier<T> supplier, Function<T, U> function, UnaryOperator<T> preFunction, Consumer<U> postFunction)

}
