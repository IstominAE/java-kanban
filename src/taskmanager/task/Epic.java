package taskmanager.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public List<Integer> getSubtasks() {
        return subtasks;
    }

    public void clearAllSubtasks() {
        subtasks.clear();
        setStatus(Status.NEW);
    }

    public void addSubtask(int subtaskId) {
        if(subtaskId == id) {
            System.out.println("Нельзя добавлять в Epic самого себя в качестве подзадачи");
            return;
        }
        subtasks.add(subtaskId);
    }

    public void removeSubtask(int subtaskId) {
        subtasks.remove(Integer.valueOf(subtaskId));
    }

    @Override
    public String toString() {
        return "taskmanager.task.Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", subtasks=" + subtasks +
                '}';
    }
}
