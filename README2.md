- What are the benefits of using a RESTful API

  REST leveres med en fleksibel tilgang og formater til serialisering af data i JSON
  RESTful API'er kan tilgås via HTTP, hvilket gør dem tilgængelige på tværs af forskellige platforme og sprog.
  
- What is JSON, and why does JSON fit so well with REST?

  JSON understøtter strukturerede data, herunder objekter og arrays, hvilket gør det ideelt til at overføre oplysninger til objekter.
  Da JSON kan bruges med JavaScript, fungerer det godt med webapplikationer, der ofte bruger RESTful API'er.
  
- How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's

  dto'erne er delt op i request og response, dette gør det let at skelne mellem hvilke data vi får ind når der skal oprettes noget i databasen og hvilke 
  data som skal sendes ud til brugerne. alle crud end points som skal ned i databasen bruger request dto og dem som skal op fra databasen bruger response.  
  
-  What is the advantage of using DTOs to separate api from data structure when designing rest endpoints

  sikkerhed er en af fordelene fordi man kan skelne mellem hvilke informationer brugerne kan få ud af systemet. api'et bliver mere læsbart da man adskiller
  koden og får et mere tydeligt billede af hvad der sker under maskine rummet. 
  
- Explain shortly the concept mocking in relation to software testing

  når man mocker er der ikke snak om en decideret database man efterligner bare en, dette gør test meget mere effektive og hurtige.
  Mockning i softwaretestning refererer til oprettelsen af simuleringer eller erstatninger for reelle objekter eller tjenester, som en softwarekomponent 
  interagerer med. Dette gøres for at isolere den pågældende komponent og teste dens funktionalitet uafhængigt af de andre komponenter
  
- How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code

  jeg har både brugt h2 og mockito test i service laget
  
- Explain the concept Build Server and the role Github Actions play here

  En build-server er en server eller en tjeneste, der automatisk bygger (kompilerer og pakker) softwareprojekter på en konsistent måde og kører tests for at 
  sikre, at koden fungerer som forventet. Bygge-servere er afgørende for Continuous Integration (CI) og Continuous Deployment (CD) processer, da de sikrer, 
  at ændringer i koden integreres og valideres regelmæssigt.
  github actions ser om koden kan bygges hvis den kan det bliver jar filen sendt videre til azure som tager sig af deployment
  
- Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)

  Maven bruges til at kompilere projektet og oprette en distribuerbar pakke, f.eks. en JAR-fil
  Maven kan bruges til at oprette deployerbare artefakter, som derefter kan bruges til deployment eller distribution.
  maven bruges til at administrere afhængigheder, bygge og konfigurere koden. 
  
-  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin

  azure er infrastruktur som service og database som en service
  github er software som en service

  
