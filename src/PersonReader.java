import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import javax.swing.JFileChooser;


/*

1.	Now create a new java main class in the same project called PersonReader.java.
2.	Be sure to use JFileChooser and SafeInput.
3.	Create a program that prompts the user to select an existing Person file and then displays the file to the screen.
4.	Use your PersonTestData.txt file to test and debug your program.
Use String.format to create a neatly formatted columnar display of the data records

ID#           Firstname     Lastname       Title    YOB
=====================================
000001     Bilbo              Baggins           Esq.    1060
000002     Frodo            Baggins           Esq.    1120
…

GET SCREENSHOTS OF:
- THE FILECHOOSER RUNNING
-	THE DISPLAY OF THE CHOOSEN FILE

*/


public class PersonReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();

            try
            {
                BufferedReader reader = Files.newBufferedReader(file);

                System.out.println(
                        String.format(
                                "%-10s %-15s %-15s %-10s %-6s",
                                "ID#",
                                "Firstname",
                                "Lastname",
                                "Title",
                                "YOB"
                        )
                );

                System.out.println(
                        "=========================================================="
                );

                String line;

                while ((line = reader.readLine()) != null)
                {
                    String[] fields = line.split(",");

                    String id = fields[0].trim();
                    String firstName = fields[1].trim();
                    String lastName = fields[2].trim();
                    String title = fields[3].trim();
                    String yearOfBirth = fields[4].trim();

                    System.out.println(
                            String.format(
                                    "%-10s %-15s %-15s %-10s %-6s",
                                    id,
                                    firstName,
                                    lastName,
                                    title,
                                    yearOfBirth
                            )
                    );
                }

                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}