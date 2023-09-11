# formation-clean-code

Pour générer les archives `workspaces-student.zip` et `workspaces-trainer.zip`, lancer la commande suivante :

```
mvn package -Dmaven.test.skip=true
```

Il est nécessaire d'ignorer les tests pour générer les archives : de nombreux tests échouent dans les projets `*-start`
pour que les personnes qui participent à la formation prennent conscience de certains problèmes.
