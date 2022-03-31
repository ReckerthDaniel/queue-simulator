package controller;

import business.SimulationManager;
import common.SimulationInformation;
import common.InputFileReader;
import view.SimulationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
  private final SimulationFrame view;

  public Controller(SimulationFrame simulationFrame) {
    this.view = simulationFrame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (view.getManuallyDataCheckBox().isSelected()) {
      SimulationInformation simulationInformation = new SimulationInformation();
      view.getInputFileComboBox().setEnabled(false);
      view.enableManualDataFields(true);
      if (src.equals(view.getSubmitBtn())) {
        if (view.areInputFieldsEmpty()) {
          view.showErrorMessage("Please fill all the input fields");
        } else {
          parseInputFields(simulationInformation);
          Thread thread = new Thread(new SimulationManager(simulationInformation, view));
          thread.start();
        }
      }
    } else {
      view.enableManualDataFields(false);
      view.getInputFileComboBox().setEnabled(true);
      view.clearFields();
      String inputFileName = (String) view.getInputFileComboBox().getSelectedItem();
      if(view.getOutFileTxtFld().getText().isEmpty()) {
        view.showErrorMessage("Please fill the output file name");
      } else {
        String outputFileName = view.getOutFileTxtFld().getText();
        SimulationInformation simulationInformation = new SimulationInformation();
        InputFileReader inputFileReader = new InputFileReader(simulationInformation);
        inputFileReader.readInputFile(inputFileName);
        simulationInformation.setOutputFileName(outputFileName);
        simulationInformation.setSelectionPolicy(view.getPolicyComboBox());
        if(src.equals(view.getSubmitBtn())) {
          Thread thread = new Thread(new SimulationManager(simulationInformation, view));
          thread.start();
        }
      }
    }

    if(src.equals(view.getClearBtn())) {
      view.getTextArea().setText("");
      view.clearFields();
    }
  }

  private void parseInputFields(SimulationInformation simulationInformation) {
    simulationInformation.setOutputFileName(view.getOutFileTxtFld().getText());
    simulationInformation.setNumberOfServers(Integer.parseInt(view.getQueuesTxtFld().getText()));
    simulationInformation.setMaxSimulationTime(Integer.parseInt(view.getSimTimeTxtFld().getText()));
    simulationInformation.setNumberOfTasks(Integer.parseInt(view.getTasksTxtFld().getText()));
    simulationInformation.setMinServiceTime(Integer.parseInt(view.getMinSrvTimeTxtFld().getText()));
    simulationInformation.setMinArrivalTime(Integer.parseInt(view.getMinArrivTimeTxtFld().getText()));
    simulationInformation.setMaxServiceTime(Integer.parseInt(view.getMaxSrvTimeTxtFld().getText()));
    simulationInformation.setMaxArrivalTime(Integer.parseInt(view.getMaxArrivTimeTxtFld().getText()));
  }

}
