package taskmanager.manager;

import org.junit.jupiter.api.Test;
import taskmanager.task.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    @Test
    void add() {
        Task task = new Task("Покормить собаку.", "С выполнением команд.");
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }
}