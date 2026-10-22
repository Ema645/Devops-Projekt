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
