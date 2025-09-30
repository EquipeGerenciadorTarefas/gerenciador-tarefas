package br.com.gerenciador.tarefas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

// Classe de testes para o modelo Task (antiga Tarefa).
// Todos os testes são unitários puros, sem mocks.
public class TaskTest {

    @Test
    public void shouldCreateUncompletedTask() {
        // Testa se uma tarefa criada é inicialmente NÃO concluída
        Task t = new Task("Study", LocalDate.now(), Priority.MEDIUM);
        assertFalse(t.isCompleted(), "Tarefa deve iniciar como não concluída");
    }

    @Test
    public void shouldMarkTaskCompleted() {
        // Testa se o método markCompleted altera o estado para concluído
        Task t = new Task("Do exercise", LocalDate.now(), Priority.HIGH);
        t.markCompleted();
        assertTrue(t.isCompleted(), "Tarefa deve estar marcada como concluída");
    }

    @Test
    public void shouldHaveCorrectDescription() {
        // Verifica se a descrição é armazenada corretamente
        Task t = new Task("Buy bread", LocalDate.now(), Priority.LOW);
        assertEquals("Buy bread", t.getDescription(), "Descrição deve corresponder à fornecida");
    }

    @Test
    public void shouldHaveCorrectPriority() {
        // Verifica se a prioridade é atribuída corretamente
        Task t = new Task("Final project", LocalDate.now(), Priority.HIGH);
        assertEquals(Priority.HIGH, t.getPriority(), "Prioridade deve ser HIGH");
    }

    @Test
    public void shouldHaveCorrectDeadline() {
        // Testa se o prazo é armazenado corretamente
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Task t = new Task("Tomorrow's Date", tomorrow, Priority.MEDIUM);
        assertEquals(tomorrow, t.getDeadline(), "Deadline deve corresponder à data fornecida");
    }

    @Test
    public void markingCompletedTwiceShouldRemainCompleted() {
        // Testa idempotência do método markCompleted: chamar duas vezes não altera o resultado
        Task t = new Task("Double Check", LocalDate.now(), Priority.HIGH);
        t.markCompleted();
        t.markCompleted();
        assertTrue(t.isCompleted(), "Tarefa deve permanecer concluída após múltiplas chamadas");
    }

    @Test
    public void shouldRecognizeLowPriority() {
        // Verifica que a prioridade baixa é reconhecida corretamente
        Task t = new Task("No rush", LocalDate.now(), Priority.LOW);
        assertEquals(Priority.LOW, t.getPriority(), "Prioridade deve ser LOW");
    }

    @Test
    public void shouldRecognizeMediumPriority() {
        // Verifica que a prioridade média é reconhecida corretamente
        Task t = new Task("Medium task", LocalDate.now(), Priority.MEDIUM);
        assertEquals(Priority.MEDIUM, t.getPriority(), "Prioridade deve ser MEDIUM");
    }

    @Test
    public void shouldRecognizeHighPriority() {
        // Verifica que a prioridade alta é reconhecida corretamente
        Task t = new Task("Urgent task", LocalDate.now(), Priority.HIGH);
        assertEquals(Priority.HIGH, t.getPriority(), "Prioridade deve ser HIGH");
    }

    @Test
    public void shouldNotBeCompletedInitially() {
        // Confirma que tarefas recém-criadas começam não concluídas
        Task t = new Task("Initial test", LocalDate.now(), Priority.LOW);
        assertFalse(t.isCompleted(), "Nova tarefa deve ser não concluída");
    }

    @Test
    public void shouldChangeStateToCompleted() {
        // Verifica que chamar markCompleted realmente muda o estado
        Task t = new Task("Complete me", LocalDate.now(), Priority.MEDIUM);
        assertFalse(t.isCompleted(), "Antes de marcar deve estar não concluída");
        t.markCompleted();
        assertTrue(t.isCompleted(), "Após marcar deve estar concluída");
    }

    @Test
    public void shouldMaintainDescriptionAfterCompletion() {
        // Confirma que a descrição não é alterada ao completar a tarefa
        Task t = new Task("Keep description", LocalDate.now(), Priority.HIGH);
        String desc = t.getDescription();
        t.markCompleted();
        assertEquals(desc, t.getDescription(), "Descrição deve permanecer igual após completar");
    }

    @Test
    public void shouldMaintainPriorityAfterCompletion() {
        // Confirma que a prioridade não muda após completar a tarefa
        Task t = new Task("Keep priority", LocalDate.now(), Priority.MEDIUM);
        Priority p = t.getPriority();
        t.markCompleted();
        assertEquals(p, t.getPriority(), "Prioridade deve permanecer igual após completar");
    }

    @Test
    public void shouldMaintainDeadlineAfterCompletion() {
        // Confirma que o prazo (deadline) não muda após completar a tarefa
        LocalDate deadline = LocalDate.now().plusDays(3);
        Task t = new Task("Keep deadline", deadline, Priority.LOW);
        t.markCompleted();
        assertEquals(deadline, t.getDeadline(), "Deadline deve permanecer igual após completar");
    }

    @Test
    public void shouldCreateTaskWithPastDeadline() {
        // Permite criar tarefas com prazo no passado
        LocalDate past = LocalDate.now().minusDays(1);
        Task t = new Task("Past task", past, Priority.HIGH);
        assertEquals(past, t.getDeadline(), "Deadline pode ser no passado");
    }

    @Test
    public void shouldCreateTaskWithTodayDeadline() {
        // Permite criar tarefas com prazo para hoje
        LocalDate today = LocalDate.now();
        Task t = new Task("Today task", today, Priority.MEDIUM);
        assertEquals(today, t.getDeadline(), "Deadline pode ser hoje");
    }

    @Test
    public void shouldMarkMultipleTasksCompleted() {
        // Testa conclusão de várias tarefas independentes
        Task t1 = new Task("Task 1", LocalDate.now(), Priority.LOW);
        Task t2 = new Task("Task 2", LocalDate.now(), Priority.MEDIUM);
        t1.markCompleted();
        t2.markCompleted();
        assertTrue(t1.isCompleted(), "Tarefa 1 deve estar concluída");
        assertTrue(t2.isCompleted(), "Tarefa 2 deve estar concluída");
    }

    @Test
    public void shouldNotAlterCompletedStateAccidentally() {
        // Verifica que operações de leitura não alteram estado da tarefa
        Task t = new Task("Stable task", LocalDate.now(), Priority.HIGH);
        t.markCompleted();
        assertTrue(t.isCompleted(), "Estado inicial concluído deve permanecer");
        LocalDate temp = t.getDeadline(); // operação de leitura
        assertTrue(t.isCompleted(), "Estado concluído não deve mudar após leitura");
        assertEquals(temp, t.getDeadline(), "Deadline não deve mudar após leitura");
    }
}
