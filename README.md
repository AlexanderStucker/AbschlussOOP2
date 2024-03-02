# Filmverwaltung und -bewertung

Dieses Projekt ist eine Anwendung zur Verwaltung von Filmen, Serien und Bewertungen, die mit Java und JavaFX entwickelt wurde. Es ermöglicht das Hinzufügen von Filmen, Serien und Bewertungen sowie die Anzeige und Sortierung der Daten. Die Anwendung verwendet Collections wie ArrayLists zur Verwaltung von Filmen und Serien, Enums für die Festlegung von Genres und implementiert die Java Serialisierung für die Speicherung der Daten.

## Projektstruktur

Das Projekt ist als Maven-Projekt strukturiert und organisiert in verschiedenen Paketen:

- **model**: Enthält Klassen für Filme, Serien, Bewertungen und Enums.
- **data**: Hier werden Collections für die Verwaltung von Filmen und Serien implementiert.
- **ui**: Beinhaltet die JavaFX-Oberfläche mit Controller-Klassen.
- **serialization**: Implementiert die Java Serialisierung für die Speicherung von Daten.
- **exceptions**: Enthält benutzerdefinierte Exceptions für die Fehlerbehandlung.

## Datenmodell

Die Klassen für Filme, Serien und Bewertungen enthalten die notwendigen Attribute und Methoden zur Speicherung und Abfrage von Informationen. Enums werden verwendet, um Genres festzulegen.

## Collections und Sortierung

ArrayLists werden verwendet, um Filme und Serien zu verwalten. Sortierfunktionen sind implementiert, um Filme nach verschiedenen Kriterien zu sortieren.

## Benutzeroberfläche

Die JavaFX-Oberfläche ermöglicht es dem Benutzer, Filme und Serien hinzuzufügen, Bewertungen abzugeben und die Daten anzuzeigen. Die Benutzeroberfläche ist mit Textfeldern, Buttons, Listenansichten und Dialogfeldern benutzerfreundlich gestaltet.

## Serialisierung

Die Java Serialisierung wird verwendet, um Daten wie Film/Serien-listen und Benutzer zu speichern und nach einem Neustart der Anwendung wiederherzustellen.

## Ausnahmen und Logging

Exceptions werden verwendet, um Fehler sauber abzufangen und detaillierte Fehlermeldungen anzuzeigen. Logging-Framework ist implementiert um Log-Daten zu erfassen und Fehler zu analysieren.

## Tests

Unit-Tests sind implementiert, um wichtige Teile der Applikationslogik zu prüfen. Dies umfasst die Validierung von Eingaben, das Hinzufügen und Überprüfen von Filmen.

## Techniken

Vererbung, Interfaces, abstrakte Klassen und Generics werden genutzt, um die Code-Wiederverwendbarkeit zu verbessern und eine saubere Architektur zu erstellen.

## Benutzerinteraktion

Funktionen sind implementiert, um Filme und Serien hinzuzufügen, Bewertungen abzugeben. Die Benutzerinteraktion ist intuitiv und benutzerfreundlich gestaltet.

## Build und Ausführbare Version

Maven ist konfiguriert, um das Projekt zu kompilieren und eine ausführbare JAR-Datei zu erstellen.

## Weitere Ideen

- **Filmempfehlungen**: Implementiere einen personalisierten Algorithmus für Filmempfehlungen basierend auf dem Benutzerverhalten.
- **Film Roulette**: Schlägt zufällig einen Film vor, der nach persönlichen Vorlieben personalisiert werden kann.
