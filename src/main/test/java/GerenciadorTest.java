package br.com.gerenciador.tarefas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class GerenciadorTest {
    @Test
    public void deveAdicionarTarefa() {
        Gerenciador g = new Gerenciador();
        g.adicionarTarefa(new Tarefa("Nova tarefa", LocalDate.now(), Prioridade.MEDIA));
        assertEquals(1, g.listarTarefas().size());
    }

    @Test
    public void deveRemoverTarefaPorDescricao() {
        Gerenciador g = new Gerenciador();
        g.adicionarTarefa(new Tarefa("Remover", LocalDate.now(), Prioridade.BAIXA));
        g.removerTarefa("Remover");
        assertEquals(0, g.listarTarefas().size());
    }

    @Test
    public void deveConcluirTarefaPorDescricao() {
        Gerenciador g = new Gerenciador();
        g.adicionarTarefa(new Tarefa("Concluir", LocalDate.now(), Prioridade.ALTA));
        g.concluirTarefa("Concluir");
        assertTrue(g.listarTarefas().get(0).isConcluida());
    }
}
