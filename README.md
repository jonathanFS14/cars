# cars project 

- The idea with, and reasons for why to use, a ORM-mapper
  
- The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected
   
- How to create simple Java entities and map them to a database via the Spring Data API

  ved at bruge entity annoteringen fortæller jeg spring data jpa at klassen skal være en entitet som skal mappes i min database.
   
- How to control the mapping between individual fields in an Entity class and their matching columns in the database

  ved at bruge column annoteringen kan man fortælle spring data jpa at dette specifike fields skal mappes i databasen, derudover har annoteringen nogle argumenter.
  disse argumenter kan sørge for hvor lang en input må være og om det er lovligt at gøre den nullable osv. 
   
- How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)

når vi bruger spring data jpa skal vi bruge id annoteringen for et field for at fortælle jpa at det field annoteringen er ovenover skal være primary key for det specifikke table.
id kan også spille sammen med en anden annotering kaldet generated value, dette er dog ikke nødvendigt og id annoteringen skal heller ikke være over at field kaldet id. 
   
- How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern


   
- How to write simple "integration" tests, using H2 as a mock-database instead of MySQL
 
- How to add (dev) connection details for you local MySQL database
   
