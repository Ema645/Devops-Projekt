# Lecture 6

## Was wir gemacht haben

- MkDocs und Markdown kennengelernt.
- Ein MkDocs-Projekt im Repository angelegt:
    - `mkdocs/mkdocs.yml`
    - `mkdocs/docs/`
- Unsere bisherigen Notizen als Markdown-Dateien in den Ordner `docs` gelegt:
    - `lecture1.md` bis `lecture5.md`
- Eine Startseite `index.md` erstellt.
- In `mkdocs.yml` den Seitennamen, das Theme `material` und die Navigation festgelegt.
- Einen Hell- und Dunkelmodus sowie Deutsch als Sprache eingestellt.
- Ein `Dockerfile` für die Dokumentation erstellt, das auf nginx basiert:
    - `FROM nginx:1.27-alpine`
    - `RUN rm -rf /usr/share/nginx/html/*`
    - `COPY site/ /usr/share/nginx/html/`
    - `EXPOSE 80`
- Variablen für die Dokumentation im `env`-Block der Pipeline definiert:
    - `DOCS_NAME: devops-team3-docs`
    - `DOCS_TAG: 0.0.${{ github.run_number }}`
- Einen Pipeline-Schritt eingebaut, der die HTML-Seite mit dem Image `squidfunk/mkdocs-material` erzeugt:
    - `squidfunk/mkdocs-material build`
- Das Docs-Image aus dem Ordner `mkdocs` gebaut:
    - `docker build -t $DOCS_NAME:$DOCS_TAG mkdocs`
- Den alten Docs-Container gestoppt und entfernt:
    - `docker stop $DOCS_NAME || true`
    - `docker rm $DOCS_NAME || true`
- Den neuen Docs-Container auf einem anderen Port als die Anwendung gestartet:
    - `-p 8081:80`
    - `--restart unless-stopped`
- Die Dokumentation im Browser über die IP des Build Agents aufgerufen:
    - `http://10.0.40.165:8081`
- Das Docs-Image getaggt und in die Registry gepusht:
    - `10.0.40.171:5000/devops-team3-docs:<tag>`

## Was wir gelernt haben

- Was MkDocs ist und wie man aus Markdown-Dateien eine HTML-Dokumentation erzeugt.
- Wie ein MkDocs-Projekt aufgebaut ist (`mkdocs.yml` und Ordner `docs`).
- Wie man in `mkdocs.yml` Theme, Sprache und Navigation konfiguriert.
- Dass MkDocs die fertige Seite im Ordner `site` ablegt.
- Dass nginx ein einfacher Webserver ist, der alles ausliefert, was im Image liegt.
- Dass der Ablauf dem Build der Anwendung ähnelt:
    - Gradle-Container erzeugt eine JAR, das Anwendungsimage basiert auf einem JRE-Image.
    - mkdocs-material-Container erzeugt den Ordner `site`, das Docs-Image basiert auf nginx.
- Dass zwei Container auf derselben VM verschiedene Ports brauchen.
- Dass ohne `index.md` keine `index.html` erzeugt wird und nginx dann seine eigene Standardseite zeigt.
- Dass man den Standardinhalt von nginx vor dem Kopieren löschen sollte.
- Wie man mit `docker exec` prüft, welche Dateien tatsächlich im Container liegen.