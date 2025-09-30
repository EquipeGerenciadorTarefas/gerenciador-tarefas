package br.com.gerenciador.tarefas;

import java.time.LocalDate;

// Classe principal para execução e demonstração.
public class App {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Cria e adiciona tarefas ao gerenciador
        manager.addTask(new Task("Study CI/CD", LocalDate.now().plusDays(2), Priority.HIGH));
        manager.addTask(new Task("Implement pipeline", LocalDate.now().plusDays(5), Priority.MEDIUM));

        System.out.println("All tasks:");
        // Itera sobre todas as tarefas e imprime descrição e prioridade
        manager.listAllTasks().forEach(t ->
                System.out.println("- " + t.getDescription() + " [" + t.getPriority() + "]")
        );

        // Conclui uma tarefa
        manager.completeTask("Study CI/CD");

        System.out.println("\nCompleted tasks:");
        // Lista e imprime apenas as tarefas concluídas
        manager.listCompletedTasks().forEach(t ->
                System.out.println("- " + t.getDescription())
        );
    }
}