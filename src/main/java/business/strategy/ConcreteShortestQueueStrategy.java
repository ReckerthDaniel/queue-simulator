package business.strategy;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteShortestQueueStrategy implements Strategy {

  @Override
  public void addTaskToServers(List<Server> servers, Task task) {
    Server serverWithShortestQueue = servers.get(0);

    for(Server server : servers) {
      if(server.getTasks().size() < serverWithShortestQueue.getTasks().size()) {
        serverWithShortestQueue = server;
      }
    }

    serverWithShortestQueue.addTask(task);
  }
}
