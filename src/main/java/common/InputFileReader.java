package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputFileReader {
  private final SimulationInformation simulationInformation;
  public InputFileReader(SimulationInformation simulationInformation) {
    this.simulationInformation = simulationInformation;
  }

  public void readInputFile(String inputFileName){
    try {
      BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFileName));
      simulationInformation.setNumberOfTasks(Integer.parseInt(inputFileReader.readLine()));
      simulationInformation.setNumberOfServers(Integer.parseInt(inputFileReader.readLine()));
      simulationInformation.setMaxSimulationTime(Integer.parseInt(inputFileReader.readLine()));

      String lineArrival = inputFileReader.readLine();
      String[] lineArrivalSplit = lineArrival.split(",");
      simulationInformation.setMinArrivalTime(Integer.parseInt(lineArrivalSplit[0]));
      simulationInformation.setMaxArrivalTime(Integer.parseInt(lineArrivalSplit[1]));

      String lineService = inputFileReader.readLine();
      String[] lineServiceSplit = lineService.split(",");
      simulationInformation.setMinServiceTime(Integer.parseInt(lineServiceSplit[0]));
      simulationInformation.setMaxServiceTime(Integer.parseInt(lineServiceSplit[1]));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
