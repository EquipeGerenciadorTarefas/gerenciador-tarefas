package br.com.gerenciador.tarefas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gerenciador {
    private List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void removerTarefa(String descricao) {
        tarefas.removeIf(t -> t.getDescricao().equalsIgnoreCase(descricao));
    }

    public void concluirTarefa(String descricao) {
        for (Tarefa t : tarefas) {
            if (t.getDescricao().equalsIgnoreCase(descricao)) {
                t.concluir();
                break;
            }
        }
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    public List<Tarefa> listarConcluidas() {
        return tarefas.stream().filter(Tarefa::isConcluida).collect(Collectors.toList());
    }

    public List<Tarefa> listarPendentes() {
        return tarefas.stream().filter(t -> !t.isConcluida()).collect(Collectors.toList());
    }

    public List<Tarefa> listarPorPrioridade(Prioridade prioridade) {
        return tarefas.stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }
}
