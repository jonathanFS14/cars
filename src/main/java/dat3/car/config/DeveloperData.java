package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DeveloperData implements ApplicationRunner {

    final CarRepository carRepository;
    final MemberRepository memberRepository;
    public DeveloperData (CarRepository carRepository, MemberRepository memberRepository){
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
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
        memberList.add(new Member("klaus123", "1234", "klaus@hotmail.dk", "klaus", "klausen",
                "klausvej12", "københavn", "2350"));
        memberList.add(new Member("bente123", "1234", "bente@hotmail.dk", "bente", "bentsen",
                "bentevej12", "københavn", "2350"));
        memberList.add(new Member("bo123", "1234", "bo@hotmail.dk", "bo", "boesen",
                "bovej12", "københavn", "2350"));
        memberList.add(new Member("lene123", "1234", "lene@hotmail.dk", "lene", "lenesen",
                "lenevej12", "københavn", "2350"));
        memberRepository.saveAll(memberList);
    }
}
