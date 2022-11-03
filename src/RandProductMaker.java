import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class RandProductMaker extends JFrame {
        RandomAccessFile randFile;

        JPanel mainPnl, titlePnl, entryPnl, entryPnl2, productPnl, btnPnl;
        JLabel titleLbl, nameLbl, descLbl, IDLbl, costLbl, counterLbl;
        JTextField nameTF, descTF, IDTF, costTF, counterTF;
        JButton quitBtn, submitBtn;


        String cost;
        int productCounter = 0;



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
        createButtonPanel();


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

        counterLbl = new JLabel("# Products Entered:");
        counterLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        counterTF =  new JTextField("", 20);


        nameLbl = new JLabel("Product Name:");
        nameLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        nameTF =  new JTextField("", 20);

        descLbl = new JLabel("Product Description:");
        descLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        descTF =  new JTextField("", 20);

        IDLbl = new JLabel("Product ID:");
        IDLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        IDTF =  new JTextField("", 20);

        IDLbl = new JLabel("Product ID:");
        IDLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        IDTF =  new JTextField("", 20);

        costLbl = new JLabel("Product Cost:");
        costLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        costTF =  new JTextField("", 20);


        entryPnl.add(counterLbl, new GridLayout(1,1));
        entryPnl2.add(counterTF, new GridLayout(1,1));
        counterTF.setEditable(false);

        entryPnl.add(nameLbl, new GridLayout(2,1));
        entryPnl2.add(nameTF, new GridLayout(2,1));

        entryPnl.add(descLbl, new GridLayout(3,1));
        entryPnl2.add(descTF, new GridLayout(3,1));

        entryPnl.add(IDLbl, new GridLayout(4,1));
        entryPnl2.add(IDTF, new GridLayout(4,1));

        entryPnl.add(costLbl, new GridLayout(5,1));
        entryPnl2.add(costTF, new GridLayout(5,1));


        productPnl.add(entryPnl, BorderLayout.WEST);
        productPnl.add(entryPnl2, BorderLayout.EAST);
        mainPnl.add(productPnl, BorderLayout.CENTER);
    }


    private void createButtonPanel() {
        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout(1,3));

        submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));

        btnPnl.add(submitBtn);
        btnPnl.add(quitBtn);

        mainPnl.add(btnPnl, BorderLayout.SOUTH);

        //quit -DONE
        quitBtn.addActionListener(new ActionListener() {
            JOptionPane pane =new JOptionPane();
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(pane,"Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){System.exit(0);}
                else {setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }}});

        submitBtn.addActionListener(new ActionListener() {
            JOptionPane pane =new JOptionPane();
            @Override
            public void actionPerformed(ActionEvent e) {
                //get rid of extra
                if(nameTF.getText().length() > 35)
                    nameTF.setText(nameTF.getText().substring(0, 35));
                if(IDTF.getText().length() > 6)
                    IDTF.setText(IDTF.getText().substring(0, 6));
                if(descTF.getText().length() > 75)
                    descTF.setText(descTF.getText().substring(0, 75));
                if(costTF.getText().length() > 6)
                    costTF.setText(costTF.getText().replaceAll("[^\\d.]", "").substring(0, 6));


                //collect, check, clear, and save
                cost = costTF.getText();
                if (Pattern.matches("[ 0.1-9.9 ]+", cost)) {

                    System.out.println(nameTF.getText());
                    System.out.println(descTF.getText());
                    System.out.println(IDTF.getText());
                    System.out.println(costTF.getText());


                    try {

                        randFile = new RandomAccessFile(new File("src/RandProducts.txt"), "rw");
                        randFile.write(String.format("%35s%6s%6s%75s\n",nameTF.getText(), descTF.getText(), IDTF.getText(), costTF.getText() ).getBytes());
                        randFile.close();

                    } catch (FileNotFoundException i) {
                        i.printStackTrace();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    nameTF.setText("");
                    descTF.setText("");
                    IDTF.setText("");
                    costTF.setText("");
                    productCounter++;
                    counterTF.setText(String.valueOf(productCounter));

                } else {
                    String numNeeded = "Please enter a number for cost! Try Again!";
                    JOptionPane.showMessageDialog(null, numNeeded);
                }
            }
        });
    }


}
// capture the ta to string var
    /* use in a button to finally collect all the info

    if costlbl != number

    if (Pattern.matches("[a-zA-Z]+", costTF) == false && text.length() > 2) {
    cost = costTF; }

    pop up message and clear field


    name = nameTF.getText();
    System.out.println(name);

     */





