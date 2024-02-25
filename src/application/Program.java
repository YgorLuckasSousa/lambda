package application;

import entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);



        System.out.print("Enter full file path: ");
        String file = sc.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            List<Product> list = new ArrayList<>();
            String line = br.readLine();

            while (line != null){
                String [] fields = line.split(",");
                list.add(new Product(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();
            System.out.println("Email of people whose salary is more than 2000.00: ");

            Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> names = list.stream()
                    .filter(p -> p.getSalary() > salary)
                    .map(Product::getEmail)
                    .sorted(comp)
                    .collect(Collectors.toList());

            names.forEach(System.out::println);

            double sum = list.stream()
                    .filter(p -> p.getName().charAt(0) == 'M')
                    .map(Product::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));


        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}