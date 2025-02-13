package taskmanager.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    public void equals_SubtaskWithEqualIdBeEqual() {
        Subtask subtask1 = new Subtask("Купить молоко.", "В Фермер центре.", 8);
        Subtask subtask2 = new Subtask("Купить хлеб.", "В пекарне рядом с домом.", 8);
        subtask1.setId(1);
        subtask2.setId(1);

        assertEquals(subtask1, subtask2, "Ошибка! Экземпляры класса Subtask должны быть равны друг другу, если равен их id.");
    }
    @Test
    public void setEpicId_SubtaskCannotBeAddedToItself() {
        Subtask subtask = new Subtask("Купить молоко.", "В Фермер центре.", 8);
        subtask.setId(1);

        subtask.setEpicId(subtask.getId());

        assertNotEquals(subtask.getId(), subtask.getEpicId(), "Ошибка подзадача была указана самой себе в качестве Эпика.");
    }
}