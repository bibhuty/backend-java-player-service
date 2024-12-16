# One time step and then comment them
#docker pull ollama/ollama
#docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
#docker exec -it ollama ollama run tinyllama &

# All time step
mvn clean install -DskipTests
mvn spring-boot:run