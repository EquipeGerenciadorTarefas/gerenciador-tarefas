package br.com.gerenciador.tarefas;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

// A classe TaskManager é responsável por gerenciar todas as tarefas.
public class TaskManager {
    // Lista privada que armazena todos os objetos Task.
    private List<Task> tasks = new ArrayList<>();

    // Adiciona uma nova tarefa à lista, se não for nula (defensive check).
    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    // Remove uma tarefa com base em sua descrição (case-insensitive).
    public void removeTask(String description) {
        // Usa removeIf com uma expressão lambda para remover o elemento correspondente.
        tasks.removeIf(t -> t.getDescription().equalsIgnoreCase(description));
    }

    // Marca uma tarefa como concluída com base em sua descrição.
    public void completeTask(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                t.markCompleted(); // Chama o método de atualização de estado na classe Task
                break; // Paramos assim que a tarefa é encontrada e concluída
            }
        }
    }

    // --- Métodos de Listagem e Consulta (Query) ---

    // Retorna a lista completa de todas as tarefas (pendentes e concluídas).
    public List<Task> listAllTasks() {
        return tasks;
    }

    // Retorna uma nova lista contendo apenas as tarefas concluídas (usando Stream API).
    public List<Task> listCompletedTasks() {
        return tasks.stream().filter(Task::isCompleted).collect(Collectors.toList());
    }

    // Retorna uma nova lista contendo apenas as tarefas pendentes.
    public List<Task> listPendingTasks() {
        return tasks.stream().filter(t -> !t.isCompleted()).collect(Collectors.toList());
    }

    // Retorna uma nova lista de tarefas filtradas por um nível de prioridade específico.
    public List<Task> listByPriority(Priority priority) {
        return tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .collect(Collectors.toList());
    }

    // --- Métodos Adicionais (geralmente para Testes ou Casos Específicos) ---

    // Retorna a lista interna. Útil para testes de unidade.
    public List<Task> getTasks() {
        return tasks;
    }

    // Lista tarefas pendentes ordenadas por prioridade decrescente (HIGH > MEDIUM > LOW).
    public List<Task> listSortedTasks() {
        return tasks.stream()
                .filter(t -> !t.isCompleted()) // Filtra apenas as pendentes
                .sorted(Comparator.comparing(Task::getPriority).reversed()) // Ordena pelo Enum (HIGH primeiro)
                .collect(Collectors.toList());
    }
}