package taskmanager.manager;

import taskmanager.task.Epic;
import taskmanager.task.Status;
import taskmanager.task.Subtask;
import taskmanager.task.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private int nextId;

    private int getNextId() {
        return nextId++;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void clearAllTasks() {
        tasks.clear();
    }

    @Override
    public void clearAllEpics() {
        epics.clear();
        clearAllSubtasks();
    }

    @Override
    public void clearAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearAllSubtasks();
            updateEpicStatus(epic);
        }
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public Task addTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(getNextId());
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(subtask.getId());
        updateEpicStatus(epic);
        return subtask;
    }

    @Override
    public Task updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            System.out.println("Задача с таким id не найдена.");
            return null;
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) {
            System.out.println("Эпик с таким id не найден.");
            return null;
        }
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
        return epic;
    }

    @Override
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

    @Override
    public Task removeTaskById(int id) {
        return tasks.remove(id);
    }

    @Override
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

    @Override
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

    @Override
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
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}
