package Globals;

import java.util.Random;

public class Globals {
    public static String browserType="chrome";
    public static String homePageUrl = "https://demo.nopcommerce.com/";
    public static String loginPageUrl="https://demo.nopcommerce.com/login?returnUrl=%2F";
    public static String registerPageUrl="https://demo.nopcommerce.com/register?returnUrl=%2F";

    public static String dashboardPageUrl="https://demo.nopcommerce.com/cell-phones";
    public static String fullName = "Arbi Topi";

    public static String email =generateRandomString(4)+ "@gmail.com";

    public static String password  = generateRandomString(6);

    public static String gender ="Male";

    public static final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    public static final String USER = "sa";
    public static final String PASSWORD = "";

    public static String generateRandomString(int length) {
        // Define characters to include in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Initialize random object
        Random random = new Random();

        // Create StringBuilder to store the random string
        StringBuilder sb = new StringBuilder(length);

        // Generate random string by appending random characters from the characters string
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        // Convert StringBuilder to String and return
        return sb.toString();
    }
}