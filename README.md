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
    * FactoryReservation : **KO**
    * FactoryPlageHoraire : **KO**
    * FactorySalle : **OK**
 * Codage des Factory
    * FactoryClient : **OK**
    * FactoryForfait : In progress
    * FactoryReservation : In progress
    * FactoryPlageHoraire : In progress
    * FactorySalle : In progress

-----------
* **Metier**
   *  Création des métiers de base
      * CreationClient : KO
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
