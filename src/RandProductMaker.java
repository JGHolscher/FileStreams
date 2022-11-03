import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandProductMaker extends JFrame {
        JPanel mainPnl, titlePnl, entryPnl, entryPnl2, productPnl, btnPnl;
        JLabel titleLbl, nameLbl, descLbl, IDLbl, costLbl;
        JTextField nameTF, descTF, IDTF, costTF;


        String name;
        String description;
        String ID;
        double cost;


    public RandProductMaker() {
        setTitle("Product List Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize((screenWidth / 4) * 3, screenHeight);
        setLocationRelativeTo(null); //centers

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        entryPnl = new JPanel();
        entryPnl.setLayout(new GridLayout(5,2));
        entryPnl2 = new JPanel();
        entryPnl2.setLayout(new GridLayout(5,1));




        createTitlePanel();
        createProductPanel();
        //createButtonPanel();


        setVisible(true);
    }

    private void createTitlePanel()//DONE
    {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Enter Product Information:", JLabel.CENTER);
        titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        //aligns text and image to be stacked not side by side
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);

    }

    private void createProductPanel()
    {
        productPnl = new JPanel();

        nameLbl = new JLabel("Product Name:");
        nameLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        nameTF =  new JTextField("", 6);

        descLbl = new JLabel("Product Description:");
        descLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        descTF =  new JTextField("", 6);

        IDLbl = new JLabel("Product ID:");
        IDLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        IDTF =  new JTextField("", 6);

        IDLbl = new JLabel("Product ID:");
        IDLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        IDTF =  new JTextField("", 6);


        entryPnl.add(nameLbl, new GridLayout(1,1));
        entryPnl2.add(nameTF, new GridLayout(1,1));

        entryPnl.add(descLbl, new GridLayout(2,1));
        entryPnl2.add(descTF, new GridLayout(2,1));

        entryPnl.add(IDLbl, new GridLayout(3,1));
        entryPnl2.add(IDTF, new GridLayout(3,1));









        productPnl.add(entryPnl, BorderLayout.WEST);
        productPnl.add(entryPnl2, BorderLayout.EAST);
        mainPnl.add(productPnl, BorderLayout.CENTER);
    }
    // capture the ta to string var
    /* use in a button to finally collect all the info

    if costlbl != number
    pop up message and clear field
    name = nameTF.getText();
    System.out.println(name);

     */
}

