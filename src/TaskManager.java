import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    public Map<Integer, Task> tasks = new HashMap<>();
    public Map<Integer, Epic> epics = new HashMap<>();
    public Map<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId;

    private int getNextId() {
        return nextId++;
    }

    public Collection<Task> getAllTasks() {
        return tasks.values();
    }

    public Collection<Epic> getAllEpics() {
        return epics.values();
    }

    public Collection<Subtask> getAllSubtasks() {
        return subtasks.values();
    }

    public void clearAllTasks() {
        tasks.clear();
    }

    public void clearAllEpics() {
        epics.clear();
        clearAllSubtasks();
    }

    public void clearAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearAllSubtasks();
        }
    }

    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    public Epic getEpicById(Integer id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    public Task addTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic addEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(getNextId());
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubtask(subtask);
        return subtask;
    }

    public Task updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            System.out.println("Задача с таким id не найдена.");
            return null;
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) {
            System.out.println("Эпик с таким id не найден.");
            return null;
        }

        Epic oldValue = epics.get(epic.getId());
        for (Subtask subtask : oldValue.getSubtasks().values()) {
            removeSubtaskById(subtask.getId());
        }

        epics.put(epic.getId(), epic);
        for (Subtask subtask : epic.getSubtasks().values()) {
            addSubtask(subtask);
        }

        epic.updateStatus();
        return epic;
    }

    public Subtask updateSubtask(Subtask subtask) {
        if (!subtasks.containsKey(subtask.getId())) {
            System.out.println("Подзадача с таким id не найдена.");
            return null;
        }
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).updateSubtask(subtask);
        return subtask;
    }

    public Task removeTaskById(Integer id) {
        return tasks.remove(id);
    }

    public Epic removeEpicById(Integer id) {
        if (!epics.containsKey(id)) {
            System.out.println("Эпик с таким id не найден.");
            return null;
        }
        for (Subtask subtask : epics.get(id).getSubtasks().values()) {
            removeSubtaskById(subtask.getId());
        }
        return epics.remove(id);
    }

    public Task removeSubtaskById(Integer id) {
        if (!subtasks.containsKey(id)) {
            System.out.println("Подзадача с таким id не найдена.");
            return null;
        }
        Subtask subtask = subtasks.get(id);
        epics.get(subtask.getEpicId()).removeSubtask(subtask);
        return subtasks.remove(id);
    }

    public Collection<Subtask> getAllEpicSubtasks(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            System.out.println("Эпик с таким id не найден.");
            return null;
        }
        return epic.getSubtasks().values();
    }
}
