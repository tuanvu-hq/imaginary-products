package com.imaginaryproducts.lib.product.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ProductRandomizer {
    private static final Random random = new Random();
    private static final Map<String, List<String>> productNameSetting = new HashMap<>() {{
        put("one", List.of("Zenith", "Nebula", "Quantum", "Serenity", "Celestial", "Horizon", "Odyssey", "Nova", "Eclipse", "Orion"));
        put("two", List.of("Radiance", "Aether", "Infinity", "Aurora", "Cosmos", "Stellar", "Pinnacle", "Genesis", "Epoch", "Ethereal"));
        put("three", List.of("Harmony", "Astral", "Synergy", "Nimbus", "Solstice", "Velocity", "Zen", "Vortex", "Quest", "Apex"));
        put("roman", List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"));
    }};
    private static final List<String> manufacturerNameSetting = List.of("Harmony", "Astral", "Synergy", "Nimbus", "Solstice", "Velocity", "Zen", "Vortex", "Quest", "Apex");

    /**
     * Generates a random product name. It takes a random name from each list.
     *
     * @return Returns a randomized product name.
     * */
    public static String generateName() {
        String one = productNameSetting.get("one").get(random.nextInt(10));
        String two = productNameSetting.get("two").get(random.nextInt(10));
        String three = productNameSetting.get("three").get(random.nextInt(10));
        String roman = productNameSetting.get("roman").get(random.nextInt(20));

        return String.format("%s %s %s %s", one, two, three, roman);
    }

    /**
     * Description randomizer. It will the following:
     * <ul>
     *  <li>with a description: "A simple description.". Emulates, that the products has a description.</li>>
     *  <li>without a description: Default is set to null. Emulates, that the products has no description.</li>
     * </ul>
     *
     * @return 50% chance to return a description.
     * */
    public static String generateDescription() {
        return random.nextBoolean() ? "A simple description." : null;
    }

    /**
     * Generates a random number.
     *
     * @param min Sets the minimum of the range.
     * @param max Sets the maximum of the range.
     * @param type Uses the {@link Double} or {@link Long} as a cast & return type.
     * @return Returns a random number.
     * */
    public static <T extends  Number> T generateNumber(double min, double max, Class<T> type) {
        double number = min + (max - min) * random.nextDouble();
        double parsed = Double.parseDouble(new DecimalFormat("#.##").format(number));

        return type == Double.class ? type.cast(parsed) : type.cast((long) parsed);
    }


    /**
     * Generates a random manufacturer name. It takes a random name from a list.
     *
     * @return Returns a randomized manufacturer name.
     * */
    public static String generateManufacturer() {
        return manufacturerNameSetting.get(random.nextInt(10));
    }

    /**
     * The function returns a random {@link Date}.
     * To change the range, configure start/end {@link LocalDate#toEpochDay()}.
     *
     * @return Returns a random {@link Date}.
     */
    public static Date generateManufacturingDate() {
        long startEpochDay = LocalDate.parse("2023-01-01").toEpochDay();
        long endEpochDay = LocalDate.parse("2023-12-31").toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
        LocalDate date =  LocalDate.ofEpochDay(randomEpochDay);

        return Date.valueOf(date);
    }

    /**
     * The function returns the current {@link Timestamp}.
     *
     * @return Returns a {@link Timestamp}.
     * */
    public static Timestamp generateAcquisitionDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);

        return Timestamp.valueOf(date);
    }

    /**
     * Creates a randomized {@link Product}.
     *
     * @return Returns a {@link Product}.
     * */
    public static Product build() {
        Product product = new Product();

        product.setId(UUID.randomUUID());
        product.setName(generateName());
        product.setDescription(generateDescription());
        product.setPrice(generateNumber(1.0, 999999.0, Double.class));
        product.setQuantity(generateNumber(1, 9999, Long.class));
        product.setManufacturer(generateManufacturer());
        product.setManufacturingDate(generateManufacturingDate());
        product.setAcquisitionDate(generateAcquisitionDate());
        product.setStatus(Status.values()[random.nextInt(3)]);

        return product;
    }
}

