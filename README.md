# Bon appétit backend

1/ Utiliser le script (fichier bdd.txt) pour créer la base de donnée  
2/ Cloner le repo  
3/ Ouvrir le répertoire "api-bonap" avec IntelliJ  
4/ Dans le projet vérifier le fichier src/main/ressources/application.properties (url + port pour accéder à la bdd, user + mdp pour y accéder, etc)  
    => normalement, rien à modifier à ce stade pour travailler en local  
5/ Lancer l'application via src/main/java/ApiApplication (logo bleu avec une petite flêche verte)  
6/ Dans la console tout doit fonctionner et l'application doit être à l'écoute sur le port localhost:8080  

Toutes les entités (models) ont été cré grâce à JPA, donc automatiquement.  
Manuellement j'ai créé les repositories, services et controllers pour "Utilisateur" et "Restaurant". Il faudra créer les autres en fonction des besoins.  

Dans les controlleurs vous trouverez les endpoints (api/utilisateurs et api/restaurants, etc)  
=> pour vérifier que tout fontionne bien, compléter votre bdd avec 1 utilisateur ou 1 restaurant  
=> puis taper dans votre navigateur localhost:8080/api/utilisateurs  
=> un Json doit vous être retourné dans le navigateur  

NB : J'ai dû supprimer les clés composites, donc la structure de la bdd est la même avec des modifications mineures pour que ça tourne. Assurez-vous donc bien d'utiliser le script (fichier txt) pour partir sur une bdd neuve.  
