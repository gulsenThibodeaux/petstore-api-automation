package gulproject.testdata;

import gulproject.models.Pet;

public class PetTestData {

    public static Pet utkuTheWolf() {
        return new Pet(77777, "Utku", "available");
    }

    public static Pet buddyTheDog() {
        return new Pet(88888, "Buddy", "available");
    }

    public static Pet lunaTheCat() {
        return new Pet(99999, "Luna", "pending");
    }

    // For dynamic test data - random ID, custom name/status
    public static Pet customPet(String name, String status) {
        long randomId = (long)(Math.random() * 900000) + 100000;
        return new Pet(randomId, name, status);
    }
}