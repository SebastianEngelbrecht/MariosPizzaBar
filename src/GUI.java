import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GUI {

    private JFrame frame = new JFrame();
    private JLabel headerLabel = new JLabel();
    private ImageIcon marioIcon = new ImageIcon("Mario_Icon.png");
    private JPanel buttons = new JPanel();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JTextPane centerPanelLeft = new JTextPane();
    private JTextPane centerPanelRight = new JTextPane();
    private BorderLayout mainPanelLayoutManager = new BorderLayout(0, 0);
    private BorderLayout centerPanelLayoutManager = new BorderLayout();
    private GridLayout buttonsLayoutManager = new GridLayout(9, 1, 0, 5);
    private static MenuCard menuCard;
    private static Product[] list = null;
    private Color mainColor = Color.white;
    private Color centerPanelTextColor;
    private Color fileMenuTextColor;
    private Color fileMenuBackgroundColor;
    private Color lineBorderColor;
    private Color secondaryBorderColor;
    private Color menuCardButtonColor;
    private Color orderButtonColor;
    private Color financesButtonColor;
    private Color maintenanceButtonColor;
    private Color quitButtonColor;
    private Color themeMenuBackgroundColor;
    private Color themeMenuTextColor;
    private boolean themeDark;
    private boolean themeLight;
    private JButton menuCardButton = new JButton("1. MENU CARD");
    private JButton orderButton = new JButton("2. ORDER");
    private JButton financesButton = new JButton("3. FINANCES");
    private JButton maintenanceButton = new JButton("4. MAINTENANCE");
    private JButton quitButton = new JButton("5. QUIT");
    private JMenu fileMenu = new JMenu(" File ");
    private JMenu themeMenu = new JMenu(" Themes ");


    public GUI() {
        menuCard = new MenuCard();
        menuCard.loadCard();
        list = menuCard.getProductList();
        lookAndFeel();
        savedTheme();
        menuSetup();
        colorTheme();
        frameSetup();
        mainPanel();
        centerPanelSetup();
        mainMenuButtons();
    }

    private void lookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {
            System.err.println("Look and feel not set.");
        }
    }

    private void savedTheme() {
        String content = null;
        String theme = "theme";
        String light = "light";
        String dark = "dark";
        String enabled = "true";
        try {
            FileReader readTheme = new FileReader("Theme.txt");
            Scanner scanTheme = new Scanner(readTheme);
            while (scanTheme.hasNext()) {
                content = scanTheme.nextLine();
                System.out.println(content);
                if (content.toLowerCase().contains(theme.toLowerCase())) {
                    if (content.toLowerCase().contains(light)) {
                        if (content.toLowerCase().contains(enabled)) {
                            themeLight = true;
                            themeDark = false;
                        }
                    } else if (content.toLowerCase().contains(dark)) {
                        if (content.toLowerCase().contains(enabled)) {
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
    }

    private void colorTheme() {
    if (themeDark) {
        this.mainColor = new Color(43, 43, 43);
        this.centerPanelTextColor = Color.WHITE;
        this.fileMenuTextColor = Color.WHITE;
        this.fileMenuBackgroundColor = mainColor;
        this.lineBorderColor = Color.WHITE;
        this.secondaryBorderColor = mainColor;
        this.menuCardButtonColor = Color.WHITE;
        this.orderButtonColor = Color.WHITE;
        this.financesButtonColor = Color.WHITE;
        this.maintenanceButtonColor = Color.WHITE;
        this.quitButtonColor = Color.WHITE;
        this.themeMenuBackgroundColor = mainColor;
        this.themeMenuTextColor = Color.WHITE;
        } else if (themeLight) {
        this.mainColor = Color.WHITE;
        this.centerPanelTextColor = Color.BLACK;
        this.fileMenuTextColor = Color.BLACK;
        this.fileMenuBackgroundColor = mainColor;
        this.lineBorderColor = Color.BLACK;
        this.secondaryBorderColor = Color.WHITE;
        this.menuCardButtonColor = Color.WHITE;
        this.orderButtonColor = Color.WHITE;
        this.financesButtonColor = Color.WHITE;
        this.maintenanceButtonColor = Color.WHITE;
        this.quitButtonColor = Color.WHITE;
        this.themeMenuBackgroundColor = mainColor;
        this.themeMenuTextColor = Color.BLACK;
        } else {
        this.mainColor = Color.WHITE;
        this.centerPanelTextColor = Color.BLACK;
        this.fileMenuTextColor = Color.BLACK;
        this.fileMenuBackgroundColor = mainColor;
        this.lineBorderColor = Color.BLACK;
        this.secondaryBorderColor = Color.WHITE;
        this.menuCardButtonColor = Color.WHITE;
        this.orderButtonColor = Color.WHITE;
        this.financesButtonColor = Color.WHITE;
        this.maintenanceButtonColor = Color.WHITE;
        this.quitButtonColor = Color.WHITE;
        this.themeMenuBackgroundColor = mainColor;
        this.themeMenuTextColor = Color.BLACK;
        }
        menuBar.setBackground(mainColor);
        menuCardButton.setBackground(menuCardButtonColor);
        orderButton.setBackground(orderButtonColor);
        financesButton.setBackground(financesButtonColor);
        maintenanceButton.setBackground(maintenanceButtonColor);
        quitButton.setBackground(quitButtonColor);
        mainPanel.setBackground(mainColor);
        leftPanel.setBackground(mainColor);
        buttons.setBackground(leftPanel.getBackground());
        rightPanel.setBackground(mainColor);
        centerPanel.setBackground(mainColor);
        bottomPanel.setBackground(mainColor);
        centerPanelLeft.setBackground(mainColor);
        centerPanelRight.setBackground(mainColor);
        fileMenu.setBackground(fileMenuBackgroundColor);
        fileMenu.setForeground(fileMenuTextColor);
        themeMenu.setBackground(themeMenuBackgroundColor);
        themeMenu.setForeground(themeMenuTextColor);
    }

    private void menuSetup() {

        JMenuItem exitItem = new JMenuItem("1. Exit");
        JMenuItem lightTheme = new JMenuItem("1. Light theme");
        JMenuItem darkTheme = new JMenuItem("2. Dark theme");

        themeMenu.add(lightTheme);
        themeMenu.add(darkTheme);
        themeMenu.setForeground(fileMenuTextColor);

        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        lightTheme.addActionListener(e -> {

            themeDark = false;
            themeLight = true;
            colorTheme();
            centerPanelLeft.setText("");
            centerPanelRight.setText("");

        });


        darkTheme.addActionListener(e -> {

            themeLight = false;
            themeDark = true;
            colorTheme();
            centerPanelLeft.setText("");
            centerPanelRight.setText("");
        });


        fileMenu.add(exitItem);
        fileMenu.setForeground(fileMenuTextColor);

        menuBar.setOpaque(true);
        menuBar.add(fileMenu);
        menuBar.add(themeMenu);
        menuBar.setBorder(BorderFactory.createCompoundBorder());
        menuBar.setBackground(mainColor);

        exitItem.setOpaque(true);

        frame.setJMenuBar(menuBar);
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

        menuCardButton.addActionListener(showMenuCard);
        menuCardButton.setBackground(menuCardButtonColor);
        menuCardButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(menuCardButton);
        buttons.add(menuCardButton);



        orderButton.addActionListener(e -> System.out.println("2. ORDER"));
        orderButton.setBackground(Color.WHITE);
        orderButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(orderButton);
        buttons.add(orderButton);



        financesButton.addActionListener(e -> System.out.println("3. FINANCES"));
        financesButton.setBackground(Color.WHITE);
        financesButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(financesButton);
        buttons.add(financesButton);


        maintenanceButton.addActionListener(e -> System.out.println("4. MAINTENANCE"));
        maintenanceButton.setBackground(Color.WHITE);
        maintenanceButton.setBorder(BorderFactory.createRaisedBevelBorder());
        buttonPress(maintenanceButton);
        buttons.add(maintenanceButton);


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
        mainPanel.setBorder(BorderFactory.createEtchedBorder(lineBorderColor, secondaryBorderColor));

        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(110, 200));
        leftPanel.setBorder(BorderFactory.createEtchedBorder(lineBorderColor, secondaryBorderColor));

        mainPanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.setPreferredSize(new Dimension(10, 100));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(lineBorderColor, secondaryBorderColor));

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 10));
        bottomPanel.setBorder(BorderFactory.createEtchedBorder(lineBorderColor, secondaryBorderColor));

        leftPanel.setBackground(mainColor);
        rightPanel.setBackground(mainColor);
        centerPanel.setBackground(mainColor);
        bottomPanel.setBackground(mainColor);
    }

    private void centerPanelSetup() {
        centerPanel.setLayout(centerPanelLayoutManager);

        centerPanel.add(centerPanelLeft, BorderLayout.WEST);
        centerPanel.add(centerPanelRight, BorderLayout.EAST);

        centerPanelLeft.setOpaque(true);
        centerPanelLeft.setBackground(mainColor);
        centerPanelLeft.setEditable(false);

        centerPanelRight.setOpaque(true);
        centerPanelRight.setBackground(mainColor);
        centerPanelRight.setEditable(false);
    }

    ActionListener showMenuCard = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String leftLine = null;
            String rightLine = null;

            centerPanelLeft.setEditable(true);
            centerPanelLeft.setText("");
            String leftResult = "";

            centerPanelRight.setEditable(true);
            centerPanelRight.setText("");
            String rightResult = "";

            for (int i = 0; i < menuCard.getProductSize(); i++) {
                leftLine = ((1 + i) + " " + list[i].getName() + " -- " + list[i].getDescription());
                leftResult += leftLine + "\n";
            }
            for (int i = 0; i < menuCard.getProductSize(); i++) {
                rightLine = ((list[i].getPrice() + "kr."));
                rightResult += rightLine + "\n";
            }

            centerPanelLeft.setText(leftResult);
            centerPanelLeft.setFont(new Font("Comic sans", Font.PLAIN, 12));
            centerPanelLeft.setForeground(centerPanelTextColor);
            centerPanelLeft.setEditable(false);

            centerPanelRight.setText(rightResult);
            centerPanelRight.setFont(new Font("Comic sans", Font.PLAIN, 12));
            centerPanelRight.setForeground(centerPanelTextColor);
            centerPanelRight.setEditable(false);
        }

    };
}

