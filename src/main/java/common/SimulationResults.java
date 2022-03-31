package common;

public class SimulationResults {
  private double averageWaitingTime;
  private double averageServiceTime;
  private int peakHour;

  public void setAverageWaitingTime(double averageWaitingTime) {
    this.averageWaitingTime = averageWaitingTime;
  }

  public void setAverageServiceTime(double averageServiceTime) {
    this.averageServiceTime = averageServiceTime;
  }

  public void setPeakHour(int peakHour) {
    this.peakHour = peakHour;
  }

  public double getAverageWaitingTime() {
    return averageWaitingTime;
  }

  public double getAverageServiceTime() {
    return averageServiceTime;
  }

  public int getPeakHour() {
    return peakHour;
  }
}
