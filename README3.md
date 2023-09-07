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

  

- How/where you have (if done) added user defined queries to you repositories

  

- a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase

  

- a few words about where you have used inheritance in your project, and how it's reflected in your database

  

- how are passwords stored in the database with the changes suggested in part-6 of the exercise

  

