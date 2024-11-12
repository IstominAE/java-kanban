import taskmanager.manager.TaskManager;
import taskmanager.task.Epic;
import taskmanager.task.Status;
import taskmanager.task.Subtask;
import taskmanager.task.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        Task myTask = new Task("Покормить собаку.", "С выполнением команд.");
        myTask = taskManager.addTask(myTask);
        System.out.println(myTask);

        myTask.setDescription("Не забыть про выполнение команд.");
        myTask.setStatus(Status.IN_PROGRESS);
        myTask = taskManager.updateTask(myTask);
        System.out.println(myTask);

        myTask.setName("Покормил!");
        myTask.setDescription("Ты идиот! Забыл про команды.");
        myTask.setStatus(Status.DONE);
        myTask = taskManager.updateTask(myTask);
        System.out.println(myTask);

        Task myTask2 = new Task("Покормить кошку.", "Вкусно");
        myTask2 = taskManager.addTask(myTask2);
        System.out.println(myTask2);

        Task myTask3 = new Task("Покормить хомячка.", "Чем-нибудь");
        myTask3 = taskManager.addTask(myTask3);
        System.out.println(myTask3);

        System.out.println("Все задачи:");
        System.out.println(taskManager.getAllTasks());
        taskManager.removeTaskById(myTask.getId());
        System.out.println("Удалил первую:");
        System.out.println(taskManager.getAllTasks());
        taskManager.clearAllTasks();
        System.out.println("Удалил все:");
        System.out.println(taskManager.getAllTasks());


        Epic catFind = new Epic("Найти кошку.", "Нужно успеть за час.");
        taskManager.addEpic(catFind);
        System.out.println(catFind);
        Subtask findCatInKitchen = new Subtask("Поискать на кухне", "Обязательно посмотреть в холодильнике!", catFind.getId());
        taskManager.addSubtask(findCatInKitchen);
        System.out.println(catFind);
        findCatInKitchen.setStatus(Status.IN_PROGRESS);
        System.out.println(findCatInKitchen);
        taskManager.updateSubtask(findCatInKitchen);
        System.out.println(catFind);

        Subtask findCatInLivingRoom = new Subtask("Поискать в гостиной", "Проверить в шкафах с одеждой!", catFind.getId());
        taskManager.addSubtask(findCatInLivingRoom);
        System.out.println(catFind);
        findCatInLivingRoom.setStatus(Status.DONE);
        System.out.println(findCatInLivingRoom);
        taskManager.updateSubtask(findCatInLivingRoom);
        System.out.println(catFind);
        System.out.println(taskManager.getSubtasksByEpic(catFind.getId()));
        taskManager.removeSubtaskById(findCatInKitchen.getId());
        System.out.println("Удалил подзадачу \"Поискать на кухне\":");
        System.out.println(catFind);
        System.out.println(taskManager.getSubtasksByEpic(catFind.getId()));
        taskManager.clearAllSubtasks();
        System.out.println("Удалил все подзадачи:");
        System.out.println(taskManager.getAllSubtasks());
        taskManager.clearAllEpics();

        Epic epic1 = new Epic("Прогулка", "Нужно успеть за час.");
        taskManager.addEpic(epic1);
        Epic epic2 = new Epic("Уборка", "Нужно успеть за час.");
        taskManager.addEpic(epic2);
        Epic epic3 = new Epic("Готовка", "Нужно успеть за час.");
        taskManager.addEpic(epic3);

        System.out.println("Все эпики:");
        System.out.println(taskManager.getAllEpics());
        taskManager.removeEpicById(epic1.getId());
        System.out.println("Удалил первый:");
        System.out.println(taskManager.getAllEpics());
        taskManager.clearAllEpics();
        System.out.println("Удалил все:");
        System.out.println(taskManager.getAllEpics());
    }
}
