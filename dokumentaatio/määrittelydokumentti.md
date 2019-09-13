# Määrittelydokumentti

Projektin tarkoituksena on laatia ohjelma, joka ratkaisee perusmuotoisen sekä laajennetun 15-pelin.

## Käytettävät algoritmit ja tietorakenteet
Pelitilanne tallennetaan taulukkoon. Perusmuodossaan 15-pelissä käytetään 4x4 kokoista taulukkoa mutta sovelluksessa mahdollistetaan myös isomman pelikentän käytön. 
Algoritmi hyödyntää ratkaisun löytämisessä verkkoa. Kaikki mahdolliset pelitilanteet muodostavat verkon solmut ja tehtävät pelisiirrot ovat verkkojen kaaria. Pelin ratkaisu mahdollisimman vähin siirroin löytyy kun etsitään verkossa lyhyin polku alkutilanteesta loppuratkaisuun. 

Projektissa kokeillaan ja verrataan erilaisia hakualgoritmeja pelin ratkaisemiseksi. Ainakin A* tyyppinen algoritmi tulisi toteuttaa. Tässä algoritmityypissä lasketaan ja tallennetaan pelitilanteelle aina "kustannusarvio" joka on käytännössä arvio ratkaisuun tarvittavien siirtojen määrästä. 

## Pohdintaa aikavaativuudesta
Aikavaativuus riippuu pelin koosta. 4x4 kokoisessa pelissä voi numerot olla 16! eri järjestyksessä. Näistä puolet ovat sellaisia, että peliä ei ole mahdollista ratkaista. Käytännössä mahdollisia pelitilanteita on siis 16!/2. Jokaisesta pelitilanteesta on enintään 4 mahdollista siirtoa toisiin pelitilanteisiin. Jos tyhjä ruutu on nurkassa mahdollisia siirtoja on kaksi. Tyhjä ruutu reunassa merkitsee kolmea siirtomahdollisuutta ja keskialueella mahdollisuuksia neljä. 

## Syötteet sekä ohjelman tuottama ratkaisu
Ohjelma saa syötteeksi käyttäjältä pelikentän koon, jonka jälkeen ohjelma luo pelille satunnaisen lähtötilanteen. 
Kun ohjelma on löytänyt pelin ratkaisun, se tulostaa ratkaisuun tarvittavat siirrot, siirtojen määrän sekä ratkaisuun käytetyn ajan. 

## Lähteet
https://en.wikipedia.org/wiki/15_puzzle
