package business;

import common.SimulationInformation;
import common.SimulationResults;
import common.TaskUtils;
import model.Server;
import model.Task;
import view.SimulationFrame;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.valueOf;


public class SimulationManager implements Runnable{
  private final SimulationInformation simulationInformation;
  private final SimulationResults simulationResults;
  private final Scheduler scheduler;
  private final List<Task> generatedTasks;
  private FileWriter fileWriter;
  private final SimulationFrame view;

  public SimulationManager(SimulationInformation simulationInformation, SimulationFrame view) {
    this.simulationInformation = simulationInformation;
    this.view = view;
    this.simulationResults = new SimulationResults();
    this.generatedTasks = TaskUtils.generateRandomTasks(simulationInformation);
    this.simulationResults.setAverageServiceTime(TaskUtils.getAverageServiceTime(generatedTasks));
    this.scheduler = new Scheduler(simulationInformation.getNumberOfServers(), simulationInformation.getSelectionPolicy());

    try {
      this.fileWriter = new FileWriter(simulationInformation.getOutputFileName());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    int simulationTime = 0, peakHour = 0, maxWaitingTasks = 0, totalWaitingTasks = 0;
    while(simulationTime < simulationInformation.getMaxSimulationTime()) {
      write("Time " + simulationTime + "\n");
      write("Waiting list: ");
      for(Task task : generatedTasks) {
        String taskString = task.toString();
        write(taskString + "; ");
        if(simulationTime == task.getArrivalTime()) {
          scheduler.dispatchTask(task);
          generatedTasks.remove(task);
        }
      }
      write("\n");
      String schedulerString = scheduler.toString();
      displayGUI(simulationTime);
      write(schedulerString);

      int waitingTasks = 0;
      for(Server server: scheduler.getServers()) {
        waitingTasks += server.getTasks().size();
        if(!server.getTasks().isEmpty()) {
          totalWaitingTasks += server.getTasks().size();
        }
      }

      if(waitingTasks > maxWaitingTasks) {
        peakHour = simulationTime;
        maxWaitingTasks = waitingTasks;
      }

      simulationTime++;

      if(generatedTasks.isEmpty() && scheduler.isEmpty()) {
        break;
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }
    }
    stopServers();
    simulationResults.setAverageWaitingTime((double) totalWaitingTasks / simulationInformation.getNumberOfTasks());
    simulationResults.setPeakHour(peakHour);
    displayEvaluationOnGUI();
    writeEvaluationLogFile();
  }

  private void stopServers() {
    for(Server server: scheduler.getServers())
      server.stop();
  }

  private void writeEvaluationLogFile(){
    try {
      fileWriter.write("Average waiting time: " + format("%.2f", simulationResults.getAverageWaitingTime()) + "\n");
      fileWriter.write("Average service time: " + format("%.2f", simulationResults.getAverageServiceTime()) + "\n");
      fileWriter.write("Peak hour: " + simulationResults.getPeakHour() + "\n");
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void write(String text) {
    try {
      fileWriter.write(text);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void displayGUI(int simTime) {
    SwingWorker<String, String> worker = new SwingWorker<>() {
      @Override
      protected String doInBackground() {
        publish("Time " + simTime + "\n");
        publish("Waiting list: ");
        for(Task t: generatedTasks){
          String tString = t.toString();
          publish(tString + "; ");
        }
        publish("\n");
        String schedulerString = scheduler.toString();
        publish(schedulerString);
        return null;
      }

      @Override
      protected void process(List<String> chunks) {
        for (String str : chunks)
          view.writeTextArea(str);
      }
    };
    worker.execute();
  }

  private void displayEvaluationOnGUI(){
    view.getAvgWaitTxtFld().setText(valueOf(format("%.2f", simulationResults.getAverageWaitingTime())));
    view.getPeakHourTxtFld().setText(valueOf(simulationResults.getPeakHour()));
    view.getAvgSrvTxtFld().setText(valueOf(format("%.2f",simulationResults.getAverageServiceTime())));
  }
}
