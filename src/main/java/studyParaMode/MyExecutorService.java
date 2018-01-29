package studyParaMode;

import java.util.concurrent.*;

/**
 * @author ruanxin
 * @create 2018-01-25
 * @desc
 */
public class MyExecutorService {
    int i = 0;
    //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
    ExecutorService cachedExe = Executors.newCachedThreadPool();
    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    ExecutorService fixedExe = Executors.newFixedThreadPool(10);
    //创建一个定长线程池，支持定时及周期性任务执行
    ExecutorService scheduleExe = Executors.newScheduledThreadPool(10);
    //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
    ExecutorService singleExe = Executors.newSingleThreadExecutor();

    public void doRun() {
        fixedExe.execute(new Runnable() {
            public void run() {
                System.out.println(++i);
            }
        });
    }

    public void doSubmit() {
        Future future = fixedExe.submit(new Runnable() {
            public void run() {
                System.out.println("asy task" + i++);
            }
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void shutdown () {
        fixedExe.shutdown();
    }

    public void shutdownNow() {
        fixedExe.shutdownNow();
    }
    public static void main(String args[]) {
        MyExecutorService myExecutorService = new MyExecutorService();
//        for (int i = 0; i < 20; i++) {
//            myExecutorService.doRun();
//        }
////        myExecutorService.shutdown();
//        myExecutorService.shutdownNow();

//        myExecutorService.doSubmit();
//        myExecutorService.shutdown();
    }
}
