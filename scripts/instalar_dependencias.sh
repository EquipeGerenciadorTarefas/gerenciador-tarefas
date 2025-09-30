#!/bin/bash

# Script de instalação de dependências
# Autores: Raissa, Matheus e Tiago
# Disciplina: C14 - Engenharia de Software
# Professor: Gabriel Pivoto

echo "=========================================="
echo "  INSTALAÇÃO DE DEPENDÊNCIAS"
echo "=========================================="

# Atualiza lista de pacotes
echo "📦 Atualizando lista de pacotes..."
sudo apt-get update -y

# Instala Java JDK 11
echo "☕ Instalando Java JDK 11..."
sudo apt-get install -y openjdk-11-jdk

# Verifica instalação do Java
java -version
if [ $? -eq 0 ]; then
    echo "✅ Java instalado com sucesso!"
else
    echo "❌ Erro ao instalar Java"
    exit 1
fi

# Instala Maven
echo "🔧 Instalando Maven..."
sudo apt-get install -y maven

# Verifica instalação do Maven
mvn -version
if [ $? -eq 0 ]; then
    echo "✅ Maven instalado com sucesso!"
else
    echo "❌ Erro ao instalar Maven"
    exit 1
fi

# Instala ferramentas de e-mail (opcional, para envio real)
echo "📧 Instalando ferramentas de e-mail..."
sudo apt-get install -y mailutils curl

# Configurações de ambiente
echo "🔧 Configurando variáveis de ambiente..."
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

echo "=========================================="
echo "✅ Todas as dependências foram instaladas!"
echo "=========================================="
echo ""
echo "Versões instaladas:"
echo "-------------------"
java -version
echo ""
mvn -version
echo ""
echo "=========================================="

exit 0