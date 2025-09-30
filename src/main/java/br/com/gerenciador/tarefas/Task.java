package br.com.gerenciador.tarefas;

import java.time.LocalDate;

public class Task {
    // Variáveis de instância privadas (Encapsulamento)
    private String description;
    private boolean isCompleted;
    private LocalDate deadline;
    private Priority priority;

    // Construtor: Inicializa a tarefa. 'isCompleted' é sempre 'false' por padrão.
    public Task(String description, LocalDate deadline, Priority priority) {
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = false; // A tarefa começa sempre como não concluída
    }

    // --- Métodos Getters ---

    // Retorna a descrição da tarefa.
    public String getDescription() { return description; }

    // Retorna o prazo final da tarefa (usando o moderno LocalDate).
    public LocalDate getDeadline() { return deadline; }

    // Retorna a prioridade da tarefa (usando o enum Priority).
    public Priority getPriority() { return priority; }

    // Verifica se a tarefa está concluída.
    public boolean isCompleted() { return isCompleted; }

    // --- Método de Ação ---

    // Altera o estado da tarefa para concluída. Este é o único "setter" que permite mudança de estado.
    public void markCompleted() { this.isCompleted = true; }

    /*
     * Nota: Métodos toString(), equals() e hashCode() são geralmente adicionados aqui
     * para melhorar a legibilidade (logs) e o uso em coleções, mas não estão presentes nesta versão.
     */
}