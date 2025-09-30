#!/bin/bash

# Script de notificação por e-mail
# Autores: Raissa, Matheus e Tiago
# Disciplina: C14 - Engenharia de Software

# ===== VALIDAÇÕES =====
if [ -z "$EMAIL_DESTINO" ]; then
  echo "ERRO: Variável de ambiente EMAIL_DESTINO não configurada."
  echo "Configure no GitHub Actions: env -> EMAIL_DESTINO"
  exit 1
fi

# Verifica se credenciais SMTP estão configuradas
if [ -z "$SMTP_USER" ] || [ -z "$SMTP_PASSWORD" ]; then
  echo "AVISO: Credenciais SMTP não configuradas. Executando em modo simulação."
  MODO_SIMULACAO=true
else
  MODO_SIMULACAO=false
fi

# Recebe o status da execução do pipeline como argumentos
STATUS=${1:-"DESCONHECIDO"}
TESTE_STATUS=${2:-"não informado"}
BUILD_STATUS=${3:-"não informado"}

echo "=========================================="
echo "  NOTIFICAÇÃO DE PIPELINE CI/CD"
echo "=========================================="
echo "Status Geral: $STATUS"
echo "Status dos Testes: $TESTE_STATUS"
echo "Status do Build: $BUILD_STATUS"
echo "Destinatários: $EMAIL_DESTINO"
echo "Modo: $([ "$MODO_SIMULACAO" = true ] && echo "SIMULAÇÃO" || echo "ENVIO REAL")"
echo "=========================================="

# ===== DEFINIÇÃO DO CONTEÚDO DO E-MAIL =====
if [ "$STATUS" == "SUCESSO" ] || [ "$STATUS" == "success" ]; then
  ASSUNTO="CI/CD SUCESSO - Pipeline de Gerenciador de Tarefas"
  CORPO="Pipeline executado com SUCESSO! Testes e build foram concluídos."
  ICONE="SUCESSO"
else
  ASSUNTO="CI/CD FALHA - Pipeline de Gerenciador de Tarefas"
  CORPO="Pipeline executado com FALHA! Verifique o log no GitHub Actions."
  ICONE="FALHA"
fi

# ===== INFORMAÇÕES ADICIONAIS =====
CORPO="$CORPO\n\n"
CORPO="${CORPO}========================================\n"
CORPO="${CORPO}RESUMO DA EXECUCAO\n"
CORPO="${CORPO}========================================\n"
CORPO="${CORPO}Status Final: $ICONE - $STATUS\n"
CORPO="${CORPO}Testes: $TESTE_STATUS\n"
CORPO="${CORPO}Build: $BUILD_STATUS\n"
CORPO="${CORPO}Destinatarios: $EMAIL_DESTINO\n"
CORPO="${CORPO}========================================\n\n"
CORPO="${CORPO}Projeto: Gerenciador de Tarefas\n"
CORPO="${CORPO}Equipe: Raissa Rodrigues, Matheus Cesar, Tiago Gregorio\n"
CORPO="${CORPO}Disciplina: C14 - Engenharia de Software\n"
CORPO="${CORPO}Professor: Gabriel Pivoto\n\n"
CORPO="${CORPO}Acesse o GitHub Actions para mais detalhes:\n"
CORPO="${CORPO}https://github.com/Raissarodrigues15/gerenciador-tarefas/actions\n"

# ===== MODO SIMULAÇÃO =====
if [ "$MODO_SIMULACAO" = true ]; then
  echo ""
  echo "--- SIMULANDO ENVIO DE E-MAIL ---"
  echo "Para: $EMAIL_DESTINO"
  echo "Assunto: $ASSUNTO"
  echo -e "Corpo:\n$CORPO"
  echo "--- FIM SIMULACAO ---"
  echo ""
  echo "Script de notificacao executado com sucesso (simulacao)!"
  exit 0
fi

# ===== ENVIO REAL DE E-MAIL VIA GMAIL =====
echo ""
echo "Enviando e-mails via Gmail SMTP..."
echo ""

SMTP_SERVER="${SMTP_SERVER:-smtp.gmail.com}"
SMTP_PORT="${SMTP_PORT:-587}"

# Separa os destinatários e envia para cada um
IFS=',' read -ra EMAILS <<< "$EMAIL_DESTINO"
EMAIL_ENVIADOS=0
EMAIL_FALHAS=0

for email in "${EMAILS[@]}"; do
  email=$(echo "$email" | xargs)

  echo "Enviando para: $email"

  # Cria arquivo temporário com o conteúdo do e-mail
  TEMP_EMAIL=$(mktemp)
  cat > "$TEMP_EMAIL" <<EOF
From: CI/CD Pipeline <$SMTP_USER>
To: $email
Subject: $ASSUNTO
Content-Type: text/plain; charset=UTF-8

$CORPO
EOF

  # Envia usando curl com Gmail SMTP
  RESULTADO=$(curl -s --url "smtp://$SMTP_SERVER:$SMTP_PORT" \
    --ssl-reqd \
    --mail-from "$SMTP_USER" \
    --mail-rcpt "$email" \
    --user "$SMTP_USER:$SMTP_PASSWORD" \
    --upload-file "$TEMP_EMAIL" 2>&1)

  if [ $? -eq 0 ]; then
    echo "  Enviado com sucesso!"
    ((EMAIL_ENVIADOS++))
  else
    echo "  Falha no envio"
    echo "  Erro: $RESULTADO"
    ((EMAIL_FALHAS++))
  fi

  rm -f "$TEMP_EMAIL"
  sleep 1
done

echo ""
echo "=========================================="
echo "RESULTADO DO ENVIO"
echo "=========================================="
echo "Enviados: $EMAIL_ENVIADOS"
echo "Falhas: $EMAIL_FALHAS"
echo "Total: ${#EMAILS[@]}"
echo "=========================================="

if [ $EMAIL_FALHAS -gt 0 ]; then
  echo "AVISO: Alguns e-mails falharam. Verifique as credenciais SMTP."
  exit 1
else
  echo "Todos os e-mails foram enviados com sucesso!"
  exit 0
fi