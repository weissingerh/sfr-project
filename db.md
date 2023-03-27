# Database
Wir haben uns für die Datenbank MongoDB (Cloud-Lösung) entschieden. Wir haben uns dafür entschieden, weil MongoDB die 
Daten Repliciert. Die Daten werden auf einem Primary und mehrere Secondaries, auf die zurückgegriffen werden kann, wenn der ursprüngliche 
Primary ausfällt, gesichert. Dadurch steigt die Ausfallssicherheit der Datenbank. Dies ist für unsere Applikation von großer Bedeutung,
da wir sicherstellen wollen, dass die Daten zu jeder Zeit erreichbar sind. 

![](/Users/aidaomic/Desktop/Bildschirm­foto 2023-03-27 um 22.15.10.png)

Andere Optionen wie Database per Application kamen für uns nicht in Frage, da unser Projekt nur aus einer Applikation besteht, 
welche auf die Datenbank zugreift. Daher bietet uns diese Variante per se keine Vorteile.

## Tables
### Tracks und Artists
Wir haben uns dazu entschieden, den in der vorherigen Übung aggregierten Stream herzunehmen und die Daten in der DB abzuspeichern.

`Track: id, title, artist(id), average(average times listened per person)`

![](/Users/aidaomic/Desktop/Bildschirm­foto 2023-03-27 um 22.22.06.png)

`Artist: id, name`

![](/Users/aidaomic/Desktop/Bildschirm­foto 2023-03-27 um 22.23.03.png)

### Top Tracks
Neben den Tracks und Artists speichern wir auch die Top Tracks in einer eigenen Tabelle. Dabei verlinken wir hier auf die Tabelle Track.

`TopTrack: id, place, date, track(id)`

![](/Users/aidaomic/Desktop/Bildschirm­foto 2023-03-27 um 22.25.24.png)


