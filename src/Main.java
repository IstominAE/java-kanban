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


        Epic catFind = new Epic("Найти кошку.", "Нужно успеть за час.");
        taskManager.addEpic(catFind);
        System.out.println(catFind);
        Subtask findCatInKitchen = new Subtask("Поискать на кухне", "Обязательно посмотреть в холодильнике!", catFind.getId());
        taskManager.addSubtask(findCatInKitchen);
        System.out.println(catFind);
        findCatInKitchen.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(findCatInKitchen);
        System.out.println(catFind);

        Subtask findCatInLivingRoom = new Subtask("Поискать в гостиной", "Проверить в шкафах с одеждой!", catFind.getId());
        taskManager.addSubtask(findCatInLivingRoom);
        System.out.println(catFind);
        findCatInLivingRoom.setStatus(Status.DONE);
        taskManager.updateSubtask(findCatInLivingRoom);
        System.out.println(catFind);


    }
}
