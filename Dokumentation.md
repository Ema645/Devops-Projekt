# DevOps-Projekt

# Lecture 1

## Was wir gemacht haben

- Projektteam festgelegt.
- SSH-Schlüsselpaare für die Teammitglieder erstellt.
- Liste der Teammitglieder und die öffentlichen SSH-Keys an Andreas Gottardi gesendet.
- Git-Repository erstellt und initialisiert.
- Teammitglieder als Collaborators zum GitHub-Repository hinzugefügt.
- Anwendung mit Spring Boot erstellt.
- Gradle als Build-System verwendet.
- Lokales Projekt mit dem GitHub-Repository verbunden.
- Anwendung committed und auf GitHub gepusht.

## Was wir gelernt haben

- Grundlegende Git-Befehle:
    - `git clone`
    - `git pull`
    - `git add`
    - `git commit`
    - `git push`
    - `git branch`
- Wie SSH-Keys erstellt werden.
- Wie ein Spring-Boot-Projekt erstellt wird.

# Lecture 1 Part 2

## Was wir gemacht haben

- Mit dem Remote-Server per SSH verbunden.
- Verbindung mit Standard- und eigenen SSH-Key-Dateien ausprobiert.
- Grundlegende Terminal-Befehle verwendet:
  - `pwd`
  - `mkdir`
  - `cd`
- Wichtige Git-Befehle nachgelesen:
  - `git clone`
  - `git pull`
  - `git add`
  - `git commit`
  - `git push`
  - `git fetch`
  - `git branch`
- Den Editor `vim` ausprobiert.

## Was wir gelernt haben

- Wie man sich per SSH mit einem Remote-Server verbindet.
- Wie man sich im Terminal bewegt und Ordner erstellt.
- Wie grundlegende Terminal-Befehle funktionieren.
- Wofür die wichtigsten Git-Befehle verwendet werden.
- `vim` ausprobiert.

# Lecture 2

## Was wir gemacht haben

- Mit dem Remote-Server per SSH verbunden.
- Einen self-hosted GitHub Actions Runner mit dem GitHub-Projekt verbunden.
- Den Runner gestartet und überprüft, ob er in GitHub als `Idle` angezeigt wird.
- Einen einfachen GitHub Actions Workflow erstellt.
- Die Pipeline so angepasst, dass sie auf dem eigenen Runner läuft:
  - `runs-on: self-hosted`
- Einen einfachen Befehl in der Pipeline ausgeführt:
  - `echo "Hello World"`
- Die Ausführung und Logs des Workflows unter GitHub Actions überprüft.
- Den Arbeitsordner des Runners kontrolliert:
  - `~/actions-runner/_work`
- Grundlegende Docker-Befehle kennengelernt.
- Das offizielle Docker-Image `hello-world` mit `docker run` ausgeführt.

## Was wir gelernt haben

- Wie man einen eigenen GitHub Actions Runner mit einem Repository verbindet.
- Wie man einen einfachen GitHub Actions Workflow mit einer `.yml`-Datei erstellt.
- Wie eine Pipeline auf einem self-hosted Runner ausgeführt wird.
- Wo man vergangene Workflow-Runs und Logs in GitHub findet.
- Wie man überprüft, ob ein Repository vom Runner verarbeitet wurde.
- Wie der grundlegende Docker-Befehl `docker run` funktioniert.

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
