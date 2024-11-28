package taskmanager.manager;

import org.junit.jupiter.api.Test;
import taskmanager.task.Epic;
import taskmanager.task.Status;
import taskmanager.task.Subtask;
import taskmanager.task.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    TaskManager taskManager = new InMemoryTaskManager();
    @Test
    public void addTaskById() {
        //проверяем, что InMemoryTaskManager добавляет задачи и может найти их по id;
        Task myTask = new Task("Покормить собаку.", "С выполнением команд.");
        myTask = taskManager.addTask(myTask);
        Task taskById = taskManager.getTaskById(myTask.getId());
        assertEquals(myTask, taskById, "Задачи не совпадают");
    }
    @Test
    public void addEpicById() {
        Epic catFind = new Epic("Найти кошку.", "Нужно успеть за час.");
        catFind = taskManager.addEpic(catFind);
        Epic epicById = taskManager.getEpicById(catFind.getId());
        assertEquals(catFind, epicById, "Епики не совпадают");
    }
    @Test
    public void addSubtaskById() {
        Epic catFind = new Epic("Найти кошку.", "Нужно успеть за час.");
        catFind = taskManager.addEpic(catFind);
        Subtask findCatInKitchen = new Subtask("Поискать на кухне", "Обязательно посмотреть в холодильнике!", catFind.getId());
        taskManager.addSubtask(findCatInKitchen);
        Subtask mySubtask = taskManager.getSubtaskById(findCatInKitchen.getId());
        assertEquals(findCatInKitchen, mySubtask, "Подзадачи не совпадают");
    }
}