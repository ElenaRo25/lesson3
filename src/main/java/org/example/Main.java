package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол):");
            String userInput = scanner.nextLine();

            String[] userDataArray = userInput.split("\\s+");
            if (userDataArray.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите все требуемые значения.");
            }

            String lastName = userDataArray[0];
            String firstName = userDataArray[1];
            String middleName = userDataArray[2];
            String birthDate = userDataArray[3];
            long phoneNumber = Long.parseLong(userDataArray[4]);
            char gender = userDataArray[5].charAt(0);

            if (!isValidDate(birthDate)) {
                throw new IllegalArgumentException("Неверный формат даты рождения. Используйте формат dd.mm.yyyy.");
            }

            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Неверный символ пола. Используйте f или m.");
            }

            UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            writeUserDataToFile(userData);

            System.out.println("Данные успешно сохранены в файл.");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean isValidDate(String date) {
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }

    private static void writeUserDataToFile(UserData userData) throws IOException {
        String fileName = userData.getLastName() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(userData.toString());
            writer.newLine();
        }
    }
}

class UserData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private long phoneNumber;
    private char gender;

    public UserData(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String toString() {
        return String.format("%s %s %s %s %d %c", lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }

    public String getLastName() {
        return lastName;
    }
}