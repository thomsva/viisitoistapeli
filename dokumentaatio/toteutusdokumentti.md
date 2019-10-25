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
Settings: Game size 4*4, mix: 0, method: A*, timeout: 1000 ms
RandomBoost: true  (true = on, false = off)
Distances: Manhattan: 0, Edges: 0
Streak: 7
Available commands:
(1) size: Initialize game to a new size.
(2) mix: Mix the game.
(3) method: Select search algortihm type.
(4) solve: Attempts to find solution for game.
(5) test: Run performance tests (100 runs).
(6) timeout: Change timeout
(7) randomboost: Toggle on or off
(8) exit: Exits program
> 

## Käyttöohje
Käyttöliittymän komentoja voi valita kirjoittamalla komento tai valitsemalla komentoa vastaava numero ja painamalla enteriä.
Ensin valitaan sopivat parametrit. Sen jälkeen sekoitetaan peli mix -toiminnolla ja ratkaistaan se solve -toiminnolla. 
Test toiminto ratkaisee pelin 100 kertaa parametreissä valituilla spekseillä. Kannattaa valita silloin sopivan lyhyt timeout että tuloksia jaksaa odottaa. 
Jos testit käynnistää kun mix=0, eli peliä ei ole sekoitettu käynnistyy testi siten, että sekoitusta lisätään vaiheittan testin edetessä. 

Käyttöliittymään tulostuu pisteitä ja huutomerkkejä algortimin työskennellessä. Huutomerkki tarkoittaa Random Boost toiminnon käynnistymistä (kuvattu alla). Piste tarkoittaa, että pelin työn alla oleva ylärivi ja vasen sarake on saatu valmiiksi. Lähtökohtaisesti nämä lukittuvat silloin ja ratkaistavana on alkuperäistä pienempi peli. 


## Oma tietorakenne GamePositionQueue
Minimikeko, joka tarjoaa seuraavaksi sen pelitilanteen, jossa kuljettu matka plus arvioitu jäljellä oleva matka on pienin. Keko tallentaa tietoa taulukkoon, jonka kokoa tuplataan tai puolitetaan aina tarvittaessa. 

## Aika- ja tilavaativuudet 
Esimerkkinä ollut pelitilanne voidaan ratkaista 20 siirrolla. Algoritmi käytti 12 millisekuntia oikean ratkaisun löytämiseksi ja pelitilanteita on käyty läpi noin 1000. Jos h(n) kustannuslaskenta otetaan pois käytöstä, löytyy täsmälleen sama ratkaisu mutta ratkaisun löytäminen kestää 51 sekuntia kun pelitilanteita on käyty läpi yli 7 milj. 

Tämä kuvastaa miten tärkeä käytetty kustannusfunktio on aikavaativuuden kannalta. Pahimmillaan A* algoritmin aikavaativuus sanotaan olevan O(b^d), jossa b on "branching factor" eli haarautumiskerroin. 

Viisitoistapelissä tiedetään, että haarautumiskerroin on aina alle 3 (kun suljetaan pois mahdollisuus palata takaisin edelliseen tilanteeseen). Paras mahdollinen haarautumiskerroin on 1 (jos algoritmi aina löytää parhaan seuraavan siirron). Verkon syvyys d on 4x4 kokoisessa pelissä enintään 80 (Wikipedia). Esimerkkitapauksessa syvyys oli 20. Jotta peli olisi ratkaistavissa kohtuullisessa ajassa pitää haarautumiskerroin olla alhainen, mahdollisimman lähellä 1. 

Alla kuvattu "edges" lähestymistavan tarkoituksena on vähentää haarottumista sillä tasapelitilanteita tulee vertailuissa vähemmän. 

GamePositionQueue noudattaa tunnettuja maksimikeon aika- ja tilavaativuuksia. 

## Suorituskyvyn parantelua
Vaikuttaisi siltä, lyhyimmän mahdollisen polun löytäminen missä tahansa pelissä kohtuullisessa ajassa on melko haastavaa. Kokeilin siksi muuttaa tavoitetta hieman, eli haen mitä tahansa ratkaisua mahdollisimman nopeasti. Ratkaisun ei siis tarvitse olla lyhyin polku, mutta tavoitteena on ratkaista mikä tahansa 4*4 kokoinen peli kohtuullisessa ajassa. 

### Laitojen järjestelyä, "edges" comparaattori
Ajattelen, että algoritmi toimisi nopeammin, jos sitä kannustetaan ratkaisemaan peliä enemmän ihmisen tavalla. Ihminen (ainakin minä itse) aloittaa ratkaisun järjestämällä yläriviä ja jatkaa sitten alaspäin. Toteutin siksi erilaisen lajitteluperusteen, jossa pyritään ratkaisemaan pelin vasenta ja ylintä laitaa. Tämä vaikuttaisi toimivan hyvin silloin kun se käytetään toissijaisena lajitteluperusteena, eli silloin kuin A* käyttämä kuljettu etäisyys plus Manhattan etäisyys näyttää "tasapeliä" kahden tilanteen välillä. Näitä tasapelejä on paljon siksi tämä vaikuttaa toimivan hyvin. Tämä laitoja rakentava toiminnallisuus löytyy EdgesComparator.java tiedostosta. 

### Random boostilla lisää suorituskykyä
Edges tyylinen tilanteiden vertailu paransi suorituskykyä mutta edelleen jää peli joskus jumiin. Jos esim. 4*4 pelissä on 1 ja 5 vaihtanut paikkaa ja 2 sekä 9 paikallaan. Silloin pitää olemassaolevaa järjestystä rikkoa melko paljon, jotta saataisiin 1 ja 5 vaihtamaan paikkaa. Algoritmi ei luonnostaan riko järjestystä vaan jää jumiin. 
Yleisenä keinona edetä jumiutuneesta tilanteesta on järkevien liikkeiden korvaaminen sattumanvaraisilla liikkeillä vähäksi aikaa. Tuurilla voidaan päästä parempaan tilanteeseen, jolloin voidaan jatkaa järkevää toimintaa. Tein tällaisen toiminnallisuuden hakualgoritmiin ja nimesin sen "Random boost". Tämä paransi suorituskykyä mukavasti! Löydetty polku ei tosin ole aina kovin lyhyt mutta löytyypä joku ratkaisu kohtuullisessa ajassa. 

## Työn puutteet ja parannusehdotukset
Ohjelmassa ei ole erityisesti huolehdittu poikkeuksien hallinnasta. Käyttäjän syötteitä ei myöskään validoida. Suorityskykyä on varmasti vielä mahdollista parantaa paljonkin vaikka nytkin on tehty useita harppauksia eteenpäin perustasolta. 
Ohjelman rakennetta olen parannellut sen jälkeen kun sain algoritmin toimimaan. GamePosition luokka on melko pitkä ja se voisi olla hyvä jakaa pienempiin. Esim. siirtojen toiminnallisuus voisi mahdollisesti olla kätevämpi luokan ulkopuolella. 


## Lähteet
Wikipedia, [A* Search Algortihm](https://en.wikipedia.org/wiki/A*_search_algorithm)

Antti Laaksonen, [Tirakirja](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/)

Wikipedia, [15 puzzle](https://en.wikipedia.org/wiki/15_puzzle)


