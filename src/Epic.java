import java.util.HashMap;
import java.util.Map;

public class Epic extends Task {
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public Map<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public void clearAllSubtasks() {
        subtasks.clear();
        setStatus(Status.NEW);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateStatus();
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateStatus();
    }

    public void removeSubtask(Subtask subtask) {
        subtasks.remove(subtask.getId());
        updateStatus();
    }

    public void updateStatus() {
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }
        boolean isExistNew = false;
        boolean isExistInProgress = false;
        boolean isExistDone = false;
        for (Subtask subtask : subtasks.values()) {
            if (Status.NEW == subtask.getStatus()) {
                isExistNew = true;
            } else if (Status.IN_PROGRESS == subtask.getStatus()) {
                isExistInProgress = true;
            } else if (Status.DONE == subtask.getStatus()) {
                isExistDone = true;
            }
        }

        if (isExistNew && !isExistInProgress && !isExistDone) {
            setStatus(Status.NEW);
        } else if (!isExistNew && !isExistInProgress && isExistDone) {
            setStatus(Status.DONE);
        } else {
            setStatus(Status.IN_PROGRESS);
        }

    }


    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", subtasks=" + subtasks +
                '}';
    }
}
