package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
  private JPanel mainPanel;
  private JCheckBox manuallyDataCheckBox;
  private JTextField tasksTxtFld;
  private JTextField queuesTxtFld;
  private JTextField simTimeTxtFld;
  private JTextField minArrivTimeTxtFld;
  private JTextField maxArrivTimeTxtFld;
  private JTextField minSrvTimeTxtFld;
  private JTextField maxSrvTimeTxtFld;
  private JButton submitBtn;
  private JButton clearBtn;
  private JTextField avgWaitTxtFld;
  private JTextField avgSrvTxtFld;
  private JTextField peakHourTxtFld;
  private JComboBox inputFileComboBox;
  private JTextField outFileTxtFld;
  private JComboBox policyCmbBox;
  private JTextArea textArea;
  private JLabel titleLbl;
  private JLabel inFileLbl;
  private JLabel manuallyDataLbl;
  private JLabel tasksLbl;
  private JLabel queuesLbl;
  private JLabel simTimeLbl;
  private JLabel arrivTimeLbl;
  private JLabel minArrivTimeLbl;
  private JLabel maxArrivTimeLbl;
  private JLabel srvTimeLbl;
  private JLabel minSrvTimeLbl;
  private JLabel maxSrvTimeLbl;
  private JLabel avgWaitLbl;
  private JLabel avgSrvLabel;
  private JLabel peakHourLbl;
  private JLabel outFileLbl;
  private JLabel policyLbl;

  private final Controller controller = new Controller(this);

  public SimulationFrame(String title) {
    super(title);
    setContentPane(mainPanel);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
    addActionListener(controller);
    enableManualDataFields(false);
  }

  public void addActionListener(ActionListener listener) {
    submitBtn.addActionListener(listener);
    clearBtn.addActionListener(listener);
    manuallyDataCheckBox.addActionListener(listener);
  }

  public void enableManualDataFields(boolean enable) {
    tasksTxtFld.setEnabled(enable);
    queuesTxtFld.setEnabled(enable);
    simTimeTxtFld.setEnabled(enable);
    minArrivTimeTxtFld.setEnabled(enable);
    maxArrivTimeTxtFld.setEnabled(enable);
    minSrvTimeTxtFld.setEnabled(enable);
    maxSrvTimeTxtFld.setEnabled(enable);
  }

  public boolean areInputFieldsEmpty() {
    return tasksTxtFld.getText().isEmpty() ||
           queuesTxtFld.getText().isEmpty() ||
           simTimeTxtFld.getText().isEmpty() ||
           minArrivTimeTxtFld.getText().isEmpty() ||
           maxArrivTimeTxtFld.getText().isEmpty() ||
           minSrvTimeTxtFld.getText().isEmpty() ||
           maxSrvTimeTxtFld.getText().isEmpty();
  }

  public void clearFields() {
    tasksTxtFld.setText("");
    queuesTxtFld.setText("");
    simTimeTxtFld.setText("");
    minArrivTimeTxtFld.setText("");
    maxArrivTimeTxtFld.setText("");
    minSrvTimeTxtFld.setText("");
    maxSrvTimeTxtFld.setText("");
    avgWaitTxtFld.setText("");
    avgSrvTxtFld.setText("");
    peakHourTxtFld.setText("");
  }

  public void writeTextArea(String text) {
    textArea.append(text);
  }

  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public JCheckBox getManuallyDataCheckBox() {
    return manuallyDataCheckBox;
  }

  public JTextField getTasksTxtFld() {
    return tasksTxtFld;
  }

  public JTextField getQueuesTxtFld() {
    return queuesTxtFld;
  }

  public JTextField getSimTimeTxtFld() {
    return simTimeTxtFld;
  }

  public JTextField getMinArrivTimeTxtFld() {
    return minArrivTimeTxtFld;
  }

  public JTextField getMaxArrivTimeTxtFld() {
    return maxArrivTimeTxtFld;
  }

  public JTextField getMinSrvTimeTxtFld() {
    return minSrvTimeTxtFld;
  }

  public JTextField getMaxSrvTimeTxtFld() {
    return maxSrvTimeTxtFld;
  }

  public JButton getSubmitBtn() {
    return submitBtn;
  }

  public JButton getClearBtn() {
    return clearBtn;
  }

  public JTextField getAvgWaitTxtFld() {
    return avgWaitTxtFld;
  }

  public JTextField getAvgSrvTxtFld() {
    return avgSrvTxtFld;
  }

  public JTextField getPeakHourTxtFld() {
    return peakHourTxtFld;
  }

  public JComboBox getInputFileComboBox() {
    return inputFileComboBox;
  }

  public JTextField getOutFileTxtFld() {
    return outFileTxtFld;
  }

  public JComboBox getPolicyCmbBox() {
    return policyCmbBox;
  }

  public JTextArea getTextArea() {
    return textArea;
  }

  public String getPolicyComboBox(){
    return (String) policyCmbBox.getSelectedItem();
  }
}
