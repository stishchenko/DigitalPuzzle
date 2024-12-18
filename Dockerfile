FROM openjdk:8
WORKDIR /app
COPY . .
RUN javac DigitalPuzzle.java
CMD ["java", "DigitalPuzzle"]