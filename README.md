# Le Donjon du Dragon Écarlate 🐉⚔️

Application de type « Livre dont vous êtes le héros » développée en Java dans le cadre d'un projet académique à l'ISEN Ouest.

Le joueur explore un donjon oublié et progresse dans l'aventure à travers une succession de choix. Chaque décision influence le déroulement du récit jusqu'à l'une des fins possibles, victorieuses ou non.

---

## Stack technique

* **Langage :** Java
* **Interface graphique :** Java Swing
* **Base de données :** SQLite
* **Accès aux données :** JDBC
* **Patrons de conception :**
  * **Singleton** (`GameManager`)
  * **Observer** (`GameListener`)

---

## Organisation des packages

* **`bdd` :** Gestion des accès à la base SQLite via `DatabaseManager`.
* **`graphics` :** Interface graphique Swing et gestion des composants visuels.
* **`logics` :** Contient toute la logique métier du jeu (paragraphes, choix, fins, gestion de la partie).
* **`observer` :** Implémentation du pattern Observer pour synchroniser le moteur du jeu et l'interface graphique.

---

## 📁 Structure du projet

```text
src/
├── bdd/
│   └── DatabaseManager.java
├── graphics/
│   ├── GameWindow.java
│   └── buttons/
│       └── ChoiceButtonListener.java
├── logics/
│   ├── Choix.java
│   ├── GameManager.java
│   ├── Paragraphe.java
│   └── TypeFin.java
├── observer/
│   └── GameListener.java
└── Main.java

assets/
├── donjon.db
├── donjon_extensions.db
├── fond_donjon.png
└── icone_dragon.png

img/
├── ACCUEIL DONJON ECARLATE.png
└── captures d'écran du projet
```

---

## Fonctionnalités

* Navigation dynamique entre les paragraphes.
* Choix interactifs influençant l'histoire.
* Chargement des données depuis la base SQLite.
* Gestion des différentes fins de partie (défaite, victoire, etc.).
* Interface graphique épurée et réactive réalisée avec Swing.
* Synchronisation interface / logique grâce au pattern Observer.
* Centralisation du statut et de l'état du jeu via le Singleton `GameManager`.

---

## 📚 Objectifs pédagogiques

Ce projet permet de mettre en pratique :
* La programmation orientée objet (POO) en Java ;
* La persistance de données avec JDBC et SQLite ;
* La conception d'interfaces graphiques événementielles avec Swing ;
* L'application pratique des patrons de conception Singleton et Observer ;
* La structuration propre d'une application en architecture modulaire.

---

## 🎮 Univers du jeu

Le joueur incarne un aventurier explorant un donjon oublié, repaire du légendaire Dragon Écarlate.

Chaque paragraphe présente une situation et propose plusieurs options. Vos décisions vous mèneront vers de nouveaux embranchements jusqu'à atteindre l'aboutissement de votre quête.

---

##  Installation et lancement

### 1. Cloner le dépôt
```bash
git clone https://github.com/joaserwen-stack/tp5-donjon.git
```

### 2. Ouvrir le projet
Ouvrez le dossier dans votre IDE préféré (IntelliJ IDEA, Eclipse, NetBeans, VS Code).

### 3. Vérifier les ressources
S'assurer que le dossier `assets/` contenant `donjon.db` et les ressources graphiques se trouve bien à la racine du projet.

### 4. Exécuter l'application
Exécutez la classe principale `Main.java` pour démarrer le jeu.

---

## Concepts & Compétences clés

* Java Swing (IHM & Événements)
* JDBC / SQLite (Persistance)
* Architecture orientée objet & Modulaire
* Design Patterns (Singleton & Observer)

---

## 👨‍💻 Auteur

**Joas Erwen MVOUMA**
* **LinkedIn :** [linkedin.com/in/joas-erwen](https://linkedin.com/in/joas-erwen)
* **GitHub :** [github.com/joaserwen-stack](https://github.com/joaserwen-stack)
* **Portfolio :** [joaserwen-stack.github.io](https://joaserwen-stack.github.io)

  <img width="1917" height="1020" alt="ACCUEIL DOJON ECARLATE" src="https://github.com/user-attachments/assets/93244aa0-8555-4783-94eb-6cbf656dc713" />
