package business;

import business.strategy.ConcreteShortestQueueStrategy;
import business.strategy.ConcreteShortestTimeStrategy;
import business.strategy.Strategy;
import business.strategy.enums.SelectionPolicy;
import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
  private final List<Server> servers;
  private Strategy strategy;

  public Scheduler(int maxNoOfServers, SelectionPolicy selectionPolicy) {
    this.servers = new ArrayList<>(maxNoOfServers);
    for (int i = 0; i < maxNoOfServers; i++) {
      Server server = new Server(i + 1);
      this.servers.add(server);

      Thread thread = new Thread(server);
      thread.start();
    }
    changeStrategy(selectionPolicy);
  }

  private void changeStrategy(SelectionPolicy selectionPolicy) {
    switch (selectionPolicy) {
      case SHORTEST_QUEUE -> this.strategy = new ConcreteShortestQueueStrategy();
      case SHORTEST_TIME -> this.strategy = new ConcreteShortestTimeStrategy();
    }
  }

  public void dispatchTask(Task task) {
    this.strategy.addTaskToServers(this.servers, task);
  }

  public boolean isEmpty() {
    for(Server server : this.servers) {
      if(!server.getTasks().isEmpty()) {
        return false;
      }
    }

    return true;
  }

  public List<Server> getServers() {
    return servers;
  }

  @Override
  public String toString() {
    String str = "";
    for(Server server: servers) {
      str = str.concat("Queue " + server.getNrServer() + ": ");
      if(server.getTasks().isEmpty())
        str = str.concat("closed\n");
      else {
        str = str.concat(server + "\n");
      }
    }
    str = str + "\n";
    return str;
  }
}
