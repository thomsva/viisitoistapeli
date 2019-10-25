# Viisitoistapeli
A search algorithm for solving the 15 puzzle. Dijkstra and A* algorithms provide solid results and shortest paths but are too slow for solving any 4*4 sized game. Using the same search but a different comparator where more focus is given to finding the edge pieces first, the speed have been increased considerably. By adding a random element the performance has been improved further. 

The algorithm now solves any 4*4 game in reasonable time. A 8*8 sized game mixed with 20000 random moves took a little less than 4 minutes to solve. 

- [Viikkoraportti](https://github.com/thomsva/viisitoistapeli/blob/master/dokumentaatio/viikkoraportti.md)
- [Määrittelydokumentti](https://github.com/thomsva/viisitoistapeli/blob/master/dokumentaatio/m%C3%A4%C3%A4rittelydokumentti.md)
- [Toteutusdokumentti](https://github.com/thomsva/viisitoistapeli/blob/master/dokumentaatio/toteutusdokumentti.md)
- [Testausdokumentti](https://github.com/thomsva/viisitoistapeli/blob/master/dokumentaatio/testausdokumentti.md)