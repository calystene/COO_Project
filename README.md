-----------
![Université Lille 1](http://www.univ-lille1.fr/digitalAssets/38/38040_logo-trans.png)
#COO_Project 
###Projet de *Conception Orienté Objet* - **M1 MIAGE** 2014-2015
-----------
TODO
#####Remarque : Ne pas hésiter à créer au fil de l'eau des classes d'exception nécessaire => Servira dans Swing 
-----------
* **Couche Données Objet**
 * Codage des méthodes de base
    * CarteFidelite : **OK**
    * Client : **OK**
    * Forfait & ses fils : **OK**
    * Reservation : **OK**
    * CalendrierReservation : **OK**
    * SallePetite : **OK**
    * SalleGrande : **OK**
    * SalleSpecifique : **OK**
    * PlageHoraire : **OK**
    * DateManager : **OK**

-----------

* **Factory**
 * Création des factory
    * FactoryClient : **OK**
    * FactoryForfait : **OK**
    * FactoryReservation : **OK**
    * FactoryPlageHoraire : **OK**
    * FactorySalle : **OK**
    * FactoryReserPlageH : **OK**
 * Codage des Factory
    * FactoryClient : **OK** 
    * FactoryForfait : **OK**
    * FactoryReservation : **OK**
    * FactorySalle : **OK**
    * FactorySQL : **OK**

-----------
* **Metier**
   *  Création des métiers de base
      * CreerClient : **OK**
      * CreerForfait : KO
      * CreerSalle : KO
   * Création des métiers élaborés
      * CalculerTarif : **OK**
      * EffectuerReservation : In progressThomas
         * reservationAuto : **OK**
         * reservationManu : In Progress Thomas
         * reservationMulti : KO
      * VisualiserReservation : **OK**
      * EditionClient : In progress Manon
         * confirmerReservation : In progress Manon
         * vendreForfait : In progress Manon
      * AnnulerReservation : KO

-----------

* **Partie Base de données**
   * Création de la base
      * Elaborer MCD : **OK**
      * Création de la base : **OK**
   * Création des méthodes JDBC
      * FactoryClient : **OK**
      * FactoryForfait : **OK**
      * FactoryReservation : **OK**
      * FactorySalle : **OK**

-----------

* **Presentation**
   * Conception de l'interface
      * Version papier : **OK**
   * Programmation de l'inferface
      * Ecran Menu : **OK**
      * Ecran rechercher client : **OK**
      * Ecran afficher infos client : **OK**
      * Ecran visualtion des réservations : **OK**
      * Ecran de reservation : **OK**
      * Ecran de création client : **OK**
      * Ecran d'édition client : In progress Manon
      * Ecran pour effectuer une réservation : In progress Thomas

-----------
