# Gerenciador de Tarefas - Sistema com Pipeline CI/CD

## 📋 Sobre o Projeto

Projeto desenvolvido para a disciplina C14 - Engenharia de Software, implementando um sistema completo de gerenciamento de tarefas com pipeline de CI/CD utilizando GitHub Actions.

**Integrantes:**
- Matheus Cesar Reis
- Raissa Rodrigues
- Tiago Rodrigues Gregório


## 🎯 Objetivos da Atividade

Atender todos os requisitos especificados no PDF da atividade:
- ✅ Sistema com pelo menos 20 cenários de teste unitário/mock
- ✅ Pipeline com 3 jobs (Testes, Empacotamento, Notificação)
- ✅ Execução paralela de jobs
- ✅ Armazenamento de artefatos (pacote + relatório)
- ✅ Script de e-mail com variável de ambiente
- ✅ Instalação automática de dependências

## 🛠️ Configuração do Ambiente

### Pré-requisitos
- Java JDK 11 ou superior
- Apache Maven 3.6+
- Git
- Conta no GitHub

### 1. Clone do Repositório
```bash
git clone https://github.com/EquipeGerenciadorTarefas/gerenciador-tarefas.git
cd gerenciador-tarefas
```

### 2. Verificação do Ambiente
```bash
# Verifique se o Java está instalado
java -version

# Verifique se o Maven está instalado
mvn -version

```

## 🧪 COMANDOS PARA EXECUÇÃO E CORREÇÃO

### Comando 1: Execução dos Testes Unitários e Mock
```bash
mvn test
```
**O que verificar:**
- Execução de todos os testes (mais de 20 cenários)
- Todos os testes devem passar (BUILD SUCCESS)

### Comando 2: Geração do Relatório de Testes
```bash
mvn test site
```
**Artefatos gerados:**
- `target/site/surefire-report.html` - Relatório detalhado dos testes
- `target/site/project-reports.html` - Informações do projeto
- Pasta `target/site/` contém todos os relatórios

### Comando 3: Build e Empacotamento
```bash
mvn clean package
```
**Resultado esperado:**
- Arquivo JAR gerado em `target/gerenciador-tarefas-1.0.0.jar`
- BUILD SUCCESS no final da execução
- Pacote pronto para distribuição

### Comando 4: Verificação da Compilação
```bash
mvn compile
```
**Verificar:**
- Código compila sem erros

### Comando 5: Limpeza do Projeto
```bash
mvn clean
```
Remove arquivos temporários e prepara para novo build.

## 📊 Pipeline CI/CD - Verificação

### Estrutura do Pipeline
O pipeline está configurado em `.github/workflows/ci-cd-pipeline.yml` e contém:

**Job 1: Testes**
- Executa testes unitários e com mock
- Gera relatórios de cobertura
- Armazena relatórios como artefato

**Job 2: Empacotamento** 
- Realiza build do projeto
- Gera pacote JAR
- Armazena artefato do pacote

**Job 3: Notificação**
- Executa script para envio de e-mail
- Utiliza variável de ambiente NOTIFICATION_EMAIL

```

## 🚀 Execução do Pipeline

### Execução Automática
O pipeline executa automaticamente a cada:
- Push para o repositório
- Pull request
- Disparo manual via interface do GitHub

### Verificação dos Resultados
Após execução, verifique:
- ✅ Todos os jobs passaram (ícone verde)
- ✅ Artefatos foram gerados e armazenados
- ✅ E-mail de notificação foi enviado
  
## 📦 Artefatos Gerados

### No GitHub Actions
- **Pacote JAR**: Download do arquivo .jar empacotado
- **Relatório de Testes**: Download dos relatórios Surefire
- **Logs de Execução**: Logs completos de cada job

### Localmente
- `target/gerenciador-tarefas-*.jar` - Pacote executável
- `target/surefire-reports/` - Relatórios de teste
- `target/site/` - Site com relatórios completos

## 🔍 Checklist de Verificação

### Requisitos Obrigatórios (PDF)
- [ ] **20+ cenários de teste**: `mvn test` mostra mais de 20 testes executados
- [ ] **3 jobs no pipeline**: Verificar em `.github/workflows/ci-cd-pipeline.yml`
- [ ] **Execução paralela**: Pelo menos um job roda em paralelo
- [ ] **Armazenamento de artefatos**: JAR e relatórios salvos
- [ ] **Script de e-mail** Existe e funcionando
- [ ] **Variável de ambiente**: NOTIFICATION_EMAIL configurada nos secrets
- [ ] **Instalação automática**: Dependências instaladas via script no pipeline

### Validação Técnica
- [ ] **Compilação**: `mvn compile` executa sem erros
- [ ] **Testes**: `mvn test` executa todos os testes com sucesso
- [ ] **Build**: `mvn package` gera pacote JAR
- [ ] **Relatórios**: `mvn test site` gera site com relatórios
- [ ] **Pipeline**: Executa completamente no GitHub Actions

## ❗ Solução de Problemas

### Erros Comuns e Soluções

**Problema: Java não encontrado**
```bash
# Verificar instalação do Java
java -version
# Se não instalado, baixar.
```

**Problema: Maven não encontrado**
```bash
# Verificar instalação do Maven
mvn -version
# Se não instalado, seguir: https://maven.apache.org/install.html
```
**Problema: Pipeline falhando no GitHub**
- Verificar logs no GitHub Actions
- Confirmar variáveis de ambiente configuradas
- Verificar permissões dos scripts

## Observações

-Todos os integrantes realizaram commits relevantes no código.

-O pipeline informa claramente se os testes e a build obtiveram sucesso ou falha.

-Segue os objetivos do exercício prático C14 - Engenharia de Software (Professor: Gabriel Pivoto), incluindo a criação e manutenção de pipelines com GitHub Actions.

##Referências

-Slides, livros e aulas fornecidos pelo professor

**Repositório:** https://github.com/EquipeGerenciadorTarefas/gerenciador-tarefas

**Pipeline:** https://github.com/EquipeGerenciadorTarefas/gerenciador-tarefas/actions

**Projeto de Referência:** https://github.com/GabrielPivoto/C214

---
*Este projeto atende todos os requisitos especificados na atividade prática de CI/CD com GitHub Actions.*
