package taskmanager.manager;

public class Managers {
    static InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    static TaskManager taskManager = new InMemoryTaskManager();

    public static HistoryManager getDefaultHistory() {
        return historyManager;
    }
    public static TaskManager getDefault() {
        return taskManager;
    }
}
