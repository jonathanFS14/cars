package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DeveloperData implements ApplicationRunner {

    final CarRepository carRepository;
    final MemberRepository memberRepository;
    final ReservationRepository reservationRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String[] brands = {"Toyota", "Honda", "Ford", "Chevrolet", "Nissan", "Hyundai", "Volkswagen"};
        String[] models = {"Camry", "Civic", "Focus", "Malibu", "Altima", "Elantra", "Jetta"};
        double[] prices = {50.0, 60.0, 55.0, 65.0, 70.0, 45.0, 58.0};
        Random random = new Random();
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String brand = brands[random.nextInt(brands.length)];
            String model = models[random.nextInt(models.length)];
            double price = prices[random.nextInt(prices.length)];
            int discount = (random.nextInt(3) + 1) * 5 * 2; // Generates 10, 20, or 30
            carList.add(new Car(brand, model, price, discount));
        }
        carRepository.saveAll(carList);

        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("klaus123", "1234", "klaus@hotmail.dk", "klaus", "klausen", "klausvej12", "københavn", "2350"));
        memberList.add(new Member("bente123", "1234", "bente@hotmail.dk", "bente", "bentsen", "bentevej12", "københavn", "2350"));
        memberList.add(new Member("bo123", "1234", "bo@hotmail.dk", "bo", "boesen", "bovej12", "københavn", "2350"));
        memberList.add(new Member("lene123", "1234", "lene@hotmail.dk", "lene", "lenesen", "lenevej12", "københavn", "2350"));
        memberRepository.saveAll(memberList);

        List<Reservation> reservations = new ArrayList<>();
        Car car1 = new Car("b1", "m3", 100, 10);
        car1.setId(1);
        Car car2 = new Car();
        car2.setId(2);
        Car car3 = new Car();
        car3.setId(3);
        Member m1 = new Member("klaus123", "1234", "klaus@hotmail.dk", "klaus", "klausen", "klausvej12", "københavn", "2350");
        m1.addRole(Role.ADMIN);
        m1.addRole(Role.USER);
        memberRepository.save(m1);
        Member m2 = new Member();
        Member m3 = new Member();
        m2.setUsername("bente123");
        m3.setUsername("bo123");
        Reservation reservation1 = new Reservation(m1, car1, LocalDate.now(), LocalDate.now());
        Reservation reservation2 = new Reservation(m2, car2, LocalDate.now(), LocalDate.now());
        Reservation reservation3 = new Reservation(m3, car3, LocalDate.now(), LocalDate.now());
        Reservation reservation4 = new Reservation(m3, car1, LocalDate.now(), LocalDate.now());
        Reservation reservation5 = new Reservation(m1, car3, LocalDate.now(), LocalDate.now());
        Reservation reservation6 = new Reservation(m1, car2, LocalDate.now(), LocalDate.now());
        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);
        reservations.add(reservation4);
        reservations.add(reservation5);
        reservations.add(reservation6);
        reservationRepository.saveAll(reservations);

        setupUserWithRoleUsers();


    }

    @Autowired
    UserWithRolesRepository userWithRolesRepository;
    final String passwordUsedByAll = "test12";
    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user2.addRole(Role.ADMIN);
        user3.addRole(Role.USER);
        //user4.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }


}
