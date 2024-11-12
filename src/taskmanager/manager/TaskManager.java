package taskmanager.manager;

import taskmanager.task.Epic;
import taskmanager.task.Status;
import taskmanager.task.Subtask;
import taskmanager.task.Task;

import java.util.*;

public class TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId;

    private int getNextId() {
        return nextId++;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
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
            updateEpicStatus(epic);
        }
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
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
        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(subtask.getId());
        updateEpicStatus(epic);
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
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
        return epic;
    }

    public Subtask updateSubtask(Subtask subtask) {
        if (!subtasks.containsKey(subtask.getId())) {
            System.out.println("Подзадача с таким id не найдена.");
            return null;
        }
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        updateEpicStatus(epic);
        return subtask;
    }

    public Task removeTaskById(int id) {
        return tasks.remove(id);
    }

    public Epic removeEpicById(int id) {
        if (!epics.containsKey(id)) {
            System.out.println("Эпик с таким id не найден.");
            return null;
        }
        for (Integer subtaskId : epics.get(id).getSubtasks()) {
            removeSubtaskById(subtaskId);
        }
        return epics.remove(id);
    }

    public Task removeSubtaskById(int id) {
        if (!subtasks.containsKey(id)) {
            System.out.println("Подзадача с таким id не найдена.");
            return null;
        }
        Subtask subtask = subtasks.get(id);
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtask(subtask.getId());
        updateEpicStatus(epic);
        return subtasks.remove(id);
    }

    public List<Subtask> getSubtasksByEpic(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            System.out.println("Эпик с таким id не найден.");
            return null;
        }
        List<Subtask> epicSubtasks = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtasks()) {
            epicSubtasks.add(subtasks.get(subtaskId));
        }
        return epicSubtasks;
    }
    private void updateEpicStatus(Epic epic) {
        List<Subtask> epicSubtasks = getSubtasksByEpic(epic.getId());
        if (epicSubtasks.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }
        boolean isExistNew = false;
        boolean isExistInProgress = false;
        boolean isExistDone = false;
        for (Subtask subtask : epicSubtasks) {
            if (Status.NEW == subtask.getStatus()) {
                isExistNew = true;
            } else if (Status.IN_PROGRESS == subtask.getStatus()) {
                isExistInProgress = true;
            } else if (Status.DONE == subtask.getStatus()) {
                isExistDone = true;
            }
        }

        if (isExistNew && !isExistInProgress && !isExistDone) {
            epic.setStatus(Status.NEW);
        } else if (!isExistNew && !isExistInProgress && isExistDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }

    }
}
