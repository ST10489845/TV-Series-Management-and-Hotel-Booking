import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeriesApp {
    private static final List<SeriesModel> series = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            if (!showLaunchGate()) break;
            while (true) {
                String choice = showMenuAndReadChoice();
                switch (choice) {
                    case "1":
                        captureSeries();
                        break;
                    case "2":
                        searchSeries();
                        break;
                    case "3":
                        updateSeries();
                        break;
                    case "4":
                        deleteSeries();
                        break;
                    case "5":
                        printReport();
                        break;
                    case "6":
                        exitApplication();
                        break;
                    default:
                        continue;
                }
                break;
            }
        }
    }

    private static boolean showLaunchGate() {
        System.out.println();
        System.out.println("LATEST SERIES - 2025");
        System.out.println("********************************");
        System.out.print("Enter (1) to launch menu or any other key to exit\n> ");
        String s = in.nextLine();
        return "1".equals(s);
    }

    private static String showMenuAndReadChoice() {
        System.out.println();
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction.");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025.");
        System.out.println("(6) Exit Application.");
        System.out.print("> ");
        return in.nextLine();
    }

    private static void captureSeries() {
        System.out.println();
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("********************************");
        System.out.print("Enter the series id: ");
        String id = in.nextLine().trim();
        System.out.print("Enter the series name: ");
        String name = in.nextLine().trim();
        String age = promptForValidAge();
        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = in.nextLine().trim();
        SeriesModel m = new SeriesModel(id, name, age, episodes);
        series.add(m);
        System.out.println();
        System.out.println("Series processed successfully");
        pauseGate();
    }

    private static void searchSeries() {
        System.out.println();
        System.out.print("Enter the series id to search: ");
        String id = in.nextLine().trim();
        SeriesModel m = findSeriesById(id);
        System.out.println("----------------------------------------------");
        if (m != null) {
            System.out.println("SERIES ID: " + m.SeriesId);
            System.out.println("SERIES NAME: " + m.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + m.SeriesAge);
            System.out.println("SERIES NUMBER OF EPISODES: " + m.SeriesNumberOfEpisodes);
        } else {
            System.out.println("Series with Series Id: " + id + " was not found!");
        }
        System.out.println("----------------------------------------------");
        pauseGate();
    }

    private static void updateSeries() {
        System.out.println();
        System.out.print("Enter the series id to update: ");
        String id = in.nextLine().trim();
        SeriesModel m = findSeriesById(id);
        if (m == null) {
            System.out.println("----------------------------------------------");
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println("----------------------------------------------");
            pauseGate();
            return;
        }
        System.out.print("Enter the series name: ");
        m.SeriesName = in.nextLine().trim();
        m.SeriesAge = promptForValidAge();
        System.out.print("Enter the number of episodes: ");
        m.SeriesNumberOfEpisodes = in.nextLine().trim();
        System.out.println("Update completed.");
        pauseGate();
    }

    private static void deleteSeries() {
        System.out.println();
        System.out.print("Enter the series id to delete: ");
        String id = in.nextLine().trim();
        SeriesModel m = findSeriesById(id);
        System.out.println("----------------------------------------------");
        if (m == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println("----------------------------------------------");
            pauseGate();
            return;
        }
        System.out.println("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete.");
        System.out.print("> ");
        String confirm = in.nextLine().trim
