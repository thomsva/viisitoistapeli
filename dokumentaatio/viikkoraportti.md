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