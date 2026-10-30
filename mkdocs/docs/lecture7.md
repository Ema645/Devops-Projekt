# Lecture 7

## Was wir gemacht haben

- Pandoc kennengelernt, um aus Markdown-Dateien ein PDF zu erzeugen.
- Einen Pipeline-Schritt eingebaut, der das PDF mit dem Docker-Image `pandoc/latex` erzeugt:
    - `pandoc/latex`
- Alle Markdown-Dateien der Dokumentation zu einem gemeinsamen PDF zusammengefügt:
    - `index.md`
    - `lecture1.md` bis `lecture6.md`
- Ein Inhaltsverzeichnis und Deutsch als Sprache für das PDF festgelegt:
    - `--toc`
    - `-V lang=de`
- Das PDF im Ordner `docs` abgelegt:
    - `mkdocs/docs/documentation.pdf`
- Den Schritt direkt nach dem Checkout eingefügt, also vor dem Build der Anwendung und vor dem Docs-Image.
- Das PDF in einem eigenen Pipeline-Schritt in die Anwendung kopiert:
    - `mkdir -p src/main/resources/static`
    - `cp mkdocs/docs/documentation.pdf src/main/resources/static/`
- Beim Gradle-Build wird das PDF mit in die JAR gepackt.
- Beim MkDocs-Build wird das PDF automatisch in den Ordner `site` übernommen und damit auch ins nginx-Image.
- Das PDF im Browser über beide Container aufgerufen:
    - Dokumentation: `http://10.0.40.165:8081/documentation.pdf`
    - Anwendung: `http://10.0.40.165:8080/documentation.pdf`

## Was wir gelernt haben

- Was Pandoc ist und wie man damit Markdown in andere Formate wie PDF umwandelt.
- Dass Pandoc für PDFs LaTeX braucht und deshalb das Image `pandoc/latex` verwendet wird.
- Dass Pandoc mehrere Markdown-Dateien in der angegebenen Reihenfolge zu einem PDF zusammenfügt.
- Dass neue Lectures im Pandoc-Schritt ergänzt werden müssen, sonst fehlen sie im PDF.
- Warum die Reihenfolge in der Pipeline wichtig ist: Das PDF muss vor dem Gradle-Build existieren, sonst ist es nicht in der JAR.
- Dass `mkdocs build` den Ordner `site` vor jedem Build leert und ein PDF dort deshalb wieder gelöscht würde.
- Dass MkDocs alle Dateien aus dem Ordner `docs` nach `site` kopiert, also auch das PDF.
- Dass Spring Boot Dateien aus `src/main/resources/static` automatisch als statische Inhalte ausliefert.
- Dass erzeugte Dateien nur auf dem Runner entstehen und nicht ins Repository committed werden müssen.
