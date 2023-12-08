Liste des exigences
====================
E1:Le fichier généré par le logiciel doit contenir la liste des arrondissements qui était dans le fichier d'entrée CSV avec le nombre
d'interventions policières survenues pour chaque cas et le nombre de parcs pour chaque arrondissement
E2:Lorsque trois arguments est rentré et que le premier parametre est "--english", cela affiche le logiciel et le 
fichier en anglais
E3: Si le nombre d'argument donné est erroné le programme s'arrête et affiche une message
E4:Si le fichier d'entreee est erronne

Plan de tests
==========
| N. | Fonctionnalité | Résultat attendu                                                             | Description              | Données                     |                                                                                          
|:--:|:---------------|:-----------------------------------------------------------------------------|:-------------------------|:----------------------------|
| 1  | E1             | Un message de réussite et le fichier de sortie                               | paramètre conforme       |                             |
| 2  | E2             | Les messages du logiciels sont en anglais et le fichier de sortie en anglais | paramètre conforme       |                             |
| 3  | E3             | Un message d'erreur                                                          | arguments erronés        | Fichier manquant ou en trop |
| 4  | E4             | Un message  d'erreur                                                         | Fichier d'entree erronee |                             |