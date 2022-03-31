package model;

import static java.lang.String.format;

public class Task {
  private final int id;
  private final int arrivalTime;
  private int serviceTime;
  private int waitingTime;

  public Task(int id, int arrivalTime, int serviceTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  public int getArrivalTime() {
    return arrivalTime;
  }

  public int getServiceTime() {
    return serviceTime;
  }

  public int getWaitingTime() {
    return waitingTime;
  }

  public void setWaitingTime(int waitingTime) {
    this.waitingTime = waitingTime;
  }

  public void decrementServiceTime() {
    serviceTime--;
  }

  @Override
  public String toString() {
    return format("(%d,%d,%d)", id, arrivalTime, serviceTime);
  }
}
