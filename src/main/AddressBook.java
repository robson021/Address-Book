package main;

import enums.Categories;
import enums.Titles;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 04.04.16.
 */
public class AddressBook extends JFrame {

    private static final String TITLE = "Address Book";
    private static final int DEFAULT_COLUMN_SIZE = 15;
    private static JFrame mainFrame;

    private JPanel centerPanel, leftPanel;
    private JTextField nameSurnameTextField, phoneNo, address, email;
    private ButtonGroup radioButtonGroup;
    private JComboBox comboBox;
    private JCheckBox family, friends, work;
    private JButton addButton, deleteButton, fullInfoButton, searchButton;

    private JTextField searchField;
    private JList listOfContacts;

    private static final List<Person> personList = new ArrayList<>();

    public AddressBook() {
        super(TITLE);
        this.setLayout(new BorderLayout());

        centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        gbc.insets.set(5, 5, 5, 5);

        nameSurnameTextField = new JTextField(DEFAULT_COLUMN_SIZE);
        centerPanel.add(new JLabel("Imię i nazwisko:"), gbc);
        gbc.gridx++;
        centerPanel.add(nameSurnameTextField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        phoneNo = new JTextField(DEFAULT_COLUMN_SIZE);
        centerPanel.add(new JLabel("Numer telefonu:"), gbc);
        gbc.gridx++;
        centerPanel.add(phoneNo, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        address = new JTextField(DEFAULT_COLUMN_SIZE);
        centerPanel.add(new JLabel("Adres:"), gbc);
        gbc.gridx++;
        centerPanel.add(address, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        email = new JTextField(DEFAULT_COLUMN_SIZE);
        centerPanel.add(new JLabel("E-mail:"), gbc);
        gbc.gridx++;
        centerPanel.add(email, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        radioButtonGroup = new ButtonGroup();
        JRadioButton maleButton = new JRadioButton("mężczyzna");
        JRadioButton femaleButton = new JRadioButton("kobieta");
        radioButtonGroup.add(femaleButton);
        radioButtonGroup.add(maleButton);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(new JLabel("Płeć:"));
        buttonPanel.add(femaleButton);
        buttonPanel.add(maleButton);

        centerPanel.add(buttonPanel, gbc);
        gbc.gridy++;

        centerPanel.add(new JLabel("Tytuł:"), gbc);
        gbc.gridx++;

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(Titles.values()));
        centerPanel.add(comboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JPanel checkBoxesPanel = new JPanel(new FlowLayout());

        friends = new JCheckBox(Categories.FRIEND.toString());
        friends.setSelected(true);

        family = new JCheckBox("rodzina");
        work = new JCheckBox("praca");

        checkBoxesPanel.add(new JLabel("Kategoria:"));
        checkBoxesPanel.add(friends);
        checkBoxesPanel.add(family);
        checkBoxesPanel.add(work);

        centerPanel.add(checkBoxesPanel, gbc);
        gbc.gridy++;

        JPanel buttonPanel2 = new JPanel(new FlowLayout());
        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");

        addButton.addActionListener(new AddButtonAction());
        deleteButton.addActionListener(new DeleteButtonAction());

        buttonPanel2.add(addButton);
        buttonPanel2.add(deleteButton);

        searchButton = new JButton("Szukaj");
        searchButton.addActionListener(new SearchButtonAction());

        centerPanel.add(buttonPanel2, gbc);
        gbc.gridy++;

        this.add(centerPanel, BorderLayout.CENTER);

        leftPanel = new JPanel(new GridBagLayout());
        gbc.gridy = gbc.gridx = 0;

        leftPanel.add(new JLabel("Filter:"), gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        searchField = new JTextField(DEFAULT_COLUMN_SIZE);
        leftPanel.add(searchField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        leftPanel.add(searchButton, gbc);
        gbc.gridy++;

        initListOfContacts();

        listOfContacts = new JList(personList.toArray());
        listOfContacts.setFixedCellWidth(DEFAULT_COLUMN_SIZE * 15);
        listOfContacts.setFixedCellHeight(listOfContacts.getFixedCellWidth() / 4);
        listOfContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOfContacts.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        leftPanel.add(listOfContacts, gbc);
        gbc.gridy++;

        JPanel buttonPanel3 = new JPanel(new FlowLayout());
        fullInfoButton = new JButton("Info");
        fullInfoButton.addActionListener(new InfoButtonAction());
        buttonPanel3.add(fullInfoButton);
        leftPanel.add(buttonPanel3, gbc);
        gbc.gridy++;

        this.add(leftPanel, BorderLayout.WEST);
    }

    private void initListOfContacts() {
        Person p = new Person();
        List<Categories> catergories = new ArrayList<>();
        catergories.add(Categories.FRIEND);
        p.setAddress("Jakas Ulica 43");
        p.setCategory(catergories);
        p.setEmail("liszka@gmail.com");
        p.setName("Małgorzata");
        p.setSurname("Liszka");
        p.setTitle(Titles.NONE);

        personList.add(p);

        Person p2 = new Person();
        p2.setAddress("Jakas Ulica 33");
        p2.setCategory(catergories);
        p2.setEmail("nowak@gmail.com");
        p2.setName("Robert");
        p2.setSurname("Nowak");
        p2.setTitle(Titles.NONE);

        personList.add(p2);
    }

    public static String getTITLE() {
        return TITLE;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame = new AddressBook();
                mainFrame.pack();
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLocationByPlatform(true);
                mainFrame.setResizable(false);
                mainFrame.setVisible(true);
            }
        });
    }

    private boolean validateFields() {
        if (nameSurnameTextField.getText().trim() == "") return false;
        String[] ns = nameSurnameTextField.getText().split(" ");
        if (ns.length != 2) return false;
        if (phoneNo.getText().trim() == "") return false;
        String e = email.getText().trim();
        if (e == "" || !e.contains("@")) return false;
        if (address.getText().trim() == "") return false;

        return true;
    }

    private class AddButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (validateFields()) {
                Person p = new Person();
                p.setEmail(email.getText().trim());
                p.setTitle((Titles) comboBox.getSelectedItem());
                String[] ns = nameSurnameTextField.getText().trim().split(" ");
                p.setName(ns[0]);
                p.setSurname(ns[1]);
                p.setPhoneNo(phoneNo.getText().trim());


                List<Categories> categories = new ArrayList<>();

                if (friends.isSelected()) categories.add(Categories.FRIEND);
                if (family.isSelected()) categories.add(Categories.FAMILY);
                if (work.isSelected()) categories.add(Categories.WORK);

                p.setCategory(categories);

                personList.add(p);

                System.out.println("Person added: " + p.toString());
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Błędnie wypełniona pola!");
            }

        }
    }

    private class DeleteButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO removing people
        }
    }

    private class SearchButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Person> persons = new ArrayList<>();
            String text = searchField.getText().trim().toLowerCase();
            System.out.println(text);
            for (Person person : personList) {
                System.out.println(person.toString());
                if (person.getName().toLowerCase().equals(text) || person.getSurname().toLowerCase().equals(text))
                    persons.add(person);
            }
            if (persons.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Nic nie znaleziono.");
            } else {
                DefaultListModel model = new DefaultListModel();
                for (Person person : persons)
                    model.addElement(person);
                listOfContacts.setModel(model);
            }
        }
    }

    private class InfoButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = listOfContacts.getSelectedValue().toString();
            JOptionPane.showMessageDialog(mainFrame, text);
        }
    }
}
