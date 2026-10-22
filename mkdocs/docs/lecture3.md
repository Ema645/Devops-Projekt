# Lecture 3

## Was wir gemacht haben

- Die bestehende GitHub Actions Pipeline um einen Build der Java-Anwendung erweitert.
- Den Build in einem Gradle-Docker-Container mit Java 17 ausgeführt:
  - `gradle:jdk17`
- Das Projektverzeichnis in den Container eingebunden und als Arbeitsverzeichnis festgelegt.
- Die Anwendung mit dem Gradle Wrapper gebaut:
  - `bash ./gradlew build`
- Die Unit Tests während des regulären Builds ausgeführt.
- Die Build-Ausgabe und Testergebnisse in den GitHub Actions Logs überprüft.
- Per SSH mit dem Build-Server verbunden und das ausgecheckte Projekt im Arbeitsordner des Runners gesucht:
  - `~/actions-runner/_work`
- Die erzeugten Dateien im Verzeichnis `build` untersucht:
  - JAR-Dateien unter `build/libs`
  - Testberichte unter `build/reports/tests`
- Geprüft, ob für die Anwendung eine ausführbare Fat JAR mit allen benötigten Abhängigkeiten erforderlich ist.
- Einen Schritt vor dem Checkout eingefügt, um die Besitzrechte des Arbeitsverzeichnisses zu korrigieren:
  - `chown -R $(id -u):$(id -g) /project`
- Den Aufbau eines Dockerfiles kennengelernt und die Verwendung der erzeugten JAR für ein Anwendungsimage besprochen.

## Was wir gelernt haben

- Wie man eine Java-Anwendung innerhalb eines Docker-Containers mit Gradle baut.
- Wie man den Gradle Wrapper und Aufgaben wie `tasks`, `clean` und `build` verwendet.
- Dass `clean` bisherige Build-Ergebnisse entfernt und `build` die Anwendung baut sowie normalerweise die Unit Tests ausführt.
- Wo Gradle die erzeugten Artefakte und Testberichte speichert.
- Dass Maven seine Build-Ergebnisse im Verzeichnis `target` ablegt.
- Was eine Fat JAR ist und warum sie die Ausführung einer Anwendung mit ihren Abhängigkeiten erleichtert.
- Wie man Projektdateien über ein Volume für einen Docker-Container verfügbar macht.
- Warum durch Container erzeugte Dateien Berechtigungsprobleme verursachen können und wie man Benutzer- und Gruppenrechte korrigiert.
- Warum die Build-Werkzeuge in Docker ausgeführt werden, statt SDKs direkt auf dem Runner zu installieren.
- Wie ein Dockerfile die Grundlage für ein Docker-Image der Java-Anwendung bildet.
