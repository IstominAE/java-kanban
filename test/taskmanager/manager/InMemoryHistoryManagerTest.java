package taskmanager.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.task.Status;
import taskmanager.task.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {
    InMemoryHistoryManager historyManager;

    @BeforeEach
    public void init() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void addTask_Success() {
        Task task = new Task("Покормить собаку.", "С выполнением команд.");

        historyManager.add(task);

        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(1, history.size(), "История пустая.");
    }

    @Test
    void addTask_HistoryNotUpdateTaskFields() {
        Task task = new Task("Покормить собаку.", "С выполнением команд.");

        historyManager.add(task);

        assertEquals("Покормить собаку.", task.getName(), "Название задачи в истории изменилось, при изменении в задаче после добавления в историю");
        assertEquals("С выполнением команд.", task.getDescription(), "Описание задачи в истории изменилось, при изменении в задаче после добавления в историю");
        assertEquals(Status.NEW, task.getStatus(), "Статус задачи в истории изменился, при изменении в задаче после добавления в историю");
    }

    @Test
    void addTask_TaskFieldsAreImmutableInHistory() {
        Task task = new Task("Покормить собаку.", "С выполнением команд.");
        historyManager.add(task);
        task.setDescription("Новое описание.");
        task.setName("Новое название");
        task.setStatus(Status.IN_PROGRESS);

        final List<Task> history = historyManager.getHistory();
        Task result = history.getFirst();

        assertEquals("Покормить собаку.", result.getName(), "Название задачи в истории изменилось, при изменении в задаче после добавления в историю");
        assertEquals("С выполнением команд.", result.getDescription(), "Описание задачи в истории изменилось, при изменении в задаче после добавления в историю");
        assertEquals(Status.NEW, result.getStatus(), "Статус задачи в истории изменился, при изменении в задаче после добавления в историю");
    }
}