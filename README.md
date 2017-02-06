# VirtualCoach
Web application for Fantasy Soccer Players (Italy)

##PREREQUISITI:

* Ubuntu 16.04 LTS
* JRE 1.8.X
* Python 2.7.X
* BeautifulSoup 4.0 o superiore 
* MongoDB 3.2.0 o superiore
* Apache Tomcat 8.0.X 
* Eclipse IDE for Java EE Developers (Neon package)
* Git

NB: assicurarsi di impostare il corretto percorso nel file script.sh prima di eseguirlo

##IMPORTARE IL PROGETTO

clonare la repository tramite la linea di comando git:

git clone https://github.com/biagiocaldararo/VirtualCoach.git

Per clonare il progetto nella directory corrente. A questo punto per importare il progetto in Eclipse basterà fare Import -> Existing Maven Projects e selezionare la directory del progetto appena clonato.

##ESEGUIRE IL PROGETTO

### SU ECLIPSE

Dopo aver importato il progetto per eseguirlo tasto destro sul progetto: 
* Run As -> Maven Install 
* Run As -> Run on Server (selezionando Tomcat)

### SU BROWSER

In alternativa si può eseguire il progetto direttamente su browser:
* Tasto destro sul Server -> Add and Remove.. selezionando il progetto importato
* Start del server
* Sul browser incollare il link http://localhost:8080/VirtualCoach/




