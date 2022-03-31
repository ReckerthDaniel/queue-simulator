package business.strategy;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteShortestTimeStrategy implements Strategy {

  @Override
  public void addTaskToServers(List<Server> servers, Task task) {
    Server serverWithShortestTime = servers.get(0);
    for(Server server : servers) {
      int currentServerTime = server.getWaitingPeriod().get();
      int shortestTime = serverWithShortestTime.getWaitingPeriod().get();
      if(currentServerTime < shortestTime) {
        serverWithShortestTime = server;
      }
    }

    serverWithShortestTime.addTask(task);
  }
}
