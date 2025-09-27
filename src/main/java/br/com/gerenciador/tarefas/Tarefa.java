package br.com.gerenciador.tarefas;

import java.time.LocalDate;

public class Tarefa {
    private String descricao;
    private boolean concluida;
    private LocalDate prazo;
    private Prioridade prioridade;

    public Tarefa(String descricao, LocalDate prazo, Prioridade prioridade) {
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.concluida = false;
    }

    public String getDescricao() { return descricao; }
    public LocalDate getPrazo() { return prazo; }
    public Prioridade getPrioridade() { return prioridade; }
    public boolean isConcluida() { return concluida; }

    public void concluir() { this.concluida = true; }
}
