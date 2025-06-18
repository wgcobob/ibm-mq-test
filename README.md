Para la prueba se necesita Docker instalado, ya que se ocupa una imagen de active MQ.

La imgagen a ocupar sera la siguiente
docker pull rmohr/activemq:latest

para levantar el contenedor se ocupa el siguiente comando
docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq:latest
