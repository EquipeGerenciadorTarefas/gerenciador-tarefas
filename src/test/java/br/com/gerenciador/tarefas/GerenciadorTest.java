package br.com.gerenciador.tarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GerenciadorTest {

    private Gerenciador gerenciador;
    private Tarefa mockTarefa; // Objeto Mockito para simular tarefas

    // Configuração inicial antes de cada teste.
    @BeforeEach
    public void setUp() {
        gerenciador = new Gerenciador();
        mockTarefa = Mockito.mock(Tarefa.class); // Cria o mock
    }

    @Test
    public void deveAdicionarTarefa() {
        gerenciador.adicionarTarefa(new Tarefa("Nova tarefa", LocalDate.now(), Prioridade.MEDIA));
        assertEquals(1, gerenciador.listarTarefas().size());
    }

    @Test
    public void deveRemoverTarefaPorDescricao() {
        gerenciador.adicionarTarefa(new Tarefa("Remover", LocalDate.now(), Prioridade.BAIXA));
        gerenciador.removerTarefa("Remover");
        assertEquals(0, gerenciador.listarTarefas().size());
    }

    @Test
    public void deveConcluirTarefaPorDescricao() {
        gerenciador.adicionarTarefa(new Tarefa("Concluir", LocalDate.now(), Prioridade.ALTA));
        gerenciador.concluirTarefa("Concluir");

        assertEquals(1, gerenciador.listarConcluidas().size());
    }

    @Test
    public void listarTarefasDeveRetornarVazioSeNaoHaTarefas() {
        assertTrue(gerenciador.listarTarefas().isEmpty());
    }

    @Test
    public void listarConcluidasDeveRetornarApenasConcluidas() {
        Tarefa t1 = new Tarefa("Feita", LocalDate.now(), Prioridade.ALTA);
        gerenciador.adicionarTarefa(t1);
        gerenciador.concluirTarefa("Feita");

        gerenciador.adicionarTarefa(new Tarefa("Pendente", LocalDate.now(), Prioridade.MEDIA));

        assertEquals(1, gerenciador.listarConcluidas().size());
    }

    @Test
    public void listarPendentesDeveExcluirConcluidas() {
        Tarefa t1 = new Tarefa("Concluida", LocalDate.now(), Prioridade.ALTA);
        gerenciador.adicionarTarefa(t1);
        gerenciador.concluirTarefa("Concluida");

        Tarefa t2 = new Tarefa("Pendente", LocalDate.now(), Prioridade.MEDIA);
        gerenciador.adicionarTarefa(t2);

        // A lista de pendentes deve ter apenas 1 item.
        assertEquals(1, gerenciador.listarPendentes().size(), "A lista pendente deve ter apenas o item 'Pendente'.");
    }

    @Test
    public void deveListarTarefasPorPrioridadeAlta() {
        gerenciador.adicionarTarefa(new Tarefa("Alta1", LocalDate.now(), Prioridade.ALTA));
        gerenciador.adicionarTarefa(new Tarefa("Media1", LocalDate.now(), Prioridade.MEDIA));
        gerenciador.adicionarTarefa(new Tarefa("Alta2", LocalDate.now(), Prioridade.ALTA));

        // Deve retornar 2 tarefas com prioridade ALTA
        assertEquals(2, gerenciador.listarPorPrioridade(Prioridade.ALTA).size());
    }

    @Test
    public void naoDeveConcluirTarefaInexistente() {
        gerenciador.adicionarTarefa(new Tarefa("Existente", LocalDate.now(), Prioridade.BAIXA));
        int countAntes = gerenciador.listarTarefas().size();

        gerenciador.concluirTarefa("Nao Existe");

        assertEquals(countAntes, gerenciador.listarTarefas().size());
    }

    @Test
    public void naoDeveAdicionarTarefaNula() {
        gerenciador.adicionarTarefa(null);
        assertEquals(0, gerenciador.listarTarefas().size());
    }

    @Test
    public void naoDeveRemoverTarefaInexistente() {
        gerenciador.adicionarTarefa(new Tarefa("Manter", LocalDate.now(), Prioridade.BAIXA));
        gerenciador.removerTarefa("Nao Existe");
        assertEquals(1, gerenciador.listarTarefas().size());
    }

    // Testes Mock
    @Test
    public void mockDeveVerificarChamadaDeIsConcluida() {
        mockTarefa.isConcluida();
        // Verifica se o Gerenciador (ou qualquer código) chamou o isConcluida() da Tarefa.
        verify(mockTarefa, times(1)).isConcluida();
    }

    @Test
    public void mockDeveSimularPrioridadeMedia() {
        when(mockTarefa.getPrioridade()).thenReturn(Prioridade.MEDIA);
        assertEquals(Prioridade.MEDIA, mockTarefa.getPrioridade());
    }

    @Test
    public void mockSimulandoAdicao() {

        gerenciador.adicionarTarefa(mockTarefa);
        assertEquals(1, gerenciador.getTarefas().size());
    }

    @Test
    public void mockVerificaSeGerenciadorChamaConcluir() {
        when(mockTarefa.getDescricao()).thenReturn("TAREFA_MOCK");
        gerenciador.adicionarTarefa(mockTarefa);

        gerenciador.concluirTarefa("TAREFA_MOCK");
        verify(mockTarefa, times(1)).concluir();
    }

    @Test
    public void mockSimulandoDataVencimentoFutura() {
        when(mockTarefa.getPrazo()).thenReturn(LocalDate.now().plusDays(1));

        LocalDate data = mockTarefa.getPrazo();
        assertTrue(data.isAfter(LocalDate.now()));
    }
}