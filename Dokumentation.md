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
