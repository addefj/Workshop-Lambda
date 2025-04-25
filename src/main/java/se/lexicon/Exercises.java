package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	TODO: Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = person -> person.getFirstName().equalsIgnoreCase("Erik");
        System.out.println(DataStorage.INSTANCE.findMany(filter));

        System.out.println("----------------------");
    }

    /*
        2.	TODO: Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        //Write your code here

        Predicate<Person> filter = person -> person.getGender() == Gender.FEMALE;
        System.out.println(DataStorage.INSTANCE.findMany(filter));

        System.out.println("----------------------");
    }

    /*
        3.	TODO: Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = person -> person.getBirthDate().isAfter(LocalDate.parse("1999-12-31"));
        System.out.println(DataStorage.INSTANCE.findMany(filter));

        System.out.println("----------------------");
    }

    /*
        4.	TODO: Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 123;
        System.out.println(DataStorage.INSTANCE.findOne(filter));

        System.out.println("----------------------");
    }

    /*
        5.	TODO: Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 456;
        Function<Person, String> mapToString = person -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate();
        System.out.println(DataStorage.INSTANCE.findOneAndMapToString(filter, mapToString));

        System.out.println("----------------------");
    }

    /*
        6.	TODO: Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filterGender = person -> person.getGender() == Gender.MALE;
        Predicate<Person> filterNameStartsWithE = person -> person.getFirstName().startsWith("E");
        Predicate<Person> filterCombinedGenderAndName = filterGender.and(filterNameStartsWithE);
        Function<Person, String> mapToString = Person::toString;
        System.out.println(DataStorage.INSTANCE.findManyAndMapEachToString(filterCombinedGenderAndName, mapToString));

        System.out.println("----------------------");
    }

    /*
        7.	TODO: Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = person -> Period.between(person.getBirthDate(), LocalDate.now()).getYears() < 10;
        Function<Person, String> mapToString = person -> "Name: "
                + person.getFirstName() + " " + person.getLastName() + " "
                + Period.between(person.getBirthDate(), LocalDate.now()).getYears() + " years";

        List<String> myList = DataStorage.INSTANCE.findManyAndMapEachToString(filter, mapToString);
            for (String person: myList)
                System.out.println(person);

        System.out.println("----------------------");
    }

    /*
        8.	TODO: Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = person -> person.getFirstName().equalsIgnoreCase("Ulf");
        Consumer<Person> print = person -> System.out.println(person.toString());
        DataStorage.INSTANCE.findAndDo(filter, print);

        System.out.println("----------------------");
    }

    /*
        9.	TODO: Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = person -> person.getLastName().toLowerCase().contains(person.getFirstName().toLowerCase());
        Consumer<Person> print = person -> System.out.println(person.toString());
        DataStorage.INSTANCE.findAndDo(filter, print);

        System.out.println("----------------------");
    }

    /*
        10.	TODO: Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = person -> person.getFirstName().equalsIgnoreCase(new StringBuilder(person.getFirstName()).reverse().toString());
        Consumer<Person> print = person -> System.out.println(person.getFirstName() + " " + person.getLastName());
        DataStorage.INSTANCE.findAndDo(filter, print);

        System.out.println("----------------------");
    }

    /*
        11.	TODO: Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filterNameStartsWithA = person -> person.getFirstName().startsWith("A");
        Comparator<Person> sortByBirthDate = (p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate());

        List<Person> sortedList = DataStorage.INSTANCE.findAndSort(filterNameStartsWithA, sortByBirthDate);
        for (Person person: sortedList)
            System.out.println(person);

        System.out.println("----------------------");
    }

    /*
        12.	TODO: Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filterBornBefore1950 = person -> person.getBirthDate().getYear() < 1950;
        Comparator<Person> sortByBirthYear = (p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate());

        List<Person> sortedList = DataStorage.INSTANCE.findAndSort(filterBornBefore1950, sortByBirthYear.reversed());
        for (Person person: sortedList)
            System.out.println(person);

        System.out.println("----------------------");
    }

    /*
        13.	TODO: Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here

        Comparator<Person> lastNameComparator = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());
        Comparator<Person> firstNameComparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        Comparator<Person> birthDateComparator = (p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate());
        Comparator<Person> combinedComparator = lastNameComparator.thenComparing(firstNameComparator).thenComparing(birthDateComparator);


        List<Person> sortedList = DataStorage.INSTANCE.findAndSort(combinedComparator);
        for (Person person: sortedList)
            System.out.println(person);


        System.out.println("----------------------");
    }

}