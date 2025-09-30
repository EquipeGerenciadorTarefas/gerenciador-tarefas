package br.com.gerenciador.tarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Classe de testes para o TaskManager (antigo Gerenciador).
public class TaskManagerTest {

    private TaskManager taskManager;
    private Task mockTask; // Objeto Mockito para simular tarefas (antigo mockTarefa)

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager(); // Inicializa o sistema real que será testado.
        mockTask = Mockito.mock(Task.class); // Cria um mock do objeto Task.
    }

    // --- Testes de Funcionalidade Básica (CRUD) ---

    @Test
    public void shouldAddTask() {
        Task t = mock(Task.class);
        when(t.getDescription()).thenReturn("New task");
        taskManager.addTask(t);
        assertEquals(1, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldRemoveTaskByDescription() {
        Task t = mock(Task.class);
        when(t.getDescription()).thenReturn("Remove");
        taskManager.addTask(t);
        taskManager.removeTask("Remove");
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldCompleteTaskByDescription() {
        Task t = mock(Task.class);
        when(t.getDescription()).thenReturn("To complete");
        taskManager.addTask(t);
        taskManager.completeTask("To complete");
        verify(t, times(1)).markCompleted();
    }

    // --- Testes de Consulta e Filtragem ---

    @Test
    public void listAllTasksShouldReturnEmptyIfNoTasks() {
        assertTrue(taskManager.listAllTasks().isEmpty());
    }

    @Test
    public void listCompletedTasksShouldReturnOnlyCompleted() {
        Task t1 = mock(Task.class);
        when(t1.getDescription()).thenReturn("Done");
        taskManager.addTask(t1);
        taskManager.completeTask("Done");

        Task t2 = mock(Task.class);
        when(t2.getDescription()).thenReturn("Pending");
        taskManager.addTask(t2);

        assertEquals(1, taskManager.listCompletedTasks().size());
    }

    @Test
    public void listPendingTasksShouldExcludeCompleted() {
        Task t1 = mock(Task.class);
        when(t1.getDescription()).thenReturn("Completed");
        taskManager.addTask(t1);
        taskManager.completeTask("Completed");

        Task t2 = mock(Task.class);
        when(t2.getDescription()).thenReturn("Pending");
        taskManager.addTask(t2);

        assertEquals(1, taskManager.listPendingTasks().size());
    }

    @Test
    public void shouldListTasksByHighPriority() {
        Task t1 = mock(Task.class);
        Task t2 = mock(Task.class);
        Task t3 = mock(Task.class);

        when(t1.getPriority()).thenReturn(Priority.HIGH);
        when(t2.getPriority()).thenReturn(Priority.MEDIUM);
        when(t3.getPriority()).thenReturn(Priority.HIGH);

        taskManager.addTask(t1);
        taskManager.addTask(t2);
        taskManager.addTask(t3);

        assertEquals(2, taskManager.listByPriority(Priority.HIGH).size());
    }

    // --- Testes de Cenários Limite (Edge Cases) ---

    @Test
    public void shouldNotCompleteNonExistentTask() {
        Task t = mock(Task.class);
        when(t.getDescription()).thenReturn("Existing");
        taskManager.addTask(t);

        int countBefore = taskManager.listAllTasks().size();
        taskManager.completeTask("Non Existent");

        assertEquals(countBefore, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldNotAddNullTask() {
        taskManager.addTask(null);
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldNotRemoveNonExistentTask() {
        Task t = mock(Task.class);
        when(t.getDescription()).thenReturn("Keep");
        taskManager.addTask(t);

        taskManager.removeTask("Non Existent");
        assertEquals(1, taskManager.listAllTasks().size());
    }

    // --- Testes com Mockito (Testes de Unidade/Colaboração) ---

    @Test
    public void mockShouldVerifyIsCompletedCall() {
        mockTask.isCompleted();
        verify(mockTask, times(1)).isCompleted();
    }

    @Test
    public void mockShouldSimulateMediumPriority() {
        when(mockTask.getPriority()).thenReturn(Priority.MEDIUM);
        assertEquals(Priority.MEDIUM, mockTask.getPriority());
    }

    @Test
    public void mockSimulatingAddition() {
        taskManager.addTask(mockTask);
        assertEquals(1, taskManager.getTasks().size());
    }

    @Test
    public void mockVerifiesIfManagerCallsMarkCompleted() {
        when(mockTask.getDescription()).thenReturn("MOCK_TASK");
        taskManager.addTask(mockTask);

        taskManager.completeTask("MOCK_TASK");

        verify(mockTask, times(1)).markCompleted();
    }

    @Test
    public void mockSimulatingFutureDeadline() {
        when(mockTask.getDeadline()).thenReturn(LocalDate.now().plusDays(1));
        assertTrue(mockTask.getDeadline().isAfter(LocalDate.now()));
    }

    @Test
    public void shouldNotRemoveFromEmptyList() {
        Task fakeTask = mock(Task.class);
        when(fakeTask.getDescription()).thenReturn("NothingHere");

        taskManager.removeTask("NothingHere");
        assertTrue(taskManager.listAllTasks().isEmpty());
    }

    @Test
    public void shouldAddMultipleMockTasksAndCheckCount() {
        Task t1 = mock(Task.class);
        Task t2 = mock(Task.class);
        Task t3 = mock(Task.class);

        when(t1.getDescription()).thenReturn("T1");
        when(t2.getDescription()).thenReturn("T2");
        when(t3.getDescription()).thenReturn("T3");

        taskManager.addTask(t1);
        taskManager.addTask(t2);
        taskManager.addTask(t3);

        assertEquals(3, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldCompleteMockTaskAndVerifyItIsNotPending() {
        Task fakeTask = mock(Task.class);
        when(fakeTask.getDescription()).thenReturn("FinishMe");

        taskManager.addTask(fakeTask);
        taskManager.completeTask("FinishMe");

        verify(fakeTask, times(1)).markCompleted();
        assertFalse(taskManager.listPendingTasks().contains(fakeTask));
    }

    @Test
    public void mockShouldReturnFutureDeadline() {
        Task fakeTask = mock(Task.class);
        when(fakeTask.getDeadline()).thenReturn(LocalDate.now().plusDays(5));

        assertTrue(fakeTask.getDeadline().isAfter(LocalDate.now()));
    }

    @Test
    public void mockShouldVerifyDescriptionCalled() {
        Task fakeTask = mock(Task.class);
        fakeTask.getDescription();
        verify(fakeTask, times(1)).getDescription();
    }
}
