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

  
  
- How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code

  
  
- Explain the concept Build Server and the role Github Actions play here

  
  
- Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)

  
  
-  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin

  
