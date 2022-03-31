package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
  private final BlockingQueue<Task> tasks;
  private final AtomicInteger waitingPeriod;
  private final AtomicBoolean isRunning;
  private final int nrServer;

  public Server(int nrServer) {
    this.nrServer = nrServer;
    this.tasks = new LinkedBlockingQueue<>();
    waitingPeriod = new AtomicInteger(0);
    isRunning = new AtomicBoolean(true);
  }

  public void addTask(Task task) {
    tasks.add(task);
    task.setWaitingTime(waitingPeriod.get() + task.getServiceTime());
    waitingPeriod.set(task.getWaitingTime());
  }

  public void stop() {
    isRunning.set(false);
  }

  @Override
  public void run() {
    while(isRunning.get()) {
      try {
        if(!tasks.isEmpty()) {
          Task t = tasks.peek();
          t.decrementServiceTime();
          waitingPeriod.decrementAndGet();
          if (t.getServiceTime() <= 0)
            tasks.remove();
        }
        Thread.sleep(1000);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


  public BlockingQueue<Task> getTasks() {
    return tasks;
  }

  public AtomicInteger getWaitingPeriod() {
    return waitingPeriod;
  }

  public int getNrServer() {
    return nrServer;
  }

  @Override
  public String toString() {
    String str = "";
    for(Task t: this.getTasks())
      str = str.concat(t.toString() + "; ");
    return str;
  }

}
