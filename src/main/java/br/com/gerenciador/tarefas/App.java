package br.com.gerenciador.tarefas;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Gerenciador g = new Gerenciador();

        g.adicionarTarefa(new Tarefa("Estudar CI/CD", LocalDate.now().plusDays(2), Prioridade.ALTA));
        g.adicionarTarefa(new Tarefa("Implementar pipeline", LocalDate.now().plusDays(5), Prioridade.MEDIA));

        System.out.println("Todas as tarefas:");
        g.listarTarefas().forEach(t -> 
            System.out.println("- " + t.getDescricao() + " [" + t.getPrioridade() + "]")
        );

        g.concluirTarefa("Estudar CI/CD");

        System.out.println("\nConcluídas:");
        g.listarConcluidas().forEach(t -> 
            System.out.println("- " + t.getDescricao())
        );
    }
}
