package taskmanager.manager;

import taskmanager.task.Epic;
import taskmanager.task.Subtask;
import taskmanager.task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    int historySize = 10;
    List<Task> history = new ArrayList<>(historySize);
    @Override
    public void add(Task task) {
        if(history.size() == historySize) {
            history.removeFirst();
        }

        Task taskSnapshot = getTaskSnapshot(task);
        history.add(taskSnapshot);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }

    private Task getTaskSnapshot(Task task) {
        Task taskSnapshot;
        if (task instanceof Epic) {
            taskSnapshot = new Epic(task.getName(), task.getDescription());
        } else if (task instanceof Subtask) {
            taskSnapshot = new Subtask(task.getName(), task.getDescription(), ((Subtask) task).getEpicId());
        } else {
            taskSnapshot = new Task(task.getName(), task.getDescription());
        }

        taskSnapshot.setId(task.getId());
        taskSnapshot.setStatus(task.getStatus());
        return taskSnapshot;
    }
}
