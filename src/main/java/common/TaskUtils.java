package common;

import model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskUtils {

  public static List<Task> generateRandomTasks(SimulationInformation info) {
    Random random = new Random();
    List<Task> tasks = new CopyOnWriteArrayList<>();
    for(int i = 1; i <= info.getNumberOfTasks(); i++) {
      int serviceTime = random.nextInt(info.getMaxServiceTime() -
              info.getMinServiceTime() + 1) + info.getMinServiceTime();

      int arrivalTime = random.nextInt(info.getMaxArrivalTime()
              - info.getMinArrivalTime() + 1) + info.getMinArrivalTime();
      tasks.add(new Task(i, arrivalTime, serviceTime));
    }
    tasks.sort(Comparator.comparingInt(Task::getArrivalTime));
    return tasks;
  }

  public static double getAverageServiceTime(List<Task> tasks) {
    double totalServiceTime = 0;
    for(Task task : tasks) {
      totalServiceTime += task.getServiceTime();
    }
    return totalServiceTime / tasks.size();
  }
}
