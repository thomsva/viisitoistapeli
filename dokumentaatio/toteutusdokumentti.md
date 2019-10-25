# Toteutusdokumentti

Projektin tarkoituksena on laatia ohjelma, joka ratkaisee perusmuotoisen sekä laajennetun 15-pelin.

## Ohjelman yleisrakenne
Luokka GamePosition kuvaa tiettyä pelitilannetta, eli numeroiden järjestystä pelilaudalla. GamePosition luokan olioon tallennetaan pelitilanteen lisäksi pelin ratkaisemiseen liittyvää tietoa. 

![example](images/example4x4.png)

Ylläoleva pelitilanne voisi muodostaa pelin alkutilanteen. Luvut ja niiden järjestys tallennetaan GamePosition luokan 16 paikkaiseen kokonaislukutaulukkoon. Tyhjä kohta tallennetaan nollana. {5, 1, 0, 4, 6, 7, 3, 8, 2, 14, 10, 11, 9, 13, 15, 12}

Peli ratkaistaan A* tyyppisen algoritmin avulla. Pelin lähtötilanne on verkon ensimmäinen solmu. Jokaisesta tilanteesta voi tehdä enintään neljä mahdollista siirtoa (ylös, alas, oikea, vasen). Reunoissa siirtomahdollisuuksia on kolme ja nurkissa kaksi. Algoritmi käy mahdollisuudet läpi ja vertaa niitä keskenään. Käsiteltäväksi valitaan A* algoritmissa aina se solmu (pelitilanne), jossa f(n)=g(n)+h(n) on pienin. g(n) on lähtöpisteesta kuljettu matka ja h(n) on arvio kokonaismatkasta määränpäähän. 

Funktiona h(n) käytetään tässä sovelluksessa laskelmaa siitä kuinka monta askelta kukin numerolaatta pitäisi yhteensä siirtää jotta se olisi oikealla paikallaan (ns. Manhattan Distance).

Luokassa GameSolver on toteutettu pelin ratkaiseva algoritmi. GameSolver luokan search metodille annetaan lähtötilanteeksi GamePosition luokan olio. Search metodi käyttää hyväkseen kekorakennetta tallentaakseen läpikäydyt pelitilanteet. Käsittelyyn otetaan mahdollisista seuraavista pelitilanteista käsittelyyn aina paras vaihtoehto yllä kerrotun mukaisesti. 

## Käyttöliittymä
Ohjelmaan on toteutettu tekstipohjainen käyttöliittymä eri toimintoja varten. 

--------------------------------------
  1   2   3   4 
  5   6   7   8 
  9  10  11  12 
 13  14  15   0 
--------------------------------------
Settings: Game size 4*4, mix: 0.
Available commands:
(1) size: Initialize game to a new size.
(2) mix: Mix the game.
(3) solve: Attempts to find solution for game.
(4) test: Run performance test and exit.
(5) exit: Exits program
> 

Käyttöliittymän tarkempi toiminta on kuvattu käyttöohjeessa. 

## Oma tietorakenne GamePositionQueue
Minimikeko, joka tarjoaa seuraavaksi sen pelitilanteen, jossa kuljettu matka plus arvioitu jäljellä oleva matka on pienin. Keko tallentaa tietoa taulukkoon, jonka kokoa tuplataan tai puolitetaan aina tarvittaessa. 

## Aika- ja tilavaativuudet 
Esimerkkinä ollut pelitilanne voidaan ratkaista 20 siirrolla. Algoritmi käytti 12 millisekuntia oikean ratkaisun löytämiseksi ja pelitilanteita on käyty läpi noin 1000. Jos h(n) kustannuslaskenta otetaan pois käytöstä, löytyy täsmälleen sama ratkaisu mutta ratkaisun löytäminen kestää 51 sekuntia kun pelitilanteita on käyty läpi yli 7 milj. 

Tämä kuvastaa miten tärkeä käytetty kustannusfunktio on aikavaativuuden kannalta. Pahimmillaan A* algoritmin aikavaativuus sanotaan olevan O(b^d), jossa b on "branching factor" eli haarautumiskerroin. 

Viisitoistapelissä tiedetään, että haarautumiskerroin on aina alle 3 (kun suljetaan pois mahdollisuus palata takaisin edelliseen tilanteeseen). Paras mahdollinen haarautumiskerroin on 1 (jos algoritmi aina löytää parhaan seuraavan siirron). Verkon syvyys d on 4x4 kokoisessa pelissä enintään 80 (Wikipedia). Esimerkkitapauksessa syvyys oli 20. Jotta peli olisi ratkaistavissa kohtuullisessa ajassa pitää haarautumiskerroin olla alhainen, mahdollisimman lähellä 1. 

GamePositionQueue noudattaa tunnettuja maksimikeon aika- ja tilavaativuuksia. 

## Suorituskyvyn parantelua
Vaikuttaisi siltä, lyhyimmän mahdollisen polun löytäminen missä tahansa pelissä kohtuullisessa ajassa on melko haastavaa. Kokeilin siksi muuttaa tavoitetta hieman, eli haen mitä tahansa ratkaisua mahdollisimman nopeasti. Ratkaisun ei siis tarvitse olla lyhyin polku, mutta tavoitteena on ratkaista mikä tahansa 4*4 kokoinen peli kohtuullisessa ajassa. 

Ajattelen, että algoritmi toimisi nopeammin, jos sitä kannustetaan ratkaisemaan peliä enemmän ihmisen tavalla. Ihminen (ainakin minä itse) aloittaa ratkaisun järjestämällä yläriviä ja jatkaa sitten alaspäin. Toteutin siksi kustannusfunktioon "alennuksia" riippuen siitä kuinka paljon pelin yläosasta on ratkaistu. Alennus riippuu siitä, kuinka monta numeroa alkaen ykkösestä on paikallaan. Alennuksen koko on 1 etäisyysmitta per paikallaan oleva numero. Lisäksi lasketaan lisäalennus kun kokonainen rivi on täynnä. Toiseksi viimeisestä rivistä ei kuitenkaan lasketa lisäalennusta sillä peliä ei voi ratkaista liikkumalla ainoastaan yhdellä rivillä. 

Alennus johtaa esim. siihen, että ne pelitilanteet, jossa ensimmäinen rivi on raktaistu on huomattavasti kiinnostavampia jatkon kannalta kuin ne pelitilanteet, jossa ensimmäinen rivi ei ole ratkaistu. 

## Suorityskykytestejä
Algoritmi ratkaisee nyt lähes kaikki 4*4 kokoiset pelit ja kaikki sitä pienemmät pelit vaikka niitä sekoittaa paljonkin. 
Testeissä ratkesi 98 peliä 100:sta kun aikaraja oli 10 sekuntia ja peliä oli sekoitettu 10000 siirtoa. Keskimääräinen ratkaisuaika oli 0.368 sekuntia. 
Isompi, esim. 10*10 kokoinen peli ratkeaa kohtuullisen nopeasti ja varmasti jos sitä sekoitetaan 75 siirtoa. Sen sijaan jos sekoitus on 10000 siirtoa ei oikeaa ratkaisua löydy 10 sekunnissa kertaakaan / sata yritystä. 

Tarkemmat tulokset suorituskykytesteistä löytyy testausdokumentista. 

## Tehtyjä kokeiluja
Yritin kannaustaa ratkaisijaa siirtämään pienet numerot ensin lähemmäksi oikeata paikkaansa. Tein tämän painottamalla kustannuslaskentaa siten, että pienet numerot saivat isoimman kertoimen kuin isot. Tällä tulokset 

## Työn puutteet ja parannusehdotukset
Nykyisellään algoritmi tuottaa melko lyhyen polun melko lyhyessä ajassa. Suorityskyvyn kannalta käytetty heurestiikka vaikuttaa hyödyllisin optimointikohta. Parannetulla heurestiikalla olisi vielä mahdollista parantaa suorituskykyä. 
Ohjelmassa ei ole erityisesti huolehdittu poikkeuksien hallinnasta. Käyttäjän syötteitä ei myöskään validoida. 
Timeout on kovakoodattu 10 sekuntia. Olisi kätevää jos käyttäjä voisi itse valita paljonko haluaa antaa aikaa algoritmille. 

## Lähteet
Wikipedia, [A* Search Algortihm](https://en.wikipedia.org/wiki/A*_search_algorithm)

Antti Laaksonen, [Tirakirja](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/)

Wikipedia, [15 puzzle](https://en.wikipedia.org/wiki/15_puzzle)


