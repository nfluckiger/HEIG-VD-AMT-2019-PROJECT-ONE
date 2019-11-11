HEIG-VD-AMT-2019-PROJECT-ONE

### Useful links

1.  [Setup and use](#setup-and-use) 

2. [Implementation](documentation/implementation.md)

3. [Test strategy](documentation/test_strategy.md)

   1.  [Load Experiment](documentation/load_test_report.md)

4.  [Known Issue](documentation/known_error.md)

    

### Explanation of the project

Nous avons choisi d'implémenter un système de gestion d'arbitrage pour des matchs de football américain. Un match est composé de deux équipes (une équipe `home` ainsi qu'une équipe `away`) ainsi que 7 arbitres ayant des postes et des niveaux différents. Dans notre application, les utilisateurs sont donc les arbitres.

### Setup and Use

Nous avons développer notre application sur le serveur WildFly. En raison de problème de déploiement (et en accord avec un post vu sur le site stack overflow), nous n'utilisons pas la dernière version de ce serveur is nous utilisons la version `17.0.1`. 

#### Lancer l'application en local

Dans cette partie, nous partons du principe que la configuration de WildFly (ainsi que la création de la `datasource`) est déjà faite et ne sera donc pas expliquée. Notre fichier asdasdasd `standalone.xml` ainsi que le fichier `jar` pour la connection `jdbc` sont toutefois disponible dans le dossier `component/wildfly/src`.

Ensuite, en utilisant l'application `IntelliJ Ultimate Edition`, nous pouvons créer une nouvelle configuration de la manière suivante :

![Configuration](./documentation/src/configuration.png)

Il faut toutefois noter que, dans l'onglet `Deployment`, nous devons sélectionner l'artefact à déployer dans grâce au bouton `+`. L'artifact à déployer est celui se terminant par `exploded`.

Une fois la configuration terminée, nous pouvons ouvrir un terminal à dans le dossier `docker-comp` et exécuter la commande `docker-compose up phpmyadmin` afin de démarrer le docker `MySQL` et `PHPMyAdmin`. 

Ensuite, en exécutant le programme sous `IntelliJ` avec le bouton *play*.

#### Lancer l'application en remote

Malheureusement, nous n'avons pas réussi à lancer notre application. Nous n'arrivons pas à déployer correctement notre application sur un serveur WildFly contenu dans un docker. Nous avons accés à notre serveur WildFly mais pas à notre application...