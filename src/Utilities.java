import java.util.Scanner;

public class Utilities {
    public static String username()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username: ");
        return input.nextLine();
    }
    public static String password()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter password: ");
        return input.nextLine();
    }
}
