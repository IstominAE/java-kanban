package taskmanager.manager;

import taskmanager.task.Epic;
import taskmanager.task.Subtask;
import taskmanager.task.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubtasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    Task addTask(Task task);

    Epic addEpic(Epic epic);

    Subtask addSubtask(Subtask subtask);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Subtask updateSubtask(Subtask subtask);

    Task removeTaskById(int id);

    Epic removeEpicById(int id);

    Task removeSubtaskById(int id);

    List<Subtask> getSubtasksByEpic(int epicId);
    List<Task> getHistory();
}
