# Backend
Beim Backend haben wir uns für die Layered Architecture entschieden. Dieses Backend passt am Besten zu unserem Microservice.
Das Service wird recht klein gehalten, da es sich hierbei um eine Übung und kein echtes System handelt. Die Layered Architecture 
ist für diesen Zweck perfekt. Somit wird die Applikation nicht unnötig aufgeblasen und trotzdem übersichtlich strukturiert.

![](/Users/aidaomic/Desktop/Bildschirm­foto 2023-03-27 um 22.44.37.png)

# JIT or AOT
Java Projekte verwenden default-mäßig JIT Compiler. Dies reicht für die Entwicklung dieses Projektes vollkommen. Da das Microservice
nie wirklich in Produktion (Deployment) kommen wird, reicht dies vollkommen aus. Würde ein Deployment am Ende des 
Entwicklungsprozesses stattfinden, wäre der AOT Compiler interessanter.
JIT bietet den Vorteil, dass es plattformspeziefisch ist und sich an unsere Entwicklungsumgebung anpasst.