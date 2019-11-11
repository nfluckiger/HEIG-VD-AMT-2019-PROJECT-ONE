# Load Test

Nous n'avons pas eu d'erreur avec notre test de charge. Cela nous permet de dire que notre application n'est pas inquiétée par une charge de 4500 demande en deux minutes. Ce qui pour notre model d'utilisation de l'application est tout a fait suffisant.

![loadtesting](.\src\loadtesting.PNG)

Le temps des requêtes peut par contre nous porter préjudice. Dans le cas d'une mise en service il serait bien de mettre ne place un système redondant et de gestionnaire de charge. Dans le but de rester dans des délais raisonnables.

Il nous aurait fallut aussi faire un test de lecture de page avec et sans la pagination pour voir  si un gains de vitesse subséquent était pris de la pagination. Mais nous n'avons pas eu le temps. 