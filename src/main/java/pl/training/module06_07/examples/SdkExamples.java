package pl.training.module06_07.examples;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class SdkExamples {

    public static void main(String[] args) {
        var bigValue = BigDecimal.ONE;
        var otherBigValue = BigDecimal.valueOf(100.50);
        // new BigDecimal("1.0");
        var result = bigValue.add(otherBigValue);
        System.out.println(result);

        var bigInteger = BigInteger.ONE;

        var text = "a" + "b" + "c";
        var buffer = new StringBuilder() // StringBuffer()
                .append("a")
                .append("b")
                .append("c")
                .toString();

        // Data time

        // Old api
        var date = new Date();
        Calendar calendar = Calendar.getInstance();

        // New api
        LocalDate localDate = LocalDate.now();
        LocalDate birthday = LocalDate.of(1900, 10, 10);
        System.out.println("Today: " + localDate);
        System.out.println("Birthday: " + birthday);

        LocalTime now = LocalTime.now();
        LocalTime meeting = LocalTime.of(14, 15);
        System.out.println("Now: " + now);
        System.out.println("Meeting: " + meeting);

        LocalDateTime timestamp = LocalDateTime.now();
        LocalDateTime event = LocalDateTime.of(localDate, now);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Event: " + event);

        ZonedDateTime localTime = ZonedDateTime.now();
        ZonedDateTime newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Local time: " + localTime);
        System.out.println("New York time: " + newYorkTime);

        ZonedDateTime systemTime = ZonedDateTime.now();

        // Convert to a specific time zone (e.g., Tokyo)
        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoTime = systemTime.withZoneSameInstant(tokyoZone);
        System.out.println("System Time: " + systemTime);
        System.out.println("Tokyo Time: " + tokyoTime);

        Instant instant = Instant.now(); // UTC
        System.out.println("Instant: " + instant);

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 20);
        Duration duration = Duration.between(endTime, startTime);
        System.out.println("Work hours: " + duration.toHours());

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.now();
        Period period = Period.between(startDate, endDate);
        System.out.println("Work period: years: " + period.getYears() + " months: " + period.getMonths() + " days: " + period.getDays());
        System.out.println("Total days: " + ChronoUnit.DAYS.between(startDate, endDate));

        startDate.plusDays(2);
        startDate.plus(2, ChronoUnit.DAYS);
        startTime.isAfter(endTime);
        startDate.isBefore(endDate);

        // DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm:ss"); // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        System.out.println("Formatted time: " + formatter.format(timestamp));
        String dateText = "06-02-2025 / 14:44:47";
        LocalDateTime dataTime = LocalDateTime.parse(dateText, formatter);

        DateTimeFormatter customFormatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH, 2)
                .toFormatter();

        System.out.println("Custom Formatted Date: " + dataTime.format(customFormatter));

        Date legacyDate = Date.from(instant);
        Instant instantTimestamp = legacyDate.toInstant();
        System.out.println("Legacy Date: " + legacyDate);

        /*
        The main interfaces in the Java Collections Framework are:

        Collection: The root interface for most collections.
        List: An ordered collection (sequence) that allows duplicate elements.
        Set: A collection that does not allow duplicate elements.
        Queue: A collection used to hold elements prior to processing (typically follows FIFO order).

        Map: An object that maps keys to values, with no duplicate keys allowed.
        */

        /*
        A List is an ordered collection that can contain duplicate elements. Common implementations include:

        ArrayList: Provides fast random access and is backed by an array.
        LinkedList: Ideal for frequent insertion and deletion operations.
        */

        List<String> fruits = new LinkedList<>(); // new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Duplicate element is allowed
        fruits.remove("Apple");
        fruits.remove("Apple");

        // Iterate using for-each loop
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        /*
        A Set is a collection that does not allow duplicate elements. Common implementations include:

        HashSet: Offers constant-time performance for basic operations.
        LinkedHashSet: Maintains insertion order.
        TreeSet: Stores elements in a sorted order.
        */

        Set<String> countries = new LinkedHashSet<>(); // new HashSet<>();
        countries.add("USA");
        countries.add("Canada");
        countries.add("Mexico");
        countries.add("USA"); // Duplicate will not be added

        for (String country : countries) {
            System.out.println(country);
        }

        Set<Integer> numbers = new TreeSet<>();
        numbers.add(42);
        numbers.add(7);
        numbers.add(19);
        numbers.add(42);

        // Elements will be printed in sorted order
        for (Integer number : numbers) {
            System.out.println(number);
        }

        /*
          A Queue is used for holding elements prior to processing. It typically follows FIFO (First-In, First-Out) order. Common implementations include:

          LinkedList: Can function as a queue.
          PriorityQueue: Orders its elements according to their natural ordering or by a provided comparator.
        */

        Queue<String> queue = new LinkedList<>();
        queue.offer("Task1");
        queue.offer("Task2");
        queue.offer("Task3");

        // Process tasks in FIFO order
        while (!queue.isEmpty()) {
            System.out.println("Processing: " + queue.poll());
        }

        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(20);
        priorityQueue.offer(5);
        priorityQueue.offer(15);

        // Elements will be processed in natural (ascending) order
        while (!priorityQueue.isEmpty()) {
            System.out.println("Processing: " + priorityQueue.poll());
        }

        /*
        A Map is used to store key-value pairs. No duplicate keys are allowed. Common implementations include:

        HashMap: Offers fast performance for most operations.
        LinkedHashMap: Maintains insertion order.
        TreeMap: Orders keys based on their natural order or a provided comparator.
        */

        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("hello", 1);
        wordCounts.put("world", 2);
        wordCounts.put("java", 3);
        var value = wordCounts.get("hello");

        // Iterate using entrySet
        for (Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Map<String, String> dictionary = new TreeMap<>();
        dictionary.put("banana", "Another fruit");
        dictionary.put("apple", "A fruit");
        dictionary.put("carrot", "A vegetable");

        // Iterates in alphabetical order of keys
        for (Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        var iterator = countries.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        // Consumer<String> task = (String country) -> System.out.println(country);
        Consumer<String> task = System.out::println;
        countries.forEach(System.out::println);

        /*Arrays.asList("Alice", "Bob", "Charlie", "David")
                .stream()
                .forEach(System.out::println);*/

        Arrays.asList("Alice", "Bob", "Charlie", "David")
                .forEach(System.out::println);

        Stream<String> stream = Stream.of("One", "Two", "Three");

        System.out.println("--------------------------------------------------------");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        // Filter names that start with 'A' or 'D' and convert to uppercase
        names.stream()
                //.filter(name -> name.startsWith("A") || name.startsWith("D"))
                .filter(minLength(3))
                //.map(a -> a.toLowerCase())
                .map(SdkExamples::toLowerCase)
                //.forEach(name -> System.out.println(name));
                .sorted()
                .forEach(System.out::println);

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

        int sum = values.stream()
                //.reduce(0, (accumulator, element) -> accumulator + element);
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        values.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(number -> number * 2)
                .forEach(n -> System.out.println("Even number: " + n));

        // Create a list of Person objects
        List<Person> persons = Arrays.asList(
                new Person("Alice", 23, "New York"),
                new Person("Bob", 30, "Los Angeles"),
                new Person("Charlie", 28, "New York"),
                new Person("David", 35, "Los Angeles"),
                new Person("Eve", 40, "Chicago"),
                new Person("Frank", 28, "Chicago")
        );

        // 1. Group persons by city
        Map<String, List<Person>> personsByCity = persons.stream().collect(groupingBy(Person::getCity));
        System.out.println("Persons by City:");
        personsByCity.forEach((city, pList) -> System.out.println(city + ": " + pList));

        // 2. Calculate the average age for each city
        var averageAgeByCity = persons.stream()
                .collect(groupingBy(Person::getCity, averagingInt(Person::getAge)));
        System.out.println("\nAverage Age by City:");
        averageAgeByCity.forEach((city, avgAge) -> System.out.println(city + ": " + avgAge));

        // 3. Partition persons into those under 30 and those 30 or older
        var partitionedByAge = persons.stream().collect(partitioningBy(p -> p.getAge() < 30));
        System.out.println("\nPersons under 30:");
        partitionedByAge.get(true).forEach(System.out::println);
        System.out.println("Persons 30 or older:");
        partitionedByAge.get(false).forEach(System.out::println);

        // 4. Complex chaining: Filter persons in New York, sort them by age descending, and collect their names
        var namesInNYSortedByAge = persons.stream()
                .filter(p -> "New York".equals(p.getCity()))
                .sorted(comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .toList();
        System.out.println("\nNames in New York sorted by age (desc): " + namesInNYSortedByAge);

        // A list of lists of integers
       var nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        // 5. Flatten the nested list into a single stream, filter even numbers, square them, and collect the results
        var squaredEvenNumbers = nestedList.stream()
                .flatMap(Collection::stream)             // Flatten the list-of-lists
                .filter(n -> n % 2 == 0)          // Keep only even numbers
                .map(n -> n * n)                  // Square each number
                .toList();   // Collect into a list

        System.out.println("Squared even numbers: " + squaredEvenNumbers);
    }

    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    public static Predicate<String> minLength(int length) {
        return text -> text.length() >= length;
    }

}

class Person {

    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return name + " (" + age + ") from " + city;
    }
}
