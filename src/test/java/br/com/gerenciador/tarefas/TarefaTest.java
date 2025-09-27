package br.com.gerenciador.tarefas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class TarefaTest {

    @Test
    public void deveCriarTarefaNaoConcluida() {
        Tarefa t = new Tarefa("Estudar", LocalDate.now(), Prioridade.MEDIA);
        assertFalse(t.isConcluida());
    }

    @Test
    public void deveConcluirTarefa() {
        Tarefa t = new Tarefa("Fazer exercício", LocalDate.now(), Prioridade.ALTA);
        t.concluir();
        assertTrue(t.isConcluida());
    }

    @Test
    public void deveTerDescricaoCorreta() {
        Tarefa t = new Tarefa("Comprar pão", LocalDate.now(), Prioridade.BAIXA);
        assertEquals("Comprar pão", t.getDescricao());
    }

    @Test
    public void deveTerPrioridadeCorreta() {
        Tarefa t = new Tarefa("Projeto final", LocalDate.now(), Prioridade.ALTA);
        assertEquals(Prioridade.ALTA, t.getPrioridade());
    }

    @Test
    public void deveTerPrazoCorreto() {
        LocalDate amanha = LocalDate.now().plusDays(1);
        Tarefa t = new Tarefa("Data Amanhã", amanha, Prioridade.MEDIA);
        assertEquals(amanha, t.getPrazo());
    }

    @Test
    public void concluirDuasVezesDeveManterConcluida() {
        Tarefa t = new Tarefa("Duplo Check", LocalDate.now(), Prioridade.ALTA);
        t.concluir();
        t.concluir(); // Chama novamente
        assertTrue(t.isConcluida());
    }

    @Test
    public void deveReconhecerPrioridadeBaixa() {
        Tarefa t = new Tarefa("Sem pressa", LocalDate.now(), Prioridade.BAIXA);
        assertEquals(Prioridade.BAIXA, t.getPrioridade());
    }
}