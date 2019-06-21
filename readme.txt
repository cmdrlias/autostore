#Trabalho de Desenvolvimento Web
Desenvolvido por: Larissa Silva (11713155) e Raí Aris (11715300)

##Antes do deploy

1. Ter java 11 na máquina
2. Ter Gradle atualizado na máquina
3. Rodar o arquivo runme.sh através do comando "./runme.sh" no terminal

##Instruções de deploy

Pela IDE:
1. Ter Eclipse ou Intelij Idea com frameworks de Spring Boot e Gradle instalados;
2. Buildar projeto com Gradle;
3. Executar projeto normalmente.

Pela linha de comando:
1. Navegar até a pasta do projeto;
2. Executar o comando "./gradlew clean assamble war"
3. Executar o comando "java -jar build/libs/autostore.war --server.port=8080"
