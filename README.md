-----------
![Université Lille 1](http://www.univ-lille1.fr/digitalAssets/38/38040_logo-trans.png)
#COO_Project 
###Project of *Conception Orienté Objet* for **M1 MIAGE** 2014-2015
-----------
TODO
#####Remarque : Ne pas hésiter à créer au fil de l'eau des classes d'exception nécessaire => Servira dans Swing 
-----------
1. Couche Données Objet
  * Codage des méthodes de base
   * CarteFidelite : **OK**
   * Client : In Progress
   * Forfait & ses fils : KO
   * Reservation : KO
   * CalendrierReservation : KO
   * Tarif : KO
   * SallePetite : In Progress
   * SalleGrande : In Progress
   * SalleSpecifique : In Progress
   * TrancheH_Matin : KO
   * TrancheH_AM : KO
   * TrancheH_Soir : KO
-----------
2. Factory
 * Création des factory
  * FactoryClient : KO
  * FactoryForfait : KO
  * FactoryTarif : KO
 * FactoryReservation : KO
 * FactoryTrancheHoraire : KO
 * FactorySalle : KO
-----------
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
-----------
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
-----------
5. Presentation
5.1 Conception de l'interface
5.1.1 Version papier : KO
5.2 Programmation de l'inferface
5.2.1 Ecran Menu : KO
5.2.2 Ecran visualtion des réservations : KO
5.2.3 Ecran de reservation : KO
5.2.4 Ecran de création client : KO
5.2.5 Ecran d'édition client : KO
