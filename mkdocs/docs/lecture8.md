# Lecture 8

## Was wir gemacht haben

- Option 2 für das Deployment gewählt: Deployment über einen eigenen Runner (Agent) auf dem Produktionsserver.
- Per SSH als `team3` mit dem Produktionsserver verbunden:
    - `ssh team3@10.0.40.173`
- Einen zweiten self-hosted GitHub Actions Runner (Linux) auf dem Produktionsserver installiert und mit dem GitHub-Repository verbunden.
- Dem neuen Runner das Label `deploy` gegeben.
- Dem bestehenden Runner auf dem Build Agent (`10.0.40.165`) zusätzlich das Label `build` gegeben.
- Geprüft, ob der Produktionsserver Images aus unserer Registry laden kann:
    - `docker pull 10.0.40.171:5000/devops-team3:latest`
- Den Build-Job so angepasst, dass er nur auf dem Build Agent läuft:
    - `runs-on: [self-hosted, build]`
- Einen zweiten Job `deploy` in derselben Pipeline erstellt, der nach dem Build-Job läuft:
    - `needs: build`
    - `runs-on: [self-hosted, deploy]`
- Festgelegt, dass Pull Requests nicht in Produktion deployed werden:
    - `if: github.event_name != 'pull_request'`
- Im Deploy-Job keinen Checkout verwendet, da die Images aus der Registry kommen.
- Die gerade gebauten Images aus der Registry geladen:
    - `$REGISTRY/$IMAGE_NAME:${{ github.sha }}`
    - `$REGISTRY/$DOCS_NAME:$DOCS_TAG`
- Die alten Container auf dem Produktionsserver gestoppt und entfernt:
    - `docker stop $CONTAINER_NAME $DOCS_NAME || true`
    - `docker rm $CONTAINER_NAME $DOCS_NAME || true`
- Die Anwendung und die Dokumentation auf den Ports von Team 3 gestartet:
    - `-p 1831:8080` (Anwendung)
    - `-p 1832:80` (Dokumentation)
    - `--restart always`
- Nur unsere eigenen alten Images gelöscht, nicht die der anderen Teams.
- Den Ablauf unter GitHub Actions überprüft und kontrolliert, auf welchem Runner welcher Job läuft.
- Die Container auf dem Produktionsserver überprüft:
    - `docker ps --filter name=devops-team3`
- Die Anwendung und die Dokumentation im Browser aufgerufen:
    - `http://10.0.40.173:1831/hello`
    - `http://10.0.40.173:1832`

## Was wir gelernt haben

- Wie man eine Anwendung über eine Pipeline auf einen eigenen Produktionsserver deployed.
- Dass man für das Deployment entweder SSH oder einen eigenen Runner auf dem Zielserver verwenden kann.
- Wie man mit Labels steuert, auf welchem Runner ein Job läuft.
- Dass bei mehreren Labels in `runs-on` der Runner alle Labels haben muss.
- Dass `self-hosted` ein Standard-Label ist, das alle eigenen Runner haben, und deshalb allein nicht ausreicht.
- Wie man mit `needs` Jobs nacheinander ausführt und dass `needs` nur innerhalb derselben Workflow-Datei funktioniert.
- Dass der Deploy-Job nur läuft, wenn der Build-Job erfolgreich war, und sonst die alte Version online bleibt.
- Dass die Variablen im `env`-Block für alle Jobs gelten.
- Warum der Commit-Hash als Tag sicherstellt, dass genau die gerade gebaute Version deployed wird.
- Wie bei `-p` der linke Port (Server) und der rechte Port (Container) zugeordnet werden.
- Dass auf einem gemeinsam genutzten Server jedes Team eigene Ports und eindeutige Containernamen braucht.
- Warum man auf einem geteilten Server keine Befehle wie `docker image prune -af` verwenden sollte.
