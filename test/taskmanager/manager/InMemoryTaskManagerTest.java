package taskmanager.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.task.Epic;
import taskmanager.task.Subtask;
import taskmanager.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    public void init() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void addTaskById_Success() {
        //проверяем, что InMemoryTaskManager добавляет задачи и может найти их по id;
        Task myTask = new Task("Покормить собаку.", "С выполнением команд.");

        myTask = taskManager.addTask(myTask);
        Task taskById = taskManager.getTaskById(myTask.getId());

        assertEquals(myTask, taskById, "Задачи не совпадают");
    }

    @Test
    public void addEpicById_Success() {
        Epic catFind = new Epic("Найти кошку.", "Нужно успеть за час.");

        catFind = taskManager.addEpic(catFind);
        Epic epicById = taskManager.getEpicById(catFind.getId());

        assertEquals(catFind, epicById, "Епики не совпадают");
    }

    @Test
    public void addSubtaskById_Success() {
        Epic catFind = new Epic("Найти кошку.", "Нужно успеть за час.");
        catFind = taskManager.addEpic(catFind);
        Subtask findCatInKitchen = new Subtask("Поискать на кухне", "Обязательно посмотреть в холодильнике!", catFind.getId());

        taskManager.addSubtask(findCatInKitchen);
        Subtask mySubtask = taskManager.getSubtaskById(findCatInKitchen.getId());

        assertEquals(findCatInKitchen, mySubtask, "Подзадачи не совпадают");
    }

    @Test
    public void addTask_TaskWithPreinstalledIdsAndGeneratedIds_Success() {
        Task task1 = new Task("Купить молоко.", "В Фермер центре.");
        task1 = taskManager.addTask(task1);
        Task task2 = new Task("Купить хлеб.", "В пекарне рядом с домом.");
        task2.setId(task1.getId() + 1);
        task2 = taskManager.addTask(task2);
        Task task3 = new Task("Сделать ремонт", "В кухонной комнате");
        task3.setId(task2.getId() + 1);
        task3 = taskManager.addTask(task3);
        Task task4 = new Task("Сделать ремонт", "В гостиной комнате");

        task4 = taskManager.addTask(task4);

        assertEquals(task3.getId() + 1, task4.getId(), "TaskManager некорректно обрабатывает задачи с заданным id");
    }

    @Test
    public void addSubtasks_SubtasksWithPreinstalledIdsAndGeneratedIds_Success() {
        Epic epic1 = new Epic("Покупки", "В магазине");
        epic1 = taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask("Купить молоко.", "В Фермер центре.", epic1.getId());
        subtask1 = taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Купить хлеб.", "В пекарне рядом с домом.", epic1.getId());
        subtask2.setId(subtask1.getId() + 1);
        subtask2 = taskManager.addSubtask(subtask2);
        Subtask subtask3 = new Subtask("Сделать ремонт", "В кухонной комнате", epic1.getId());
        subtask3.setId(subtask2.getId() + 1);
        subtask3 = taskManager.addSubtask(subtask3);
        Subtask subtask4 = new Subtask("Сделать ремонт", "В гостиной комнате", epic1.getId());

        subtask4 = taskManager.addSubtask(subtask4);

        assertEquals(subtask3.getId() + 1, subtask4.getId(), "TaskManager некорректно обрабатывает подзадачи с заданным id");
    }

    @Test
    public void addEpic_EpicWithPreinstalledIdsAndGeneratedIds_Success() {
        Epic epic1 = new Epic("Купить молоко.", "В Фермер центре.");
        epic1 = taskManager.addEpic(epic1);
        Epic epic2 = new Epic("Купить хлеб.", "В пекарне рядом с домом.");
        epic2.setId(epic1.getId() + 1);
        epic2 = taskManager.addEpic(epic2);
        Epic epic3 = new Epic("Сделать ремонт", "В кухонной комнате");
        epic3.setId(epic2.getId() + 1);
        epic3 = taskManager.addEpic(epic3);
        Epic epic4 = new Epic("Сделать ремонт", "В гостиной комнате");

        epic4 = taskManager.addEpic(epic4);

        assertEquals(epic3.getId() + 1, epic4.getId(), "TaskManager некорректно обрабатывает эпики с заданным id");
    }

}