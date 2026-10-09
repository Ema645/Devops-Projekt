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



# Lecture 5

## Was wir gemacht haben

- Einen eindeutigen Namen für das Docker-Image festgelegt, statt eines generischen Namens:
  - `devops-team3`
- Image- und Containernamen, Registry, SonarQube-URL und Project Key als Variablen im `env`-Block der Pipeline definiert.
- Das Image zusätzlich mit dem Commit-Hash getaggt, damit jeder Build in der Registry eindeutig ist:
  - `${{ github.sha }}`
- Per SSH als `student` mit dem Build Agent verbunden.
- SonarQube Community als Docker-Container auf dem Build Agent gestartet:
  - `docker run -d --name sonarqube --restart always -p 9000:9000 sonarqube:community`
- Volumes für Daten, Erweiterungen und Logs eingebunden, damit die Einstellungen bei einem Neustart erhalten bleiben.
- SonarQube im Browser aufgerufen und das Standardpasswort von `admin` geändert:
  - `http://10.0.40.165:9000`
- Ein lokales Projekt in SonarQube angelegt und einen Projekt-Token ohne Ablaufdatum erzeugt.
- Den Token als GitHub Secret `SONAR_TOKEN` gespeichert.
- Das JaCoCo-Plugin in `build.gradle` eingebunden:
  - `id 'jacoco'`
- Den Test-Task so konfiguriert, dass danach automatisch der Coverage-Report erzeugt wird:
  - `finalizedBy tasks.named('jacocoTestReport')`
- Den XML-Report aktiviert, damit SonarQube die Coverage auslesen kann:
  - `xml.required = true`
- Die neuen Gradle-Tasks und den erzeugten Report lokal überprüft:
  - `build/reports/jacoco/test/jacocoTestReport.xml`
  - `build/reports/jacoco/test/html/index.html`
- Einen Pipeline-Schritt für die Code-Analyse nach dem Gradle-Build eingebaut.
- Die Analyse mit dem Docker-Image `sonarsource/sonar-scanner-cli` ausgeführt.
- Server-URL und Token über Umgebungsvariablen an den Scanner übergeben:
  - `SONAR_HOST_URL`
  - `SONAR_TOKEN`
- Die Scanner-Einstellungen als Parameter übergeben:
  - `sonar.projectKey`
  - `sonar.sources`
  - `sonar.tests`
  - `sonar.java.binaries`
  - `sonar.coverage.jacoco.xmlReportPaths`
- Den Scanner mit der gleichen User-ID wie den Gradle-Build ausgeführt:
  - `-u 1000`
- Die Ergebnisse in SonarQube überprüft (0 Security-Probleme, 0 Bugs, 1 Code Smell, 40 % Coverage).
- Geprüft, ob das neue Image in der Registry vorhanden ist:
  - `http://10.0.40.171:5000/v2/_catalog`

## Was wir gelernt haben

- Warum Images in einer gemeinsamen Registry eindeutige Namen brauchen.
- Wie Variablen im `env`-Block die Pipeline übersichtlicher machen.
- Wie man SonarQube als Docker-Container auf einem Server betreibt.
- Dass `--restart always` den Container nach einem Neustart der VM automatisch wieder startet.
- Was SonarQube analysiert: Security, Reliability, Maintainability, Security Hotspots, Coverage und Duplikate.
- Was JaCoCo ist und wie es die Testabdeckung des Codes misst.
- Dass SonarQube die Coverage nicht selbst berechnet, sondern den JaCoCo-Report einliest.
- Warum der Scanner bei Java-Projekten die kompilierten Klassen braucht.
- Dass Tokens nicht in den Code gehören, sondern als GitHub Secret gespeichert werden.
- Dass innerhalb eines Containers `localhost` auf den Container selbst zeigt und deshalb die echte IP des Servers verwendet werden muss.
- Dass Scanner-Einstellungen entweder als `-D`-Parameter oder in einer Datei `sonar-project.properties` angegeben werden können.
- Wie unterschiedliche User-IDs in Containern zu Berechtigungsproblemen führen können.