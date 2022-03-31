package business.strategy;

import model.Server;
import model.Task;

import java.util.List;

public interface Strategy {
  void addTaskToServers(List<Server> servers, Task task);
}
