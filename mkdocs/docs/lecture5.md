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
