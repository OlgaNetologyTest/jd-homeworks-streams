package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    String name;
    int age;
    String sex;
    String education;

    public Person(String name, int age, String sex, String education) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.education = education;

    }

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Jack Evans", 25, "Male", "Bachelor"),
                new Person("Connor Young", 30, "Male", "Master"),
                new Person("Harry Harris", 16, "Female", "High School"),
                new Person("John Davies", 22, "Male", "Bachelor")
        );

        // Найти количество несовершеннолетних

        long minorsCount = people.stream().filter(person -> person.age < 18).count();
        System.out.println("Количество несовершеннолетних: " + minorsCount);

        // Получить список фамилий призывников

        List<String> conscriptSurnames = people.stream()
                .filter(person -> person.sex.equals("Male") && person.age >= 18 && person.age <= 27)
                .map(person -> person.name.split(" ")[1])
                .collect(Collectors.toList());

        System.out.println("Фамилий призывников: " + conscriptSurnames);

        // Получить отсортированный по фамилии список работоспособных людей с высшим образованием

        List<Person> employablePeople = people.stream()
                .filter(person -> person.education.equals("Bachelor") || person.education.equals("Master"))
                .filter(person -> {
                    if (person.sex.equals("Female")) {
                        return person.age >= 18 && person.age <= 60;
                    } else {
                        return person.age >= 18 && person.age <= 65;
                    }
                })
                .sorted((p1, p2) -> p1.name.split(" ")[1].compareTo(p2.name.split(" ")[1]))
                .collect(Collectors.toList());

        System.out.println("Отсортированный список работоспособных людей с высшим образованием: ");
        employablePeople.forEach(person -> System.out.println(person.name));
    }
}