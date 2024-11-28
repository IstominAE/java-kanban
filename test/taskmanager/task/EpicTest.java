package taskmanager.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    @Test
    public void epicWithEqualIdBeEqual() {
        Epic epic1 = new Epic("Купить молоко.", "В Фермер центре.");
        Epic epic2 = new Epic("Купить хлеб.", "В пекарне рядом с домом.");
        epic1.setId(1);
        epic2.setId(1);
        assertEquals(epic1, epic2, "Ошибка! Экземпляры класса Epic должны быть равны друг другу, если равен их id.");
    }

    @Test
    public void epicCannotBeAddedToItself() {
      Epic epic =  new Epic("Купить молоко.", "В Фермер центре.");
      epic.setId(1);
      epic.addSubtask(epic.getId());
      assertEquals(0, epic.getSubtasks().size(), "Ошибка Epic был добавлен сам в себя в качестве подзадачи");
    }
}