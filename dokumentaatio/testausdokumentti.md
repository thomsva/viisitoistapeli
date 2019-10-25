# Suorituskykytestejä

Testit on suoritettu käyttöliittymän avulla. Ensin on asetettu testin parametrit ja sen jälkeen ajettu 100 testiajoa valitsemalla käyttöliittymästä kohta testi. 

## Yhteenveto
### Dijkstra
Yhteenvetona testeistä näkee, että Dijkstra löytää varmuudella lyhimmän polun mutta on liian hidas hyvin sekoitetulla 4*4 pelillä. 
### A*
A* on nopeampi ja löytää todennäköisesti lyhimmän polun. A* ei kuitenkaan ole riittävän nopea minkä tahansa 4*4 kokoisen pelin ratkaisemiseksi. A* ratkaisee isonkin pelin kunhan se ei ole kovin paljon sekoitettu. 
### Edges
Edges vaihtoehdoksi nimetty algoritmi painottaa vasemman ja ylemmän reunan valmistamisa ensin. Kun reuna on valmis, se tyhjentää haun käytäämän minimikeon, jolloin säästyy muistia ja myös nopeus kasvaa. Verrattuna A* algoritmi on yleensä nopeampi. Se kuitenkin jää välillä jumiin kun joissain tilanteissa reunaa pitäisi vähän rikkoa, että sen saisi rakennettua kokonaiseksi. 
### RandomBoost
Kun huomasin, että Edges algoritmi jää jumiin lisäsin mahdollisuuden käyttää ratkaisijassa satunnaisuutta. Tietyin välein algoritmi tyhjentää keon ottaen siihen mennessä parhaan pelitilanteen talteen. Sen jälkeen se lisää kekoon sattumanvaraisia siirtoja pohjautuen talteen otettuun pelitilanteeseen. Tämän jälkeen algoritmi jatkaa normaalisti. RandomBoost parantaa yleisesti suorituskykyä yhdessä Edges algoritmin kanssa. Tällä yhdistelmällä ratkeaa mikä tahansa 4x4 peli ja myös vähän isommat pelit onnistuvat. Löydetyt ratkaisut eivät todennäköisesti ole lyhimpiä polkuja, vaan tässä nimenomaan on haettu parempaa ratkaisunopeutta.  

## Tuloksia
### Dijkstra, 4*4 peli
Sekoitus 50 siirtoa. Timeout 2 sekuntia. 
34 successful attempts out of 100.
Total work time: 5065 ms.
Average work time: 148 ms.

Sekoitus 20000 siirtoa. Timeout 2 sekuntia. 

### A*, 4*4 peli
Sekoitus 50 siirtoa. Timeout 2 sekuntia. 
100 successful attempts out of 100.
Total work time: 245 ms.
Average work time: 2 ms.

Sekoitus 20000 siirtoa. Timeout 2 sekuntia. 
0 successful attempts out of 100.


### Edges, 4*4 peli
Sekoitus 50 siirtoa. Timeout 2 sekuntia. 
100 successful attempts out of 100.
Total work time: 1781 ms.
Average work time: 17 ms.

Sekoitus 20000 siirtoa. Timeout 2 sekuntia. 
46 successful attempts out of 100.
Total work time: 30593 ms.
Average work time: 665 ms.

### A* + RandomBoost, 4*4 peli
99 successful attempts out of 100.
Total work time: 50695 ms.
Average work time: 512 ms.

### Edges + RandomBoost, 4*4 peli
Sekoitus 20000 siirtoa. Timeout 2 sekuntia. 
100 successful attempts out of 100.
Total work time: 25242 ms.
Average work time: 252 ms.

### Edges + RandomBoost, 5*5 peli
Sekoitus 20000 siirtoa. Timeout 2 sekuntia. 
62 successful attempts out of 100.
Total work time: 76239 ms.
Average work time: 1229 ms.

### Edges + RandomBoost, 6*6 peli
Sekoitus 20000 siirtoa. Timeout 2 sekuntia. 
3 successful attempts out of 100.
Total work time: 3928 ms.
Average work time: 1309 ms.

### Edges + RandomBoost, 8*8 peli
Sekoitus 20000 siirtoa. Timeout 2 sekuntia. 
1 successful attempts out of 1.
Total work time: 218259 ms.

