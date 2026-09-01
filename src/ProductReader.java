import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;

public class ProductReader
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
                                "%-10s %-15s %-30s %-10s",
                                "ID#",
                                "Name",
                                "Description",
                                "Cost"
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
                    String name = fields[1].trim();
                    String description = fields[2].trim();
                    String cost = fields[3].trim();

                    System.out.println(
                            String.format(
                                    "%-10s %-15s %-30s %-10s",
                                    id,
                                    name,
                                    description,
                                    cost
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