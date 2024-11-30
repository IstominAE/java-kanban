package taskmanager.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void equals_TasksWithEqualIdBeEqual() {
        Task task1 = new Task("Купить молоко.", "В Фермер центре.");
        Task task2 = new Task("Купить хлеб.", "В пекарне рядом с домом.");
        task1.setId(1);
        task2.setId(1);

        assertEquals(task1, task2, "Ошибка! Экземпляры класса Task должны быть равны друг другу, если равен их id.");
    }

}
