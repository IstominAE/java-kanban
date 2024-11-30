package taskmanager.task;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, Integer epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
    public void setEpicId(int epicId) {
        if(epicId == id) {
            System.out.println("Нельзя указать в подзадаче саму себя в качестве Эпика.");
            return;
        }
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "taskmanager.task.Subtask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
