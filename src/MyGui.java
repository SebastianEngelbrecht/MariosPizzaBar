import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyGui {

    private JFrame frame = new JFrame();
    private JLabel headerLabel = new JLabel();
    private ImageIcon marioIcon = new ImageIcon("Mario_Icon.png");
    private Color mainColor = new Color(43, 43, 43);
    private JPanel buttons = new JPanel();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel headerPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JTextPane centerPanel = new JTextPane();
    private JPanel bottomPanel = new JPanel();
    private BorderLayout mainPanelLayoutManager = new BorderLayout(10, 0);
    private GridLayout buttonsLayoutManager = new GridLayout(9, 1, 0, 5);

    private String dataCollection;
    private ArrayList<Product> productsList = new ArrayList<>();


    private static MenuCard menuCard;


    public MyGui() {
        menuCard = new MenuCard();

        frameSetup();
        headerSetup();
        mainPanel();
        mainMenuButtons();

    }

    public void show() {
        frame.setVisible(true);
    }

    private void frameSetup() {
        frame.setTitle("Mario's Pizzabar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
        frame.setIconImage(marioIcon.getImage());
        frame.add(mainPanel);
        frame.pack();
        frame.setSize(1200, 800);
    }

    private void mainMenuButtons() {

        buttons.setLayout(buttonsLayoutManager);

        JButton menuCardButton = new JButton("1. MENU CARD");

//        menuCardButton.addActionListener(e -> System.out.println("1. MENU CARD"));
        menuCardButton.addActionListener(e -> {
            centerPanel.setEditable(true);
            centerPanel.setText("");
            String fileResult = "";
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader("PizzaList.csv"));
                String line = null;
                csvReader.readLine();
                while ((line = csvReader.readLine()) != null) {
                    Product[] list = menuCard.getProductList();
                    //Do your logic here which information you want to parse from the csv file and which information you want to display in your textpane
                    dataCollection += line + "//";
                    for (String data: dataCollection.split("//")) {
                        String[] fromData = data.split(";");

                        if(fromData[0].charAt(0) == 'n')
                            fromData[0] = fromData[0].split("null")[1];

                        Product toAdd = new Product(fromData[0],fromData[1],fromData[2],fromData[3]);
                        productsList.add(toAdd);
                    }
                    for (int i = 0; i < productsList.size() - 1; i++) {
                        line = ((1 + i) + " " + list[i].getName() + " -- " + list[i].getDescription() + " " + list[i].getPrice() + "kr.");
                        fileResult += line + "\n";
                    }
                }
            }
            catch(FileNotFoundException ex) {
                System.err.println("File was not found");

            }
            catch(IOException ioe) {
                System.err.println("There was an error while reading the file");
            }
            centerPanel.setText(fileResult);
            centerPanel.setEditable(false);
        });
        menuCardButton.setBackground(Color.white);
        menuCardButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(menuCardButton);
        buttons.add(menuCardButton);


        JButton orderButton = new JButton("2. ORDER");
        orderButton.addActionListener(e -> System.out.println("2. ORDER"));
        orderButton.setBackground(Color.WHITE);
        orderButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(orderButton);
        buttons.add(orderButton);


        JButton financesButton = new JButton("3. FINANCES");
        financesButton.addActionListener(e -> System.out.println("3. FINANCES"));
        financesButton.setBackground(Color.WHITE);
        financesButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(financesButton);
        buttons.add(financesButton);

        JButton maintenanceButton = new JButton("4. MAINTENANCE");
        maintenanceButton.addActionListener(e -> System.out.println("4. MAINTENANCE"));
        maintenanceButton.setBackground(Color.WHITE);
        maintenanceButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(maintenanceButton);
        buttons.add(maintenanceButton);

        JButton quitButton = new JButton("5. QUIT");
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setBackground(Color.WHITE);
        quitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(quitButton);
        buttons.add(quitButton);


        buttons.setBackground(leftPanel.getBackground());
        leftPanel.add(buttons);
    }

    private void buttonPress(JButton button) {
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBorder(BorderFactory.createRaisedBevelBorder());
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void mainPanel() {
        mainPanel.setBackground(mainColor);
        mainPanel.setLayout(mainPanelLayoutManager);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        headerPanel.setPreferredSize(new Dimension(100, 50));
        headerPanel.setBorder(BorderFactory.createEtchedBorder());

        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(110, 200));

        leftPanel.setBorder(BorderFactory.createEtchedBorder());


        mainPanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.setPreferredSize(new Dimension(100, 100));
        rightPanel.setBorder(BorderFactory.createEtchedBorder());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(100, 100));
        centerPanel.setBorder(BorderFactory.createEtchedBorder());
        centerPanel.setEditable(false);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());

        headerPanel.setBackground(mainColor);
        leftPanel.setBackground(mainColor);
        rightPanel.setBackground(mainColor);
        centerPanel.setOpaque(true);
        bottomPanel.setBackground(mainColor);
    }

    private void headerSetup() {
        headerLabel.setText("Marios Pizzabar");
        headerLabel.setFont(new Font("Comic Sans", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
    }
}

