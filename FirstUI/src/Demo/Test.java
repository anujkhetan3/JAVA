package Demo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test implements ActionListener {

    private static JTextField emailField;
    private static JTextField passwordFeild;

    public static void main(String[] args) {

        JFrame f1 = new JFrame();
       // f1.setSize(300,600);
       // f1.setResizable(false);

        JPanel p1=new JPanel();
        Dimension d1=new Dimension(600,300);
        p1.setPreferredSize(d1);
        p1.setBackground(Color.black);

         emailField=new JTextField();
        emailField.setText("Enter email");

         passwordFeild=new JTextField();
        passwordFeild.setText("Enter password");

        JLabel l=new JLabel("Hello World!!");


        JButton submitButton=new JButton();
        submitButton.setText("Login");
        submitButton.setForeground(Color.red);

        Test t=new Test();
      //  CodeKamp c=new CodeKamp();

        submitButton.addActionListener(t);


        p1.add(submitButton);
        p1.add(Test.emailField);
        p1.add(Test.passwordFeild);
        p1.add(l);
        f1.add(p1);
        f1.pack();

        f1.setVisible(true);


    }

    public static void login(String e, String p)
    {

        System.out.println("Email:"+e+" and Password:"+p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email=Test.emailField.getText();
        String password=Test.passwordFeild.getText();

        Test.login(email,password);






    }
}
