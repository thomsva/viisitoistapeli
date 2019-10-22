# Testausdokumentti

## Suorituskykytestausta

### A*, Heuristiikka: Manhattan distance
Sekoitus 75 siirtoa. Testataan 100 kertaa. Timeout 10 sekuntia. 
99 successful attempts out of 100.
Total work time: 9250 ms.
Average work time: 93 ms.

Sekoitus 10000 siirtoa. Testataan 100 kertaa. Timeout 10 sekuntia. 
25 successful attempts out of 100.
Total work time: 113821 ms.
Average work time: 4552 ms.

### A*, Heuristiikka: Manhattan distance - alennus
Kustannusfunktioon on toteutettu alennus, joka kannustaa algoritmia ratkaisemaan peliä ylävasemmalta alkaen. Alennus riippuu siitä, kuinka monta numeroa alkaen ykkösestä on paikallaan. Alennuksen koko on 1 etäisyysmitta per paikallaan oleva numero. Lisäksi lasketaan lisäalennus kun kokonainen rivi on täynnä. Toiseksi viimeisestä rivistä ei kuitenkaan lasketa lisäalennusta sillä peliä ei voi ratkaista liikkumalla ainoastaan yhdellä rivillä. 
Huom: tämä muunnos nopeuttaa algoritmin toimintaa mutta ei tuota lyhintä mahdollista ratkaisua. 

Sekoitus 75 siirtoa. Testataan 100 kertaa. Timeout 10 sekuntia. 
100 successful attempts out of 100.
Total work time: 592 ms.
Average work time: 5 ms.

Sekoitus 10000 siirtoa. Testataan 100 kertaa. Timeout 10 sekuntia.
98 successful attempts out of 100.
Total work time: 36072 ms.
Average work time: 368 ms.


### A*, Heuristiikka: Manhattan distance - alennus, Javan oma PriorityQueue
Yllä mainitut testit on tehty itse toteutetulla minimikeko rakennetta hyödyntäen. Vertailun vuoksi vastaavat testit käyttäen Javan PriorityQueue tietorakennetta. 

Sekoitus 75 siirtoa. Testataan 100 kertaa. Timeout 10 sekuntia. 
100 successful attempts out of 100.
Total work time: 597 ms.
Average work time: 5 ms.

Sekoitus 10000 siirtoa. Testataan 100 kertaa. Timeout 10 sekuntia.
98 successful attempts out of 100.
Total work time: 43702 ms.
Average work time: 445 ms.
