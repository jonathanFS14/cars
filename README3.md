- Where and why you have used a @OneToMany annotation

  jeg har brugt en til mange annotationen i car og member entity klasserne, sammen med mapped by.
  mapped by member f.eks kobler sig op på reservation member_username foreign key og ikke member entiteten.
  dette gør at jeg har angivet at en bil  eller bruger kan have mange reservationer men at en reservation kun er koblet op til en enkelt entitet.
  jeg har brugt fetchtyype eager, dette gør at når jeg vil have en entitet fra databasen kommer alle reservationer med.
  det syntes jeg er meget fint i forhold til at der ikke er alt for meget data, havde der været mange tusinde/millioner relationer ville lazy være
  mere oplagt. lazy gør at relationerne ikke bliver hentet med op fra databasen.  

- Where an why you have used a @ManyToOne annotation

  mange til en annotationen bruger jeg i reservation entiteten, dette siger bare det modsatte af en til mange.
  altså at mange reservationer kobler sig op på en entitet (car, member).
  mange til en annotationerne er foreign keys i entiteten og det gør at en reservation altid ved hvilken bil og bruger den er koblet til.

- The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many

  mappedBy-attributten bruges til at specificere, hvilket felt i den associerede entitet, der ejer forholdet. Det bruges i bidirektionelle forhold, 
  hvor den ejende side er ansvarlig for at styre forholdet.
  cascade type handler grundlæggene om hvordan ændringer i entiteten påvirker de associasrede entiteter.
  remove gør at hvis man sletter en member f.eks så bliver alle reservationer den er relateret til også slettet, dette er ofte en god ide
  da en reservation ikke er noget værd i mit system uden en bruger og dens foreign key peger på et objekt som ikke er der mere. det gør at man ikke har
  behov for lige så mange database kald. 
  persist gør at den gemmer alle relaterede entiteter, så hvis man f.eks. sendte en reservation med når man skulle oprette en ny bruger ville den også
  blive gemt automatisk i databasen uden at man behøvede at lave to kald til databasen.
  cascade type har ikke nogle default værdi. 

- How/where you have (if done) added user defined queries to you repositories

  i mine repository klasser, jeg har brugt jpa til at hjælpe mig med at bygge dem op.

- a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase

  deployment har fejlet de sidste par dage, jeg prøver at finde ud af hvorfor.
  // TODO 

- a few words about where you have used inheritance in your project, and how it's reflected in your database

  jeg har brugt arv via admin details klassen som har edited og created, reservation og car arver fra denne klasse. dette kan ses i databasen da
  begge entiteter har admin details fields i databasen.
  member entiteten arver fra userwithrole entiteten her arver den username, password og mail.

- how are passwords stored in the database with the changes suggested in part-6 of the exercise

  via userwithroles klassen bliver passwordet encodet, hvilket gør de er ulæselige i databasen og øger sikkerheden i programet.
  i constructoren skriver jeg ikke this.password = password fordi det ville gøre at passwordet bare ryger ned som det er.
  men istedet har jeg en set password metode hvor jeg encoder den med BCryptPasswordEncoder. 

    

