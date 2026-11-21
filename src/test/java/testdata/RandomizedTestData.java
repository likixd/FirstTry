package testdata;

import utils.RandomUtils;

public class RandomizedTestData {

    public RandomUtils randomutils = new RandomUtils();

    public String gender = randomutils.getRandomGender();
    public String firstName = randomutils.getFirstName();
    public String lastName = randomutils.getLastName();
    public String userEmail = randomutils.getRandomEmail();
    public String userNumber = randomutils.getRandomPhone();
    public String hobbies = randomutils.getRandomHobby();
    public String subjects = randomutils.getRandomSubjects();

    public String[] birthDate = randomutils.generateBirthDate();
    public String dateOfBirthDate = birthDate[0];
    public String dateOfBirthMonth = birthDate[1];
    public String dateOfBirthYear = birthDate[2];

    public String currentAddress = randomutils.getCurrentAddress();
    public String state = randomutils.getState();
    public String city = randomutils.getCity(state);

}
