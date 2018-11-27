Das Pojket wurde von My-Nhien Nguyen & Pradip Ravichandran ausarbeitet.

Hier wäre die Projektbeschreibung.

<h1>Bloom-Filter</h1>
Ziel der Aufgabe ist es, einen Bloom-Filter zu programmieren und die Funktionsweise darzustellen.
Folgende Schritte werden erwartet:
<ol>
<li>Sie verschaffen sich einen Überblick über die Funktionsweise, etwa unter https://en.wikipedia.org/wiki/Bloom_filter.</li>
<li>Sie erstellen ein Java-Programm, was folgendes leistet:
<ol>
<li>Bei einer gegebenen Anzahl n an zu erwartenden Elementen, die in der Datenstruktur
gespeichert werden und einer Fehlerwahrscheinlichkeit p wird eine geeignete Filtergrösse m und die optimale Anzahl k an Hashfunktionen berechnet.</li>
<li>Die Datenstruktur wird implementiert, mit den Methoden zum Einfügen von Strings und dem Test, ob ein String enthalten ist. Als Hash-Funktionen soll murmur3 128 (etwa in Guava enthalten: https://github.com/google/guava/wiki/Release19) verwendet werden mit jeweils einem anderen Seed.</li>
<li>Lassen Sie das Programm, welches eine Fehlerwahrscheinlichkeit p als Eingabe erhält, die Wörter aus words.txt einlesen und in einen Boom-Filter geeigneter Grösse einfÜgen. Überprüfen Sie die Fehlerwahrscheinlichkeit, in dem Sie für eine grosse Anzahl an nicht enthaltenden Strings testen, ob sie enthalten sind. Die so experimentell bestimmte Fehlerwahrscheinlichkeit soll zusammen mit den Parametern der Datenstruktur ausgegeben werden.</li>
<li>Bitte räumen Sie ihr Projekt so auf, dass ich keine speziellen Pakete nachladen oder komplizierte Pfadanpassungen machen muss.</li>
</ol>
</li>
<li>Sie erstellen mit LATEX(https://www.latex-project.org/) eine Zusammenfassung voein bis zwei Seiten, welche folgendes enthält:
<ol>
<li>Idee des Bloom-Filters, mit Vor- und Nachteilen</li>
<li>ein konkretes Beispiel aus der Praxis, wo der Bloom-Filter verwendet wird mit kurzer Beschreibung dieses Programms</li>
<li>Eine Beschreibung, wie Sie die Fehlerwahrscheinlichkeit ihrer Datenstruktur getestet haben und welche Resultate dabei erzielt worden sind.</li>
</ol>
</li>
<li>Senden Sie mir bitte per Mail sowohl das Java-Projekt als auch den LATEX-Quellcode (und das fertige pdf) zu.</li>
</ol>

Allgemeine Hinweise:
<ol>
<li>Sie können in Gruppen bis zu drei Personen arbeiten.</li>
<li>Bei vollständiger Lösung wird auf die Note des kommenden Tests 0.3 drauf addiert. (Aus
systemtechnischen Gründen liegt die Erfahrungsnote zwischen 1.0 und 6.0.) Teilpunkte
werden vergeben, wenn nicht alle Anforderungen erfüllt sind.</li>
<li>Es ist nicht nötig, das Programm hinsichtlich Effizienz zu optimieren.</li>
<li>Das Programm sollte verständlich kommentiert sein.</li>
<li>Eigentlich gehe ich davon aus, dass Sie aus Fairnessgründen nicht versuchen, zu betrügen. Dennoch werde ich dies (auch mit Hilfe von Tools) kontrollieren. Falls dabei ein Täuschungsversuch festgestellt wird (also: (verschleierte) Kopien von Teilen existierender Programme (Internet oder Kollegen)), wird die Note des nächsten Tests auf 1.0 gesetzt.</li>
</ol>

Abgabe: 27.11.2018
