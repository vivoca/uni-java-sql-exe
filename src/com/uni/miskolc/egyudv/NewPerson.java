package com.uni.miskolc.egyudv;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewPerson extends JDialog {
    private JTextField id;
    private JTextField name;
    private JTextField city;
    private JTextField birthDate;
    private JTextField height;
    private JTextField weight;
    private DbMethods dbMethods = new DbMethods();
    private Checker checker = new Checker();

    /**
     * Create the dialog.
     */
    public NewPerson() {
        setBounds(100, 100, 215, 286);
        getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, 11, 75, 14);
        getContentPane().add(lblId);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(10, 41, 75, 14);
        getContentPane().add(lblName);

        JLabel lblBirthDate = new JLabel("Birth date");
        lblBirthDate.setBounds(10, 71, 75, 14);
        getContentPane().add(lblBirthDate);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(10, 101, 75, 14);
        getContentPane().add(lblCity);

        JLabel lblHeight = new JLabel("Height");
        lblHeight.setBounds(10, 131, 75, 14);
        getContentPane().add(lblHeight);
        
        JLabel lblWeight = new JLabel("Weight");
        lblWeight.setBounds(10, 161, 75, 14);
        getContentPane().add(lblWeight);

        id = new JTextField();
        id.setBounds(95, 11, 86, 20);
        getContentPane().add(id);
        id.setColumns(10);

        name = new JTextField();
        name.setBounds(95, 38, 86, 20);
        getContentPane().add(name);
        name.setColumns(10);

        birthDate = new JTextField();
        birthDate.setBounds(95, 68, 86, 20);
        getContentPane().add(birthDate);
        birthDate.setColumns(10);

        city = new JTextField();
        city.setBounds(95, 98, 86, 20);
        getContentPane().add(city);
        city.setColumns(10);

        height = new JTextField();
        height.setBounds(95, 128, 86, 20);
        getContentPane().add(height);
        height.setColumns(10);
        
        weight = new JTextField();
        weight.setBounds(95, 158, 86, 20);
        getContentPane().add(weight);
        weight.setColumns(10);

        JButton btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checker.goodInt(id, "ID")) {
                    if (checker.filled(name, "Name")) {
                        if(checker.goodDate(birthDate, "Birth date")) {
                            if(checker.filled(city, "City")) {
                                if(checker.goodInt(height, "Height")) {
                                    if(checker.goodInt(weight, "Weight")) {
                                        dbMethods.connect();
                                        dbMethods.insertData(getFieldValue(id), getFieldValue(name), getFieldValue(birthDate), getFieldValue(city), getFieldValue(height), getFieldValue(weight));
                                        dbMethods.disconnect();
                                    }
                                }

                            }
                        }
                    }
                }
                dispose();
            }
        });
        btnCreate.setBounds(53, 213, 89, 23);
        getContentPane().add(btnCreate);


    }

    public String getFieldValue(JTextField jtf) {
        return jtf.getText();
    }
}
