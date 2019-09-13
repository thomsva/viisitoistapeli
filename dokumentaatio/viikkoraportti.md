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

## Viikko 2 (  tuntia)
- Perehtymistä
- Ensimmäisen perusratkaisun toteuttaminen
- Ensimmäisten testien kirjoittaminen
- Täydennetty määrittelydokumenttia
- Lisätty määrittelydokumenttiin pohdintaa aikavaativuudesta. (jäi vielä täydentämisen varaa)
- Kirjoitettu viikkoraportti

### Epäselvää:
Tein toistaiseksi yksinkertaiset testit pelitilanteen (GamePosition) siirroista. Pohdin miten saisi tehtyä helposti/järkevästi vastaavat testit käyttäen erikokoisia ja muutenkin erilaisia pelikenttiä. 
Aikavaativuutta olen vasta pohtinut ylimalkaisesti enkä ihan saa otetta siihen miten aikavaativuuden voisi tarkkaan päätellä. Pitää luultavasti kirjoittaa valmiiksi A* algoritmi ja tämän jälkeen pohtia aikavaativuutta uudestaan. 

### Seuraavaksi: 
Tässä vaiheessa on laadittu rekursiivinen hakualgoritmi, joka ratkaisee sujuvasti 2x2 kokoisen pelin. 3x3 kokoisissa peleissä löytyy myös ratkaisu(ja) mutta ennen haun loppumista tulee ylivuotovirhe ja lyhyin ratkaisu jää löytämättä. Seuravaksi etsin ylivuotovirheen aiheuttavan koodivirheen. Tämän jälkeen on tarkoitus toteuttaa tehokkaampi algoritmi, jossa pelitilannetta arvoidaan ja päätellään viekö tietty siirto lähemmäksi vai kauemmaksi ratkaisusta.  

GamePosition luokan konstruktorille ja setField metodille pitäisi tehdä virheenkäsittely, jotta laittomia pelikenttiä ei voisi luoda. 

GamePosition luokalle voisi tehdä konstruktori, joka luo sattumanvaraisen pelikentän halutun kokoisena. 
