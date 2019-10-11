# Viikkoraportit

## Viikko 1 (9 tuntia)
- Aloitusluentoon osallistuminen. 
- Keskustelua ja pohdintaa aihevaihtoehdoista. 
- Aiheen valinta: 15-pelin ratkaiseva algoritmi. 
- Repositorion luominen
- Luotu Maven Projekti (toistaiseksi vielä tyhjä)
- Kirjoitettu määrittelydokumenttia
- Kirjoitettu viikkoraportti

### Epäselvää: 
Ohjelman toteuttaminen on vielä vasta ajatuksen tasolla ja kieltämättä myös melko epäselvä. Ajatus luultavammin selkiintyy kun seuraavalla viikolla pääsen koodaamaan. 

### Seuraavaksi: 
Ryhdyn koodaamaan Javalla ratkaisun 15-pelille.

## Viikko 2 (13 tuntia)
- Perehtymistä
- Ensimmäisen perusratkaisun toteuttaminen
- Ensimmäisten testien kirjoittaminen
- Täydennetty määrittelydokumenttia
- Lisätty määrittelydokumenttiin pohdintaa aikavaativuudesta. (jäi vielä täydentämisen varaa)
- Kirjoitettu viikkoraportti

### Opittua ja pohdittua
15 peli ja sen ratkaiseminen vaikuttaa olevan mielenkiintoinen projekti ja varsinkin isoilla pelikentillä saavutetaan varmasti tietokoneen laskentakapasiteetin rajat jos yritetään kaikki mahdollisuudet läpikäyvää brute-force ratkaisua. A* toimisi luultavasti melko tehokkaasti. Jos itse lähtisin tällaista peliä käsin ratkaisemaan yrittäisin ehkä ensin saada ykkösen paikalleen, sitten kakkosen, seuraavaksi kolmosen ilman, että jo paikoilleen laitetut häiriintyvät. Tällä ajattelulla voisi myös kokeilla tietokonealgoritmia. Kokeilematta luulen kuitenkin, että A* on tehokkaampi vaikka se joutuukin laskemaan pelitilanteista kustannusarviota. 

### Epäselvää:
Testit: Tein toistaiseksi yksinkertaiset testit pelitilanteen (GamePosition luokka) siirroista. Pohdin miten saisi tehtyä helposti/järkevästi vastaavat testit käyttäen erikokoisia ja muutenkin erilaisia pelikenttiä. Siis niin, ettei testikoodista tulisi älyttömän pitkää.

Aikavaativuutta olen vasta pohtinut ylimalkaisesti enkä ihan saa otetta siihen miten aikavaativuuden voisi tarkkaan päätellä. Pitää luultavasti kirjoittaa valmiiksi A* algoritmi ja tämän jälkeen pohtia aikavaativuutta uudestaan. 

### Seuraavaksi: 
Tässä vaiheessa on laadittu rekursiivinen hakualgoritmi, joka ratkaisee sujuvasti 2x2 kokoisen pelin. 3x3 kokoisissa peleissä löytyy myös ratkaisu(ja) mutta ennen haun loppumista tulee ylivuotovirhe ja lyhyin ratkaisu jää löytämättä. Seuravaksi etsin ylivuotovirheen aiheuttavan koodivirheen. Tämän jälkeen on tarkoitus toteuttaa tehokkaampi algoritmi, jossa pelitilannetta arvoidaan ja päätellään viekö tietty siirto lähemmäksi vai kauemmaksi ratkaisusta.  

GamePosition luokan konstruktorille ja setField metodille pitäisi tehdä virheenkäsittely, jotta laittomia pelikenttiä ei voisi luoda. 

GamePosition luokalle voisi tehdä konstruktorin, joka luo sattumanvaraisen pelikentän halutun kokoisena. 

## Viikko 3 (12 tuntia)
- Aloitettu A Star algoritmin toteuttaminen
- Toteutettu A Star kustannus/etäisyys laskenta GamePosition -luokan konstruktoriin
- Virheiden etsimistä ja korjaamista. Etäisyyslaskenta vaikuttaisi tässä vaiheessa toimivan oikein
- Käytetty aikaa algoritmin korjailuun. Vaikka etäisyyslaskenta toimii jää algoritmi joskus jumiin.
- Lisätty Checkstyle pom.xml tiedostoon.

### Opittua ja pohdittua
A Star algoritmi tekee kussakin verkon solmussa arvion siitä kuinka kaukana päätepisteestä ollaan. Tässä sovelluksessa olen tutkinut jokaisen pelilaudalla olevan numeron ja laskenut kuinka monen ruudun päässä kyseinen numero on määränpäästään kun reitit kulkevat ainoastaan ylös-alas sekä oikea-vasen (ei viistoon). Etäisyyslaskenta on toteutettu GamePosition -luokan konstruktorissa.  

### Seuraavaksi
Ensin työn alla oleva algoritmi pitäisi saada toimimaan kaikilla ratkaistavissa olevilla pelikentillä. Sen jälkeen vertaan sitä vielä siihen miten A Star algoritmi kuvataan. Olenko siis ajatellut ratkaisuni oikein. 

Vaikuttaisi siltä, että algoritmi jättää joitakin vaihtoehtoja kokeilematta ja siksi jää ratkaisu joskus löytämättä. Pitää siis parantaa seuraavan käsiteltävän solmun valintaa niin, että kaikki mahdollisuudet tutkitaan.

Senkin jälkeen kun toimiva algoritmi löytyy on vielä melko paljon tehtävää. Koodi on tässä vaiheessa jäänyt vähän keskeneräiseen tilaan ja epäsiistiksi. 

## Viikko 4 (12 tuntia)
- Korjattu kustannus/etäisyys laskenta. Ei se edellinen versio vielä toiminutkaan oikein. 
- Lisätty GamePosition luokkaan muuttuja moves, jolla lasketaan lähtötilanteesta tehtyjen siirtojen määrää. 
- Toteutettu GamePosition compareTo metodi, joka käyttää sekä tehtyjä siirtoja, että arvioitua etäisyyttä
- Kirjoitettu koko algoritmi uusiksi käyttäen PriorityQueue rakennetta. Nyt toteutus vastannee A Star toteutusta melko hyvin. 
- Laajennettu testejä
- Yritetty saada jacoco toimimaan
- Selkiytetty koodia kommenteilla
- Aloitettu toteutusdokumentin kirjoittaminen
- Oman tietorakenteen pohdintaa

### Opittua ja pohdittua
Nykyisessä algoritmissa käytetään PriorityQueue rakennetta, jonka avulla algoritmi valikoi seuraavan käsiteltävän pelitilanteen. Rakenne ei tarkista onko pelitilanne jo aikaisemmin tallennettu. Tämä aiheuttaa turhaa tilan käyttöä, mutta se ei muuten haittaa algoritmin toimintaa, sillä PriorityQueue antaa aina sen version pelitilanteesta, johon on tultu vähemmillä siirroilla. 

Viikon 4 palautustilanteen main metodissa ratkaistaan 4x4 kokinen peli. Ratkaisuun tarvitaan 20 siirtoa. Kuitenkin käsitellään lähes 2 miljoona pelitilannetta ja ratkaisun löytämiseen menee aikaa yli 4 sekuntia. Mietin onko algoritmissa sittenkin vielä jotain vikaa...

### Toteutettavan tietorakenteen suunnittelua
Käytetty PriorityQueue tuntuu käytännössä kasvavan hyvin isoksi. Yksi syy tähän on duplikaatit, jotka tässä kaikki huoletta lisätään jonoon. Vastaava oma tietorakenne voisi toimia niin, että tehdään lisäysvaiheessa duplikaattitarkistus. Jos sama pelitilanne löytyy poistetaan vanha mikäli uusi lisättävä on parempi, ja vastaavasta pidetään vanha jos uusi on huonompi.

### Seuraavaksi
Nyt olen saanut algoritmin toimimaan edes jotenkin järkevästi. Algoritmi ratkaisee nätisti "käsin sekoitetun" 4x4 kokoisen pelilaudan. Jos numerot laittaa kokonaan sattumanvaraiseen järjestykseen aikaa vaikuttaisi menevän liikaa. Toisaalta algoritmi voi myös jäädä jumiin sillä puolet mahdollisista numeroiden järjestyksistä muodostaa ratkaisemattoman pelin.
Seuraavaksi siis pitää vielä jatkaa algoritmin parantelua ja sen rajojen tutkimista. 
Sekä edelleen.... projetin rakenteen ja testien parantaminenm ja suunnitellun oman tietorakenteen toteutus.

## Viikko 5 (13 tuntia)
- Checkstyle saattaminen toimimaan NetBeansissa
- Lisätty yksinkertaisia GamePosition testejä. 
- Vertaisarvioprojektiin tutustuminen 
- Algoritmin kokeilua eri lähtötilanteilla    

### Viikon pohdintoja
Vertaisarvioprojektin tutkiminen vei tällä viikolla huomiota oman projektin etenemistä. Toisen kirjoittaman koodin hahmottaminen onkin yllättävän hankalaa vaikka työ olisi varsin laadukas. Tämä saattoi olla ihan hyvin käytettyä aikaa, sillä se avasi ainakin joitain ajatuksia miten voisin omankin projektin rakennetta tehdä järkevämmäksi.

Olen testannut algoritmia erilaisilla pelitilanteilla. Kun pelilaudan koko on 3x3 ja pelin sekoittaa 28 siirtoa kestää oikean ratkaisun löytäminen peräti 15 sekuntia. Jonoon kertyy tällöin 6.5 milj tutkittua pelitilannetta. Jos peliä sekoittaa lisää menee raktkaisuun jo niin paljon aikaa ettei siinä ole enää järkeä. Hyvä puoli tässä on se, että oikea ratkaisu löytyy kun sitä jaksaa odottaa. Algoritmissa on myös tehostamismahdollisuuksia, joten on mahdollista, että saisin sen ratkaisemaan myös enemmän sekotettuja pelilautoja järkevässä ajassa. 

## Viikko 6 (14 tuntia)
- Virheenetsintää ja läpikäyntiä kunnes tuli läpimurto algoritmin toimivuudessa ja nopeudessa. 
- PriorityQueuen korvaaminen omalla GamePositionQueue tietorakenteella. En kuitenkaan saanut sitä aluksi toimimaan. 
- Kokeiluna tein kokonaisluvuille kekorakenteen. Se tuntuu toimivan oikein. Jätin oman GamePositionQueue rakenteen vielä odottamaan.
- Algoritmi tulostaa nyt oikean ratkaisun siirrot kun se on löytynyt. 
- Rakenteen parantelua mm. palautteessa olleiden ideoiden mukaan. 
- Kokeiltu Dijkstra vs. A* algoritmi. Dijkstra on huomattavasti hitaamipi. 
- GamePositionQueue rakenteen läpikäyntiä. Katsoin tarkasti, että compareTo käytetään oikein päin ja löytyikin virhe kekoehdon varmistamisessa heapify metodeissa. Nyt toimii!

### Algoritmiongelman ratkaisu
Viime viikon esimerkin ratkaisu keski 15 sekuntia. Sain algoritmin nyt toimimaan kunnolla ja nopeasti. Isoin ero oli,että estin hakua palaamasta suoraan takaisin edelliseen tilanteeseen. Nyt viime viikkoinen esimerkkiratkaisu syntyy alle sekunnissa ja jonoon kertyy ~6000 pelitilannetta edellisen 6.5 milj tilalle. Nyt algoritmi osaa myös ratkaista 4x4 kokoisia ratkaistavissa olevia pelejä kohtuullisessa ajassa. 

### Oma tietorakenne GamePositionQueue.java
Tietorakenne tarjoaa GamePosition luokan olioille tarvittavat PriorityQueue luokkaa vastaavat toiminnot. Tallentamiseen käytetään arrayta jonka kokoa tuplataan tai puolitetaan tarpeen mukaan. 

### Opittua
Ajattelin, että keon toteutus tulee olemaan monimutkaista ja ikävää ja siksi se on lykkääntynyt. No toinen lykkäytymisen syy oli se, ettei algoritmi muutenkaan toiminut kunnolla ennen tätä viikkoa. Nopealla vilkaisulla tirakirjaan opin, että kekorakenteen tiedon tallennustapa ja operaatiot ovat niin helppoja ja loogisia että oikein tulee hyvälle tuulelle. Toteuttaminen oli ihan mukavaa puuhaa vaikka virheiden etsimisen kanssa aikaa meni lopulta melko paljon. 

### Pohdinnassa
Mietin mikä olisi helpoin ja/tai paras tapa toteuttaa suorityskykytestaus niin että siitä saisi mielenkiintoisia ja hyödyllisiä tuloksia.  

### Seuraavaksi
Tämän jälkeen työlistalla on testien parantaminen ja jonkinlaisen käyttöliittymän toteuttaminen. Tämän lisäksi jos aikaa vielä jää, jatkan vielä algoritmin suorityskyvyn optimointia ennen loppusiivousta ja viimeistelyä. 