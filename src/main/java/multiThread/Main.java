package multiThread;

/**
 * Create by Zhao Qing on 2018/4/28
 */
public class Main {

    public static void main(String[] args) throws Exception{
//        MyThread myThread = new MyThread("singleThread");
//        Thread thread = new Thread(myThread, "singleThread");
//        System.out.println(thread.getName() + " start running!");
//        long startTime = System.currentTimeMillis();
//        thread.start();
//        while (true){
//            long endtime = System.currentTimeMillis();
//            if(endtime - startTime > 1000){
//                myThread.setIsStop(true);
//                System.out.println("thread start time: " + startTime);
//                System.out.println("thread end time: " + endtime);
//                System.out.println("records count: " + MyThread.count / 5);
//                break;
//            }
//        }

        int nums = 1;
        MyThread[] myThreads = new MyThread[nums];
        Thread[] threads = new Thread[nums];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < nums; i++){
            myThreads[i] = new MyThread("thread" + i);
            threads[i] = new Thread(myThreads[i],"thread" + i);
            threads[i].start();
        }
        while (true){
            long endtime = System.currentTimeMillis();
            int duration = 1000;
            if(endtime - startTime > duration){
                long sum = 0;
                for (MyThread myThread:myThreads
                     ) {
                    myThread.setIsStop(true);
                    sum += myThread.count;
                }
                System.out.println("thread start time: " + startTime);
                System.out.println("thread end time: " + endtime);
                System.out.println("records count: " + sum);
                break;
            }
        }

    }
}
