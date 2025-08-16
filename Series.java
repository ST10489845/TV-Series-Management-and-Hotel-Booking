package za.co.series;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Series {
    private List<SeriesModel> seriesList;

    public Series() {
        seriesList = new ArrayList<>();
        seriesList.add(new SeriesModel("101", "Extreme Sports", "12", "10"));
        seriesList.add(new SeriesModel("102", "Bargain Hunters", "10", "10"));
        seriesList.add(new SeriesModel("103", "Home Cooking", "10", "20"));
    }

    
    public List<SeriesModel> getSeriesList() { return seriesList; }

    public SeriesModel getById(String id) {
        return findById(id);
    }

    public boolean updateSeriesById(String id, String newName, String newAge, String newEpisodes) {
        SeriesModel s = findById(id);
        if (s == null) return false;
        if (!isValidAge(newAge)) return false;
        s.SeriesName = newName;
        s.SeriesAge = newAge;
        s.SeriesNumberOfEpisodes = newEpisodes;
        return true;
    }

    public boolean deleteSeriesById(String id) {
        SeriesModel s = findById(id);
        if (s == null) return false;
        return seriesList.remove(s);
    }

    public boolean isValidAge(String age) {
        if (age == null || !age.matches("\\d+")) return false;
        int n = Integer.parseInt(age);
        return n >= 2 && n <= 18;
    }

   
    private SeriesModel findById(String id) {
        for (SeriesModel s : seriesList) {
            if (s.SeriesId.equals(id)) return s;
        }
        return null;
    }

   
    public void menu() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\nSERIES MANAGER");
            System.out.println("(1) Search series");
            System.out.println("(2) Update series");
            System.out.println("(3) Delete series");
            System.out.println("(4) View all");
            System.out.println("(5) Exit");
            System.out.print("> ");
            String choice = in.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter Series ID: ");
                    String id = in.nextLine();
                    SeriesModel s = getById(id);
                    System.out.println(s != null ? "Found: " + s.SeriesName : "Not found.");
                    break;
                case "2":
                    System.out.print("ID: "); id = in.nextLine();
                    System.out.print("Name: "); String name = in.nextLine();
                    System.out.print("Age: "); String age = in.nextLine();
                    System.out.print("Episodes: "); String eps = in.nextLine();
                    System.out.println(updateSeriesById(id, name, age, eps) ? "Updated." : "Failed to update.");
                    break;
                case "3":
                    System.out.print("ID: "); id = in.nextLine();
                    System.out.println(deleteSeriesById(id) ? "Deleted." : "Not found.");
                    break;
                case "4":
                    for (SeriesModel sm : seriesList) {
                        System.out.println(sm.SeriesId + " - " + sm.SeriesName + " (" + sm.SeriesAge + "yrs, " + sm.SeriesNumberOfEpisodes + " eps)");
                    }
                    break;
                case "5": return;
                default: System.out.println("Invalid.");
            }
        }
    }
}
