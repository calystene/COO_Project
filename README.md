-----------
![Université Lille 1](http://www.univ-lille1.fr/digitalAssets/38/38040_logo-trans.png)
#COO_Project 
Project of *Conception Orienté Objet* for **M1 MIAGE** 2014-2015
-----------

TODO
*Remarque : Ne pas hésiter à créer des classes d'exception nécessaire au fil de l'eau => Servira dans Swing*

1. Couche Données Objet
1.1 Codage des méthodes de base
1.1.1 CarteFidelite : **OK**
1.1.2 Client : In Progress
1.1.3 Forfait & ses fils : KO
1.1.4 Reservation : KO
1.1.5 CalendrierReservation : KO
1.1.6 Tarif : KO
1.1.7 SallePetite : In Progress
1.1.8 SalleGrande : In Progress
1.1.9 SalleSpecifique : In Progress
1.1.10 TrancheH_Matin : KO
1.1.11 TrancheH_AM : KO
1.1.12 TrancheH_Soir : KO

2. Factory
2.1 Création des factory
2.1.1 FactoryClient : KO
2.1.2 FactoryForfait : KO
2.1.3 FactoryTarif : KO
2.1.4 FactoryReservation : KO
2.1.5 FactoryTrancheHoraire : KO
2.1.6 FactorySalle : KO

3. Metier
3.1 Création des métiers de base
3.1.1 CreationClient : KO // Penser à associer une carte de fidelité à la création du client
3.1.2 AjoutForfait : KO
3.1.3 CreationSalle : KO
3.1.4 CreationTrancheHoraire : KO
3.2 Création des métiers élaborés
3.2.1 CalculerTarif : KO
3.2.2 EffectuerReservation : KO
3.2.2.1 reservationAuto : KO
3.2.2.2 reservationManu : KO
3.2.2.3 reservationMulti : KO
3.2.3 VisualiserReservation : KO
3.2.4 EditionClient : KO
3.2.4.1 confirmerReservation : KO
3.2.4.2 vendreForfait : KO
3.2.6 AnnulerReservation : KO

4. Partie Base de données
4.1 Création de la base
4.1.1 Elaborer MCD : KO
4.1.2 Création de la base : KO
4.2 Création des méthodes JDBC
4.2.1 FactoryClient : KO
4.2.2 FactoryForfait : KO
4.2.3 FactoryTarif : KO
4.2.4 FactoryReservation : KO
4.2.5 FactoryTrancheHoraire : KO
4.2.6 FactorySalle : KO
4.3 Tests du code : KO

5. Presentation
5.1 Conception de l'interface
5.1.1 Version papier : KO
5.2 Programmation de l'inferface
5.2.1 Ecran Menu : KO
5.2.2 Ecran visualtion des réservations : KO
5.2.3 Ecran de reservation : KO
5.2.4 Ecran de création client : KO
5.2.5 Ecran d'édition client : KO