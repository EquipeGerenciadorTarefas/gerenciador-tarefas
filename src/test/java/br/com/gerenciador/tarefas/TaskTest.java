package br.com.gerenciador.tarefas; // Novo pacote em inglês

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

// Classe de testes para o modelo Task (antiga Tarefa).
// Estes são testes de unidade puros, sem mocks ou dependências externas.
public class TaskTest {

    @Test
    public void shouldCreateUncompletedTask() {
        // Verifica se o construtor inicializa a tarefa corretamente como NÃO concluída.
        Task t = new Task("Study", LocalDate.now(), Priority.MEDIUM);
        assertFalse(t.isCompleted());
    }

    @Test
    public void shouldMarkTaskCompleted() {
        // Testa o método de alteração de estado 'markCompleted' (antigo concluir()).
        Task t = new Task("Do exercise", LocalDate.now(), Priority.HIGH);
        t.markCompleted();
        assertTrue(t.isCompleted());
    }

    @Test
    public void shouldHaveCorrectDescription() {
        // Verifica se o getter da descrição retorna o valor passado no construtor.
        Task t = new Task("Buy bread", LocalDate.now(), Priority.LOW);
        assertEquals("Buy bread", t.getDescription());
    }

    @Test
    public void shouldHaveCorrectPriority() {
        // Verifica a correta atribuição e recuperação do enum Priority.
        Task t = new Task("Final project", LocalDate.now(), Priority.HIGH);
        assertEquals(Priority.HIGH, t.getPriority());
    }

    @Test
    public void shouldHaveCorrectDeadline() {
        // Testa a correta atribuição e recuperação do objeto LocalDate (prazo).
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Task t = new Task("Tomorrow's Date", tomorrow, Priority.MEDIUM);
        assertEquals(tomorrow, t.getDeadline());
    }

    @Test
    public void markingCompletedTwiceShouldRemainCompleted() {
        // Testa a idempotência: chamar o método 'markCompleted' várias vezes não muda o estado final.
        Task t = new Task("Double Check", LocalDate.now(), Priority.HIGH);
        t.markCompleted();
        t.markCompleted(); // Chama novamente
        assertTrue(t.isCompleted());
    }

    @Test
    public void shouldRecognizeLowPriority() {
        // Teste simples para garantir que a prioridade baixa é corretamente configurada.
        Task t = new Task("No rush", LocalDate.now(), Priority.LOW);
        assertEquals(Priority.LOW, t.getPriority());
    }
}