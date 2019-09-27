# Toteutusdokumentti

Projektin tarkoituksena on laatia ohjelma, joka ratkaisee perusmuotoisen sekä laajennetun 15-pelin.

## Ohjelman yleisrakenne
Luokka GamePosition kuvaa tiettyä pelitilannetta, eli numeroiden järjestystä pelilaudalla. GamePosition luokan olioon tallennetaan pelitilanteen lisäksi pelin ratkaisemiseen liittyvää tietoa. 

Peli ratkaistaan A* tyyppisen algoritmin avulla. Pelin lähtötilanne on verkon ensimmäinen solmu. Jokaisesta tilanteesta voi tehdä enintään neljä mahdollista siirtoa (ylös, alas, oikea, vasen). Reunoissa siirtomahdollisuuksia on kolme ja nurkissa kaksi. Algoritmi käy mahdollisuudet läpi ja vertaa niitä keskenään. Käsiteltäväksi valitaan A* algoritmissa aina se solmu (pelitilanne), jossa f(n)=g(n)+h(n) on pienin. g(n) on lähtöpisteesta kuljettu matka ja h(n) on arvio kokonaismatkasta määränpäähän. 

Funktiona h(n) käytetään tässä sovelluksessa laskelmaa siitä kuinka monta askelta kukin numero pitäisi yhteensä siirtää jotta se olisi oikealla paikallaan.

Funktio h(n) on toteutettu GamePosition luokan konstruktorissa. Paras seuraava tutkittava pelitilanne löytyy hyödyntämällä Javan PriorityQueue rakennetta sekä GamePostion luokaan toteutettua CompareTo metodia. 

Luokassa Viisitoistapeli on toteutettu pelin ratkaiseva algoritmi. Varsiaista käyttöliittymää ei ole vaan ohjelman käyttöä demotaan luokan Viisitoistapelin main metodista käsin. 

## Aika- ja tilavaativuudet 


## Työn mahdolliset puutteet ja parannusehdotukset


## Lähteet
Wikipedia, [A* Search Algortihm](https://en.wikipedia.org/wiki/A*_search_algorithm)


