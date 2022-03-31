import controller.Controller;
import view.SimulationFrame;

public class Main {
  public static void main(String[] args) {
    SimulationFrame frame = new SimulationFrame("Queues Simulator");
    Controller controller = new Controller(frame);
  }
}
