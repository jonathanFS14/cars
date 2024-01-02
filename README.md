# cars

jeg har lavet nogle ændringer i koden i forhold til det originale tænkte projekt. 

min reservation klasse har start-date og end-date da jeg føler det afspejler mere den virkelige verden, derfor laver jeg nogle flere check i addreservation metoden i reservationservice klassen. 

min reservation response klasse har tre constructorer en til når jeg vil printe alle reservationer ud med den relaterede member og car, en til når jeg vil have en member og reservationen kun skal have car
og en til når jeg vil have en car og reservationen kun skal indeholde et member. de to sidste modifecerede construcotr indeholder en member og car i arugmentet men bliver ikke brugt, det er der udelukkende
til at identificere hvilken constructor der skal bruges. koden virker men jeg føler ikke det er flot kode, har i nogle ideer til hvordan jeg kunne gøre det mere clean og læsbart. 

[![Build and deploy JAR app to Azure Web App - dat3cars](https://github.com/jonathanFS14/cars/actions/workflows/master_dat3cars.yml/badge.svg)](https://github.com/jonathanFS14/cars/actions/workflows/master_dat3cars.yml)
