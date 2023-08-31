- The idea with, and reasons for why to use, a ORM-mapper

  Object-relationel mapping (ORM), et system der kan bruges til at konvertere mellem objekter i et programmeringssprog (Java) og en relationel database (MySQL). Der findes mange forskellige ORMs, et 
  eksempel er Hibernate, et andet er EclipseLink. Fordelen ved at bruge et ORM er man slipper for at skrive en masse SQL for at læse og skrive mellem applikationen og databasen, og i stedet kan bruge 
  metoder som save().

- The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected

  Hibernate er som sagt et ORM system, men derudover, så er Hibernate en implementation af JPA specifikationen, hvilket vil sige Hibernate implementere de interfaces og klasser som du finder i 
  specifikationen. Hvorfor implementere JPA i stedet for Hibernate direkte? Fordi så kan du nemt skifte mellem forskellige ORM systemer. Både Hibernate og EclipseLink er JPA implementationer, så du kan 
  nemt skifte mellem dem.
   
- How to create simple Java entities and map them to a database via the Spring Data API

  ved at bruge entity annoteringen fortæller jeg spring data jpa at klassen skal være en entitet som skal mappes i min database.
   
- How to control the mapping between individual fields in an Entity class and their matching columns in the database

  ved at bruge column annoteringen kan man fortælle spring data jpa at dette specifike fields skal mappes i databasen, derudover har annoteringen nogle argumenter.
  disse argumenter kan sørge for hvor lang en input må være og om det er lovligt at gøre den nullable osv. 
   
- How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)

  når vi bruger spring data jpa skal vi bruge id annoteringen for et field for at fortælle jpa at det field annoteringen er ovenover skal være primary key for det specifikke table.
  id kan også spille sammen med en anden annotering kaldet generated value, dette er dog ikke nødvendigt og id annoteringen skal heller ikke være over at field kaldet id. 
   
- How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern

  Brug JpaRepository<ClassName, Primary Key> interface.
   
- How to write simple "integration" tests, using H2 as a mock-database instead of MySQL

  Brug @DataJpaTest (se https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html)
 
- How to add (dev) connection details for you local MySQL database

  Gem miljøvariabler, enten i IntelliJ eller gennem bash profile (hvis du er på mac eller linux), eller under miljøvariabler indstillinger på Windows.

   
