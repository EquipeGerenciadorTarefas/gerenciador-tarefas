# Verifica se o endereço de e-mail foi passado via variável de ambiente 
if [ -z "$EMAIL_DESTINO" ]; then
  echo "ERRO: Variavel de ambiente EMAIL_DESTINO nao foi configurada. Falha na entrega."
  exit 1
fi

# Recebe o status da execução do pipeline como argumento (passado pelo ci-cd.yml)
STATUS=$1

# Define o conteúdo do e-mail com base no status
if [ "$STATUS" == "SUCESSO" ]; then
    ASSUNTO="✅ CI/CD SUCESSO! - Pipeline de Gerenciador de Tarefas"
    CORPO="Pipeline executado com SUCESSO! Testes e build foram concluidos."
else
    ASSUNTO="❌ CI/CD FALHA! - Pipeline de Gerenciador de Tarefas"
    CORPO="ATENÇÃO: Pipeline executado com FALHA! Verifique o log no GitHub Actions."
fi

# Adiciona informações de log
CORPO+="\nDestinatário: $EMAIL_DESTINO"
CORPO+="\nStatus: $STATUS"

# SIMULAÇÃO de envio de e-mail.
echo "--- SIMULANDO ENVIO DE E-MAIL ---"
echo "Para: $EMAIL_DESTINO"
echo "Assunto: $ASSUNTO"
echo -e "$CORPO" # O -e permite quebra de linha
echo "--- FIM SIMULACAO ---"

# O script retorna 0 para indicar sucesso na execução do script.
exit 0