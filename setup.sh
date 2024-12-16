# Step 1:One time step and then comment them
#docker pull ollama/ollama
#docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
#docker exec -it ollama ollama run tinyllama

# Step 2: Setting up custom model
#cd player-service-model && docker build -t a4a_model . && docker run -d -p 5001:5000 a4a_model && cd ..

# All time step
#mvn clean install -DskipTests
#mvn spring-boot:run