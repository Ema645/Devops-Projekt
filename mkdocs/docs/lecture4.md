# Lecture 4

## Was wir gemacht haben

- Die GitHub Actions Pipeline um Deployment, Integrationstest und Registry-Push erweitert.
- Die Anwendung in einem Gradle-Docker-Container gebaut und dabei die Unit Tests ausgeführt:
  - `gradle:8-jdk17-alpine`
  - `gradle build`
- Das Docker-Image der Anwendung erstellt:
  - `docker build -t devops-projekt:latest .`
- Den bisherigen Container gestoppt und entfernt:
  - `docker stop devops-projekt || true`
  - `docker rm devops-projekt || true`
- Den neuen Container im Hintergrund gestartet:
  - `docker run -d`
- Einen festen Containernamen vergeben:
  - `--name devops-projekt`
- Den automatischen Neustart des Containers konfiguriert:
  - `--restart unless-stopped`
- Den Port der Anwendung auf der Team-VM verfügbar gemacht:
  - `-p 8080:8080`
- Einen Integrationstest als eigenen Pipeline-Schritt eingebaut.
- Vor dem Test fünf Sekunden auf den Start der Anwendung gewartet:
  - `sleep 5`
- Den Endpunkt der laufenden Anwendung aufgerufen:
  - `curl -f http://localhost:8080/hello`
- Bei erfolgreichem Aufruf den Test mit `exit 0` abgeschlossen und bei einem Fehler mit `exit 1` abgebrochen.
- Nach erfolgreichem Integrationstest das Docker-Image für die Registry getaggt:
  - `10.0.40.171:5000/devops-projekt:latest`
- Das getestete Image mit `docker push` in die Registry hochgeladen.

## Was wir gelernt haben

- Wie man eine Anwendung über eine Pipeline auf der Team-VM als Docker-Container bereitstellt.
- Wie Stop, Cleanup und Start eines Containers automatisiert werden.
- Warum ein fester Containername das Stoppen und Entfernen vereinfacht.
- Wie `|| true` verhindert, dass ein fehlender oder bereits gestoppter Container die Pipeline abbricht.
- Wie eine Restart-Policy den automatischen Neustart nach einem VM-Neustart ermöglicht, sofern der Container nicht bewusst gestoppt wurde.
- Wie Ports zwischen der VM und dem Container zugeordnet werden.
- Dass Integrationstests gegen die laufende Anwendung ausgeführt werden und einen eigenen Pipeline-Schritt benötigen.
- Wie man mit `curl -f` einen Endpunkt abfragt und HTTP-Fehler ab Status 400 als Fehler behandelt.
- Wie Rückgabecodes den Erfolg oder Fehler eines Pipeline-Schritts bestimmen.
- Dass der aktuelle Test einen erfolgreichen HTTP-Aufruf prüft, aber den Antwortinhalt noch nicht mit erwarteten Daten vergleicht.
- Dass eine feste Wartezeit von fünf Sekunden nicht garantiert, dass die Anwendung bereits bereit ist.
- Wie man ein Docker-Image für eine Registry taggt und anschließend pusht.
- Dass die nachfolgenden Tag- und Push-Schritte bei einem fehlgeschlagenen Integrationstest standardmäßig nicht ausgeführt werden.
