package br.com.gerenciador.tarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Classe de testes para o TaskManager (antigo Gerenciador).
public class TaskManagerTest {

    private TaskManager taskManager;
    private Task mockTask; // Objeto Mockito para simular tarefas (antigo mockTarefa)

    // Configuração inicial (@BeforeEach) é executada antes de cada método de teste.
    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager(); // Inicializa o sistema real que será testado.
        // Cria um mock (simulação) do objeto Task.
        mockTask = Mockito.mock(Task.class);
    }

    // --- Testes de Funcionalidade Básica (CRUD) ---

    @Test
    public void shouldAddTask() {
        // Testa a adição de uma tarefa e verifica se o tamanho da lista é 1.
        taskManager.addTask(new Task("New task", LocalDate.now(), Priority.MEDIUM));
        assertEquals(1, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldRemoveTaskByDescription() {
        // Adiciona e remove a tarefa. Espera que a lista final tenha tamanho 0.
        taskManager.addTask(new Task("Remove", LocalDate.now(), Priority.LOW));
        taskManager.removeTask("Remove");
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldCompleteTaskByDescription() {
        // Adiciona a tarefa, conclui pelo nome e verifica se ela aparece na lista de concluídas.
        taskManager.addTask(new Task("To complete", LocalDate.now(), Priority.HIGH));
        taskManager.completeTask("To complete");

        assertEquals(1, taskManager.listCompletedTasks().size());
    }

    // --- Testes de Consulta e Filtragem ---

    @Test
    public void listAllTasksShouldReturnEmptyIfNoTasks() {
        // Verifica o estado inicial (lista vazia).
        assertTrue(taskManager.listAllTasks().isEmpty());
    }

    @Test
    public void listCompletedTasksShouldReturnOnlyCompleted() {
        // Cria uma concluída e uma pendente, verificando se o filtro funciona.
        Task t1 = new Task("Done", LocalDate.now(), Priority.HIGH);
        taskManager.addTask(t1);
        taskManager.completeTask("Done"); // Marca como concluída

        taskManager.addTask(new Task("Pending", LocalDate.now(), Priority.MEDIUM));

        // Espera-se apenas 1 tarefa na lista de concluídas.
        assertEquals(1, taskManager.listCompletedTasks().size());
    }

    @Test
    public void listPendingTasksShouldExcludeCompleted() {
        // Cria uma concluída e uma pendente.
        Task t1 = new Task("Completed", LocalDate.now(), Priority.HIGH);
        taskManager.addTask(t1);
        taskManager.completeTask("Completed");

        Task t2 = new Task("Pending", LocalDate.now(), Priority.MEDIUM);
        taskManager.addTask(t2);

        // A lista de pendentes deve ter apenas 1 item.
        assertEquals(1, taskManager.listPendingTasks().size(), "A lista pendente deve ter apenas o item 'Pending'.");
    }

    @Test
    public void shouldListTasksByHighPriority() {
        // Adiciona tarefas de diferentes prioridades.
        taskManager.addTask(new Task("High1", LocalDate.now(), Priority.HIGH));
        taskManager.addTask(new Task("Medium1", LocalDate.now(), Priority.MEDIUM));
        taskManager.addTask(new Task("High2", LocalDate.now(), Priority.HIGH));

        // Deve retornar 2 tarefas com prioridade HIGH.
        assertEquals(2, taskManager.listByPriority(Priority.HIGH).size());
    }

    // --- Testes de Cenários Limite (Edge Cases) ---

    @Test
    public void shouldNotCompleteNonExistentTask() {
        // Verifica se a conclusão de uma tarefa inexistente não afeta o total de tarefas.
        taskManager.addTask(new Task("Existing", LocalDate.now(), Priority.LOW));
        int countBefore = taskManager.listAllTasks().size();

        taskManager.completeTask("Non Existent");

        assertEquals(countBefore, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldNotAddNullTask() {
        // Verifica se a checagem de nulo no método addTask() funciona.
        taskManager.addTask(null);
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    public void shouldNotRemoveNonExistentTask() {
        // Garante que a remoção de uma descrição não existente não remova outras tarefas.
        taskManager.addTask(new Task("Keep", LocalDate.now(), Priority.LOW));
        taskManager.removeTask("Non Existent");
        assertEquals(1, taskManager.listAllTasks().size());
    }

    // --- Testes com Mockito (Testes de Unidade/Colaboração) ---

    @Test
    public void mockShouldVerifyIsCompletedCall() {
        // Verifica se o método isCompleted() foi chamado no objeto mock.
        mockTask.isCompleted();
        // A sintaxe verify(mock, times(1)) garante que a colaboração ocorreu exatamente 1 vez.
        verify(mockTask, times(1)).isCompleted();
    }

    @Test
    public void mockShouldSimulateMediumPriority() {
        // Usa 'when...thenReturn' para forçar o mock a retornar um valor específico.
        when(mockTask.getPriority()).thenReturn(Priority.MEDIUM);
        assertEquals(Priority.MEDIUM, mockTask.getPriority());
    }

    @Test
    public void mockSimulatingAddition() {
        // Testa se o TaskManager consegue adicionar o mock na sua lista interna.
        taskManager.addTask(mockTask);
        assertEquals(1, taskManager.getTasks().size());
    }

    @Test
    public void mockVerifiesIfManagerCallsMarkCompleted() {
        // Este é um teste crucial: ele verifica a colaboração.
        // 1. Configura o mock para retornar uma descrição conhecida.
        when(mockTask.getDescription()).thenReturn("MOCK_TASK");
        taskManager.addTask(mockTask);

        // 2. Chama o método de negócio (TaskManager).
        taskManager.completeTask("MOCK_TASK");

        // 3. Verifica se o TaskManager CHAMOU o método markCompleted() no objeto mock.
        verify(mockTask, times(1)).markCompleted();
    }

    @Test
    public void mockSimulatingFutureDeadline() {
        // Simula a data de vencimento para amanhã.
        when(mockTask.getDeadline()).thenReturn(LocalDate.now().plusDays(1));

        LocalDate date = mockTask.getDeadline();
        // Verifica se a data simulada é realmente no futuro.
        assertTrue(date.isAfter(LocalDate.now()));
    }
}