import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class GUITest {

    private boolean themeDark;
    private boolean themeLight;
    Path path = Paths.get("Test/Resources/Theme.txt");

    @Test
    public void completeTest() {
        updateTheme();
        colorThemeTest();
        String expected = "Dark theme: " + false + " Light theme: " + true;
        String actual = "Dark theme: " + themeDark + " Light theme: " + themeLight;
        assertEquals(expected, actual);
    }

//    @Test
    public void colorThemeTest() {
        String content = null;
        String theme = "theme";
        String light = "light";
        String dark = "dark";
        String isTrue = "true";
        try {
            FileReader readTheme = new FileReader(String.valueOf(path));
            Scanner scanTheme = new Scanner(readTheme);
            while (scanTheme.hasNextLine()) {
                content = scanTheme.nextLine();
                System.out.println(content);
                if (content.toLowerCase().contains(theme.toLowerCase())) {
                    if (content.toLowerCase().contains(light)) {
                        if (content.toLowerCase().contains(isTrue)) {
                            themeLight = true;
                            themeDark = false;
                        }
                    } else if (content.toLowerCase().contains(dark)) {
                        if (content.toLowerCase().contains(isTrue)) {
                            themeDark = true;
                            themeLight = false;
                        }
                    }
                }
            }
            scanTheme.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }
//        String expected = "Dark theme: " + true + " Light theme: " + false;
//        String actual = "Dark theme: " + themeDark + " Light theme: " + themeLight;
//        assertEquals(expected, actual);
    }
//    @Test
    public void updateTheme() {
        try {

            System.out.println(path.toAbsolutePath());
            Stream<String> lines = Files.lines(path);
            Stream<String> lines2 = Files.lines(path);
            Stream<String> lines3 = Files.lines(path);

            List<String> temp = lines.map(line -> line.replaceAll("true", "barxxd")).collect(Collectors.toList());
            Files.write(path, temp);
            List<String> replaced = lines2.map(line -> line.replaceAll("false", "true")).collect(Collectors.toList());
            Files.write(path, replaced);
            List<String> replaceTemp = lines3.map(line -> line.replaceAll("barxxd", "false")).collect(Collectors.toList());
            Files.write(path, replaceTemp);

            lines.close();

            System.out.println("Find and Replace done!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        colorThemeTest();
//        String expected = "Dark theme: " + false + " Light theme: " + true;
//        String actual = "Dark theme: " + themeDark + " Light theme: " + themeLight;
//        assertEquals(expected, actual);
    }

//    @Test
    public void test () {
        String filename="Test/Resources/Theme.txt";
        Path pathToFile = Paths.get(filename);
        System.out.println(pathToFile.toAbsolutePath());
    }
}