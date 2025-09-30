# Gerenciador de Tarefas

Sistema desenvolvido em Java para gerenciar tarefas, com suporte a:
- Adicionar, editar e remover tarefas
- Visualizar tarefas concluídas e pendentes
- Testes unitários e mock (20 cenários)
- Automação de build e CI/CD com GitHub Actions

## Integrantes
- Matheus Cesar Reis
- Raissa Rodrigues
- Tiago Rodrigues Gregorio


## Pré-requisitos
- Java JDK 11 ou superior
- Maven ou Gradle
- Git

## Como executar
1. Clone o repositório:
```bash
git clone <link-do-repositorio>

## Pipeline CI/CD

-O projeto possui pipeline configurado com GitHub Actions, contendo:

##Jobs

-Testes: executa os testes unitários e com Mockito (20 cenários)

-Build / Empacotamento: compila e empacota o projeto

-Notificação: envia e-mail informando execução do pipeline (usa variável de ambiente para destinatário)

-Execução paralela

-O job de Notificação é executado em paralelo aos demais jobs.

##Artefatos

-O pacote final e o relatório de testes são armazenados como artefatos no GitHub Actions.

-Script de notificação

-Envia e-mail informando: "Pipeline executado!"

-O e-mail do destinatário não é hardcoded, utiliza variável de ambiente configurada no GitHub Actions.

##Testes

-O projeto possui 20 cenários de teste unitário e mock, distribuídos entre TaskManagerTest e TaskTest.

-Cobrem:

-Criação, conclusão e remoção de tarefas

-Priorização e deadlines

-Idempotência do método markCompleted

--Validação de interações via Mockito

Todos os testes são executados automaticamente pelo pipeline CI/CD.

##Observações

-Todos os integrantes realizaram commits relevantes no código.

-O pipeline informa claramente se os testes e a build obtiveram sucesso ou falha.

-Segue os objetivos do exercício prático C14 - Engenharia de Software (Professor: Gabriel Pivoto), incluindo a criação e manutenção de pipelines com GitHub Actions.

##Referências

-Slides, livros e aulas fornecidos pelo professor