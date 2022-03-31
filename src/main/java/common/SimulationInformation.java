package common;

import business.strategy.enums.SelectionPolicy;

import static business.strategy.enums.SelectionPolicy.SHORTEST_QUEUE;
import static business.strategy.enums.SelectionPolicy.SHORTEST_TIME;

public class SimulationInformation {
  private int maxSimulationTime = 0;
  private int maxServiceTime = 0;
  private int minServiceTime = 0;
  private int maxArrivalTime = 0;
  private int minArrivalTime = 0;
  private int numberOfServers = 0;
  private int numberOfTasks = 0;
  private SelectionPolicy selectionPolicy = SHORTEST_QUEUE;
  private String outputFileName = null;

  public void setMaxSimulationTime(int maxSimulationTime) {
    this.maxSimulationTime = maxSimulationTime;
  }

  public void setMaxServiceTime(int maxServiceTime) {
    this.maxServiceTime = maxServiceTime;
  }

  public void setMinServiceTime(int minServiceTime) {
    this.minServiceTime = minServiceTime;
  }

  public void setMaxArrivalTime(int maxArrivalTime) {
    this.maxArrivalTime = maxArrivalTime;
  }

  public void setMinArrivalTime(int minArrivalTime) {
    this.minArrivalTime = minArrivalTime;
  }

  public void setNumberOfServers(int numberOfServers) {
    this.numberOfServers = numberOfServers;
  }

  public void setNumberOfTasks(int numberOfTasks) {
    this.numberOfTasks = numberOfTasks;
  }

  public void setSelectionPolicy(String selectionPolicy) {
    if(selectionPolicy.equals("shortest time")) {
      this.selectionPolicy = SHORTEST_TIME;
    } else {
      this.selectionPolicy = SHORTEST_QUEUE;
    }
  }

  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  public int getMaxSimulationTime() {
    return maxSimulationTime;
  }

  public int getMaxServiceTime() {
    return maxServiceTime;
  }

  public int getMinServiceTime() {
    return minServiceTime;
  }

  public int getMaxArrivalTime() {
    return maxArrivalTime;
  }

  public int getMinArrivalTime() {
    return minArrivalTime;
  }

  public int getNumberOfServers() {
    return numberOfServers;
  }

  public int getNumberOfTasks() {
    return numberOfTasks;
  }

  public SelectionPolicy getSelectionPolicy() {
    return selectionPolicy;
  }

  public String getOutputFileName() {
    return outputFileName;
  }


}
