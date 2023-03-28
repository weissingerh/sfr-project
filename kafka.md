# How to start

Um die Applikation zu starten, muss der Ordner ‚Kafka-Docker‘ in einem Terminal geöffnet werden. 
Anschließend wird der Befehl ‚_docker-compose up -d_‘ ausgeführt. Damit wird Kafka in einem Container gestartet.

Anschließend müssen die zwei Java-Applikationen MusicApi und KafkaMusicProducer gestartet werden. 
Die Applikation KafkaMusicProducer hat einen minütlichen Timer, welcher neue Daten schreib und somit die Consumer damit füttert.

## Kafka-Architektur
| Kafka-Config    | Value |
|-----------------|-------|
| Brokers         | 2     |
| Partitions      | 3     |
| Replicas        | 3     |
| in.sync.replica | 2     |
