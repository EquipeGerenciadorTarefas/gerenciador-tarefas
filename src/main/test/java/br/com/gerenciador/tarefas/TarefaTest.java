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
}
