package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {
    static Faker faker = new Faker();


    public static String getRandomGender () {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromStringArray(genders);

    }

    public static String getRandomHobby () {
        String[] genders = {"Sports", "Reading", "Music"};

        return getRandomItemFromStringArray(genders);

    }

    public static String getRandomSubjects () {
        String[] Subjects = {
                "Hindi", "English", "Maths", "Physics", "Chemistry",
                "Biology", "Computer Science", "Commerce", "Accounting",
                "Economics", "Arts", "Social Studies", "History", "Civics"
        };
        return getRandomItemFromStringArray(Subjects);
    }

    public static String getRandomString(int length) {
        String LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));

        return result.toString();
    }


    public static String getRandomEmail () {
        
        return format("%s@%s.com", getRandomString(8), getRandomString(5));

    }

    public static String getRandomItemFromStringArray (String[] stringArray) {
        int arrayLength = stringArray.length;
        int randomIndex = getRandomInt(0, arrayLength -1);

        return stringArray[randomIndex];
    }

    private static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }


    public static String getRandomPhone() {
        String phoneTemplate = "%s%s";

        return format(phoneTemplate,
                getRandomInt(11111, 99999), getRandomInt(11111, 99999));
    }

    public static String[] generateBirthDate() {
        Date date = faker.date().birthday(18, 65);

        return new String[]{
                new SimpleDateFormat("dd", Locale.ENGLISH).format(date),    // [0] день: "05"
                new SimpleDateFormat("MMMM", Locale.ENGLISH).format(date),  // [1] месяц: "May"
                new SimpleDateFormat("yyyy", Locale.ENGLISH).format(date)   // [2] год: "2003"
        };
    }


}


