Liste des exigences
====================
E1:Le fichier généré par le logiciel doit contenir la liste des arrondissements qui était dans le fichier d'entrée CSV avec le nombre
d'interventions policières survenues pour chaque cas et le nombre de parcs pour chaque arrondissement
E2:Lorsque trois arguments est rentré et que le premier parametre est "--english", cela affiche le logiciel et le 
fichier en anglais
E3: Si le nombre d'argument donné est erroné le programme s'arrête et affiche un message d'erreur
E4:Si le nom du fichier d'entreee est erronne
E5:Si l'option en anglais est sélectionnée, mais que le nom de fichier est erronée, il affiche le message en anglais
E6:Si le fichier d'entree en parametre par  l'utilisateur contient un arrondissement qui ne fait pas partie de la liste
le programme s'Arrete
E7:Si le fichier d'entree en parametre par  l'utilisateur contient une intervention policiere qui ne fait pas partie de la liste
le programme s'Arrete
E8:Si le fichier d'entree est vide

Plan de tests
==========
| N. | Fonctionnalité | Résultat attendu                                                                                                                                                                                                    | Description                     | Données                                     | Fichier à utiliser |                                                                                         
|:--:|:---------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:--------------------------------|:--------------------------------------------|--------------------|
| 1  | E1             | Un message de réussite et le fichier de sortie                                                                                                                                                                      | paramètre conforme              |                                             | e1.csv             |
| 2  | E2             | Les messages du logiciels sont en anglais et les entêtes le fichier de sortie en anglais                                                                                                                            | paramètre conforme              |                                             | e2.csv             |
| 3  | E3             | Un message d'erreur : "Fichier introuvable"                                                                                                                                                                         | arguments erronés               | Fichier manquant ou en trop                 | e3.csv             |
| 4  | E4             | Un message  d'erreur : " \n Erreur: Fichier introuvable -..."                                                                                                                                                       | Fichier d'entree erronee        |                                             |          |
| 5  | E5             | Un message  d'erreur :  "\n Error: File not found - " + e.getMessage() +" \n ~ \n the first parameter should be  --english the second " +" the path/to/your/inputfile and the 3rd the path/to/your/outputfile \n ~" | fichier entree erronee          |                                      | e5.csv             |
| 6  | E6             | Un message  d'erreur s'affiche et le programme se termine                                                                                                                                                           | Contenu fichier entree invalide | arrondissement invalide           | e6.csv             |       
| 7  | E7             | Un message  d'erreur s'affiche et le programme se termine                                                                                                                                                           | Contenu fichier entree invalide | description intervention policière invalide | e7.csv             |
| 8  | E8             | Un message  d'erreur s'affiche et le programme se termine                                                                                                                                                           | Contenu fichier entree vide     |  | e8.csv             |