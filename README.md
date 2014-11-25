-----------
![Université Lille 1](http://www.univ-lille1.fr/digitalAssets/38/38040_logo-trans.png)
#COO_Project 
###Project of *Conception Orienté Objet* for **M1 MIAGE** 2014-2015
-----------
TODO
#####Remarque : Ne pas hésiter à créer au fil de l'eau des classes d'exception nécessaire => Servira dans Swing 
-----------
* **Couche Données Objet**
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

* **Factory**
 * Création des factory
    * FactoryClient : KO
    * FactoryForfait : KO
    * FactoryTarif : KO
    * FactoryReservation : KO
    * FactoryTrancheHoraire : KO
    * FactorySalle : KO

-----------
* **Metier**
   *  Création des métiers de base
      * CreationClient : KO // Penser à associer une carte de fidelité à la création du client
      * AjoutForfait : KO
      * CreationSalle : KO
      * CreationTrancheHoraire : KO
   * Création des métiers élaborés
      * CalculerTarif : KO
      * EffectuerReservation : KO
         * reservationAuto : KO
         * reservationManu : KO
         * reservationMulti : KO
      * VisualiserReservation : KO
      * EditionClient : KO
         * confirmerReservation : KO
         * vendreForfait : KO
      * AnnulerReservation : KO

-----------

* **Partie Base de données**
   * Création de la base
      * Elaborer MCD : KO
      * Création de la base : KO
   * Création des méthodes JDBC
      * FactoryClient : KO
      * FactoryForfait : KO
      * FactoryTarif : KO
      * FactoryReservation : KO
      * FactoryTrancheHoraire : KO
      * FactorySalle : KO
   * Tests du code : KO

-----------

* **Presentation**
   * Conception de l'interface
      * Version papier : KO
   * Programmation de l'inferface
      * Ecran Menu : KO
      * Ecran visualtion des réservations : KO
      * Ecran de reservation : KO
      * Ecran de création client : KO
      * Ecran d'édition client : KO

-----------
