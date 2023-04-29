# SSR

### Wieso ist BTR nicht gut für unseren Use Case?

Die Interkativität ist bei BTR nur limitiert möglich. Zudem werden die Seiten bei Build Time gerendert, 
wodruch Updates durch unsere Kafka Streams nicht beachtet werden. Daher wird für unseren Use Case ssr (oder csr) benötigt.
Denn wenn sich die Daten durch unsere Kafka Streams ändern, soll dies auch im Frontend berücksichtigt werden.