import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class LoginJFrame extends JFrame {

    // For Sign In panel
    private JButton signInButton;
    private JTextField userEmailTextField;
    private JPasswordField userPasswordField;
    private JLabel signInLabel;

    // For Register panel
    private JButton signUpButton;
    private JLabel createAccountLabel;
    private JTextField userNameTextField;

    // Store references to the panels
    private JPanel signInPanel;
    private JPanel registerPanel;

    // for hover state ( hovering effect)
    private static boolean isMouseOutsideHover = false;

    public void showLoginJFrame() {
        setTitle("Clinic Management System - Login");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Use MigLayout for the main frame
        setLayout(new MigLayout("fill", "[grow][grow]", "[grow]"));

        // Create and store references to the panels
        signInPanel = createSignInPanel();
        registerPanel = createRegisterPanel();

        // Add the Sign In and Register panels
        add(signInPanel, "grow");
        add(registerPanel, "grow");
        try {
            ImageIcon iauLogo = new ImageIcon("iauClinicLogo.jpg");
            // Set up hover effects

            if (iauLogo == null || iauLogo.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("The logo not found or could not be loaded.");
            }
            setIconImage(iauLogo.getImage());
            setupHoverEffect(signInPanel, registerPanel, "New friend? Hello!", "iauIcon.png");
            setupHoverEffect(registerPanel, signInPanel, "Already have one? Welcome back!", "iauIcon.png");

        }catch (Exception e){
            System.out.println("An error occurred : " + e.getMessage());
            setIconImage(new ImageIcon("default_icon.png").getImage());
            setupHoverEffect(signInPanel, registerPanel, "New friend? Hello!", "default_icon.png");
            setupHoverEffect(registerPanel, signInPanel, "Already have one? Welcome back!", "default_icon.png");

        }
        setVisible(true);
    }

    private JPanel createSignInPanel() {
        // Create a panel with CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setBackground(new Color(51, 153, 255));

        // Create the Sign In panel
        JPanel signInPanel = new JPanel(new MigLayout("wrap, insets 20", "[grow]", "[grow][][][][][grow]"));
        signInPanel.setBackground(new Color(51, 153, 255));

        // Add components to the Sign In panel
        signInPanel.add(new JLabel(), "growy"); // Empty cell at the top

        signInLabel = new JLabel("Sign In");
        signInLabel.setForeground(Color.WHITE);
        signInLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signInPanel.add(signInLabel, "align center, gapbottom 20");

        userEmailTextField = new JTextField();
        userEmailTextField.putClientProperty("JTextField.placeholderText", "Enter your email");
        signInPanel.add(userEmailTextField, "growx, h 40!, gapbottom 10");

        userPasswordField = new JPasswordField();
        userPasswordField.putClientProperty("JTextField.placeholderText", "Enter your password");
        signInPanel.add(userPasswordField, "growx, h 40!, gapbottom 20");

        signInButton = new JButton("Sign In");
        signInPanel.add(signInButton, "growx, h 40!");

        signInPanel.add(new JLabel(), "growy"); // Empty cell at the bottom

        // Add the Sign In panel to the card panel
        cardPanel.add(signInPanel, "SignIn");

        return cardPanel;
    }


    private JPanel createRegisterPanel() {
        // Create a panel with CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setBackground(new Color(51, 153, 255));

        // Create the Register panel
        JPanel registerPanel = new JPanel(new MigLayout("wrap, insets 20", "[grow]", "[grow][][][][][][grow]"));
        registerPanel.setBackground(new Color(51, 153, 255));

        // Add components to the Register panel
        registerPanel.add(new JLabel(), "growy"); // Empty cell at the top

        createAccountLabel = new JLabel("Create new Account");
        createAccountLabel.setForeground(Color.WHITE);
        createAccountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        registerPanel.add(createAccountLabel, "align center, gapbottom 20");

        userNameTextField = new JTextField();
        userNameTextField.putClientProperty("JTextField.placeholderText", "Enter your name");
        registerPanel.add(userNameTextField, "growx, h 40!, gapbottom 10");

        userEmailTextField = new JTextField();
        userEmailTextField.putClientProperty("JTextField.placeholderText", "Enter your email");
        registerPanel.add(userEmailTextField, "growx, h 40!, gapbottom 10");

        userPasswordField = new JPasswordField();
        userPasswordField.putClientProperty("JTextField.placeholderText", "Enter your password");
        registerPanel.add(userPasswordField, "growx, h 40!, gapbottom 20");

        signUpButton = new JButton("Sign Up");
        registerPanel.add(signUpButton, "growx, h 40!");

        registerPanel.add(new JLabel(), "growy"); // Empty cell at the bottom

        // Add the Register panel to the card panel
        cardPanel.add(registerPanel, "Register");

        return cardPanel;
    }

    /**
     * Adds mouse listeners to the hover panel and its components to handle the hover effect.
     *
     * @param hoverPanel       The panel where the image (logo) and greetings message(e.g. Welcome back!) appear (the hover).
     * @param targetPanel      The panel where the mouse doesn't exist (the hover).
     */

    private void setupHoverEffect(JPanel hoverPanel, JPanel targetPanel, String greetingsMessage, String imagePath) {
        // Create a hover panel with the same size as the target panel
        JPanel hoverContentPanel = new JPanel(new MigLayout("wrap, insets 20", "[grow]", "[grow][][][grow]"));
        hoverContentPanel.setBackground(Color.WHITE);

        JLabel iauClinicLabel = new JLabel(new ImageIcon(imagePath));
        JLabel textLabel = new JLabel(greetingsMessage, SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 25));

        // Add components to the hover panel
        hoverContentPanel.add(new JLabel(), "growy"); // Empty cell at the top
        hoverContentPanel.add(iauClinicLabel, "align center, gapbottom 20");
        hoverContentPanel.add(textLabel, "align center, h 40!");
        hoverContentPanel.add(new JLabel(), "growy"); // Empty cell at the bottom

        // Add the hover panel to the target panel's CardLayout
        ((CardLayout) targetPanel.getLayout()).addLayoutComponent(hoverContentPanel, "Hover");
        targetPanel.add(hoverContentPanel, "Hover");

        // Add mouse listeners to the panel and its components
        addHoverListeners(hoverPanel, targetPanel);
    }

    private void addHoverListeners(JPanel hoverPanel, JPanel targetPanel) {
        // Add mouse listener to the hover panel
        hoverPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                // When the mouse enters the hover panel, set the flag to false
                // This indicates that the mouse is inside the hover panel or its components
                isMouseOutsideHover = false;
                // Update the hover state to show the original panel
                updateHoverState(targetPanel);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // When the mouse exits the hover panel, check if it is still inside any component in the original panel
                if (!hoverPanel.contains(e.getPoint())) {
                    // If the mouse is completely outside the hover panel and its components, set the flag to true
                    isMouseOutsideHover = true;
                    // Update the hover state to show the hover panel
                    updateHoverState(targetPanel);
                }
            }
        });

        // Add mouse listeners to all components inside the hover panel
        for (Component component : hoverPanel.getComponents()) {
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // When the mouse enters any component inside the hover panel, set the flag to false
                    // This indicates that the mouse is inside the hover panel or its components
                    isMouseOutsideHover = false;
                    // Update the hover state to show the original panel
                    updateHoverState(targetPanel);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // When the mouse exits any component, check if it is still inside the hover panel or any other component
                    if (!hoverPanel.contains(e.getPoint())) {
                        // If the mouse is completely outside the hover panel and its components, set the flag to true
                        isMouseOutsideHover = true;
                        // Update the hover state to show the hover panel
                        updateHoverState(targetPanel);
                    }
                }
            });
        }
    }

    /**
     * Updates the hover state based on the flag `isMouseOutsideHover`.
     *
     * @param targetPanel  The panel where the mouse doesn't exist (the hover).
     */
    private void updateHoverState(JPanel targetPanel) {
        // Check the value of the flag to determine which panel to show
        if (isMouseOutsideHover) {
            // If the flag is false, it means the mouse is inside the hover panel or its components
            // Show the original panel (buttons, text fields, etc.)
            if (targetPanel == signInPanel) {
                // If the target panel is the Sign In panel, show the Sign In content
                ((CardLayout) targetPanel.getLayout()).show(targetPanel, "SignIn");
            } else {
                // If the target panel is the Register panel, show the Register content
                ((CardLayout) targetPanel.getLayout()).show(targetPanel, "Register");
            }
        } else {
            // If the flag is true, it means the mouse is outside the hover panel and its components
            // Show the hover panel (image and greetings message)
            ((CardLayout) targetPanel.getLayout()).show(targetPanel, "Hover");
        }
    }


}