Réaliser par :

Yasmine Alimatou Cisse(CISA65370402)
Nadia Moussa Idrissa (MOUN19599705)
Saskia Dessapes(DESS93570009)

Remis à Jacques Berger

Cours : INF2050 - Outils et pratques de développement logiciel - groupe 030

Sprint 1 

Ce programme permet d'extraire dans un fichier CSV l'arrondissement 
et le nombre d'intervention policière. Les données extraites sont 
mis dans un fichier de sorti CSV.

lien vers le dépôt central:  https://gitlab.info.uqam.ca/inf2050_saskia_nadia_yasmine/sprint1.git 

Comment démarer le projet à la ligne de commande
=================================================

1. Ouvrir un terminal et aller a la racine du projet
2. pour construire le projet écrire la commande mvn clean package, un fichier jar qui va correspondre au projet 
va etre cree et ajoute dans le fichier dans le repertoir target
3. Ecrire la commande "java -jar <le nom du fichier compilé jar.jar." pour pouvoir executer le projet


Comment installer les dépendances
=================================
1. Dans le pom.xml, ajouter a la structure du projet  <dependencies></dependencies>, dans lequel nous 
pouvons y ajouter des dépendences
2. Aller sur le site https://mvnrepository.com/search?q=mvn et rechercher la dépendence/ librairie voulue (par ex: taper junit
dans la bar de rechercher)
3. Sélectionner la version la plus récente et compatible et copier coller la dépendence pour maven  à l'intérieur
crochets <dependencies></dependencies>
exemple pour maven: <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
   <dependency>
   <groupId>org.junit.jupiter</groupId>
   <artifactId>junit-jupiter-api</artifactId>
   <version>5.10.1</version>
   <scope>test</scope>
   </dependency>
4. Pour mettre à jour le logiciel, faire clic-droit sur le fichier pom.xml, placer votre curseur sur maven, et
ensuite sélectionner "reload project".