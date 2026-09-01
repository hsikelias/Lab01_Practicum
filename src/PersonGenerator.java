import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        String id = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int yearOfBirth = 0;
        boolean done = false;
        String rec;

        ArrayList<String> recs = new ArrayList<>();

        do
        {
            id = SafeInput.getRegExString(
                    in,
                    "Enter the ID",
                    "\\d{6}"
            );

            firstName = SafeInput.getNonZeroLenString(
                    in,
                    "Enter the first name"
            );

            lastName = SafeInput.getNonZeroLenString(
                    in,
                    "Enter the last name"
            );

            title = SafeInput.getNonZeroLenString(
                    in,
                    "Enter the title"
            );

            yearOfBirth = SafeInput.getInt(
                    in,
                    "Enter the year of birth"
            );

            rec = id + ", " +
                    firstName + ", " +
                    lastName + ", " +
                    title + ", " +
                    yearOfBirth;

            recs.add(rec);

            System.out.println("\nFinal record added:");
            System.out.println(rec);

            done = SafeInput.getYNConfirm(
                    in,
                    "Done entering people?"
            );

        } while (!done);

        String fileName = SafeInput.getNonZeroLenString(
                in,
                "Enter the file name"
        );

        File workingDirectory =
                new File(System.getProperty("user.dir"));

        Path file = Paths.get(
                workingDirectory.getPath() + "\\" + fileName
        );

        try
        {
            OutputStream out =
                    new BufferedOutputStream(
                            Files.newOutputStream(file, CREATE)
                    );

            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(out)
                    );

            for (String r : recs)
            {
                writer.write(r, 0, r.length());
                writer.newLine();
            }

            writer.close();

            System.out.println("\nData file written successfully!");
            System.out.println("Saved as: " + fileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        in.close();
    }
}