package multiThread;

/**
 * Create by Zhao Qing on 2018/4/28
 */
public class MyThread implements Runnable{
    public long count = 0L;

    private String name;

    private boolean isStop = false;

    public MyThread(String name){
        this.name = name;
    }

    public void setIsStop(boolean isStop){
        this.isStop = isStop;
    }

    @Override
    public void run(){
        while (!isStop){
//            try {
//                Thread.sleep(1);
//            }catch (InterruptedException exc){
//                System.out.println("error:" + exc.getMessage());
//            }
            count++;
            System.out.println("this is my thread: " + name);
        }
    }

    public void start(){
        System.out.println("starting...");
    }
}
