import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Scanner;

public class Game2048 extends JFrame {

    private Container cp;
    private JPanel jpnN = new JPanel();

    private JPanel jpn = new JPanel();
    private JPanel jpnU = new JPanel();
    private JLabel jlbtype = new JLabel("Type :");
    private JTextField jtf = new JTextField();
    private JButton set = new JButton("set");

    private test jpnC = new test();
    private JLabel jlb2048 = new JLabel("2048", JLabel.CENTER);
    private int score = 0;
    private JLabel jlbsc = new JLabel("<html><body><p align=\"center\">Score<br/" + score, JLabel.CENTER);
    private JButton reset = new JButton("reset");
    public int clear = 0;
    public int[] arr = new int[4];
    public Character[] str = { 'a', 's', 'w', 'd' };
    public Character[] move = new Character[4];
    int[] num = new int[4];
    int n;

    public Game2048() {
        init();
    }

    public void init() {
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout(3, 3));
        // cp.setBackground(new Color(139, 119, 101));
        cp.setBackground(new Color(255, 248, 220));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(400, 50, 600, 600);
        this.setVisible(true);

        jpnN.setLayout(new GridLayout(1, 4, 1, 1));
        jpn.setLayout(new GridLayout(2, 1, 1, 1));
        jpnU.setLayout(new BorderLayout(1, 1));
        Font Font1 = new Font("", Font.BOLD, 45);
        Font Font2 = new Font("", Font.BOLD, 20);
        cp.add(jpnN, BorderLayout.NORTH);
        cp.add(jpnC, BorderLayout.CENTER);

        // add keylistener to frame
        kl kli = new kl(Game2048.this);
        // cp.requestFocus(); 
        cp.addKeyListener(kli);

        jlb2048.setFont(Font1);
        jlb2048.setHorizontalTextPosition(JLabel.CENTER);
        jlb2048.setBackground(new Color(255, 248, 220));
        jlb2048.setOpaque(true);
        jpnN.add(jlb2048);
        jpnN.add(jpn);
        jpn.add(jpnU);
        jpnU.add(jlbtype, BorderLayout.WEST);
        jpnU.add(jtf);
        jpn.add(set);
        set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jpnC.type = Integer.valueOf(jtf.getText());
                cp.requestFocus(); // !!!!!!!!

            }
        });

        jlbsc.setFont(Font2);
        jlbsc.setHorizontalTextPosition(JLabel.CENTER);
        jlbsc.setBackground(new Color(163, 148, 128));
        jlbsc.setOpaque(true);
        jpnN.add(jlbsc);

        reset.setFont(Font2);
        reset.setBackground(new Color(192, 192, 192));
        jpnN.add(reset);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // clear = 1;

                for (int i = 0; i < jpnC.row; i++) {
                    for (int j = 0; j < jpnC.col; j++) {
                        jpnC.cardNum[i][j] = 0;
                    }
                }

                // if type==3,reset move[]
                if (jpnC.type == 3) {
                    for (int i = 0; i < num.length; i++) {
                        num[i] = i;
                    }

                    for (int i = 0; i < arr.length; i++) {
                        n = (int) (Math.random() * (4 - i));
                        arr[i] = num[n];
                        for (int j = n; j < num.length - 1; j++) {
                            num[j] = num[j + 1];
                        }
                    }

                    for (int i = 0; i < 4; i++) {
                        move[i] = str[arr[i]];
                    }

                    JOptionPane.showMessageDialog(Game2048.this,
                            "Up :" + move[0] + " Down :" + move[1] + " Left :" + move[2] + " Right :" + move[3]);

                }

                score = 0;
                jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                Graphics g = null;
                jpnC.randomCard(g);
                jpnC.randomCard(g);
                // paint cards again
                repaint();

                // for (int i = 0; i < jpnC.row; i++) {
                // for (int j = 0; j < jpnC.col; j++) {
                // System.out.print(jpnC.cardNum[i][j] + " ");
                // }
                // System.out.println(" ");
                // }
                // System.out.println("@@@@@@@@@@@@@");

                cp.requestFocus(); // !!!!!!!!!!

            }
        });

        // for (int i = 0; i < num.length; i++) {
        // System.out.print(arr[i]);
        // }

    }

    public static void main(String[] args) {
        Game2048 mFrm = new Game2048();
        mFrm.setVisible(true);
    }

    class test extends JPanel {
        int row = 4, col = 4;
        int type;

        public int cardNum[][] = new int[row][col];
        int x, y;
        // private JFrame f = null;
        // private test tpanel = null;

        public test() {
            // this.setLayout(null);
            // this.setOpaque(false);
            // this.f = frame;
            // this.tpanel = this;
            init();
            // tpanel.requestFocus();
        }

        public void init() {

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    cardNum[i][j] = 0;
                }
            }

            Graphics g = null;
            randomCard(g);
            randomCard(g);

        }

        // rewrite function paint
        @Override
        public void paint(Graphics g) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    x = 130 + j * 80 + j * 5;
                    y = 50 + i * 80 + i * 5;
                    switch (cardNum[i][j]) {
                        case 0:
                            g.setColor(new Color(193, 205, 193));
                            break;
                        case 2:
                            g.setColor(new Color(205, 179, 139));
                            break;
                        case 4:
                            g.setColor(new Color(209, 238, 238));
                            break;
                        case 8:
                            g.setColor(new Color(240, 255, 190));
                            break;
                        case 16:
                            g.setColor(new Color(152, 155, 255));
                            break;
                        case 32:
                            g.setColor(new Color(191, 139, 95));
                            break;
                        case 64:
                            g.setColor(new Color(238, 207, 161));
                            break;
                        case 128:
                            g.setColor(new Color(178, 223, 239));
                            break;
                        case 256:
                            g.setColor(new Color(224, 238, 224));
                            break;
                        case 512:
                            g.setColor(new Color(230, 230, 250));
                            break;
                        case 1024:
                            g.setColor(new Color(193, 255, 293));
                            break;
                        case 2048:
                            g.setColor(new Color(188, 238, 104));
                            break;
                        default:
                            g.setColor(new Color(255, 165, 0));
                            break;
                    }
                    g.fillRoundRect(x, y, 80, 80, 20, 20);

                    if (cardNum[i][j] != 0) {
                        g.setColor(new Color(0, 0, 0));
                        g.setFont(new Font("", Font.BOLD, 23));
                        g.drawString(String.valueOf(cardNum[i][j]), x + 23, y + 50);
                    }
                }
            }
        }

        public void randomCard(Graphics g) {
            int r = 0;
            Random rand = new Random();
            int m = rand.nextInt(5);
            int a = rand.nextInt(row);
            int b = rand.nextInt(col);

            if (m == 0 && cardNum[a][b] == 0) {
                cardNum[a][b] = 4;
                repaint();
                // g.drawString(String.valueOf(cardNum[a][b]), x + 23, y + 50);
            } else if (m != 0 && cardNum[a][b] == 0) {
                cardNum[a][b] = 2;
                repaint();
                // g.drawString(String.valueOf(cardNum[a][b]), x + 23, y + 50);
            } else if (cardNum[a][b] != 0) {
                // check there are empty card or not
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (cardNum[i][j] == 0) {
                            r = 1; // it is empty card
                        }
                    }
                }

                if (r == 1) {
                    randomCard(g);
                }
            }
            repaint();
        }

    }

    class kl implements KeyListener {
        private JFrame f = null;
        private test t = new test();
        kl KL = null;
        int show = 0;

        public kl(JFrame fr) {
            this.KL = this;
            this.f = fr;

            // create random number
            for (int i = 0; i < num.length; i++) {
                num[i] = i;
            }

            for (int i = 0; i < arr.length; i++) {
                n = (int) (Math.random() * (4 - i));
                arr[i] = num[n];
                for (int j = n; j < num.length - 1; j++) {
                    num[j] = num[j + 1];
                }
            }

            for (int i = 0; i < 4; i++) {
                move[i] = str[arr[i]];
            }

            // for (int i = 0; i < num.length; i++) {
            // System.out.print(move[i]+"!!!!!");
            // }

        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(jpnC.type);
            Character kc = new Character(e.getKeyChar());

            if (jpnC.type == 1 || jpnC.type == 0) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    System.out.println("Move up");
                    for (int k = 0; k < t.col; k++) {
                        int flag = 0;
                        for (int k2 = 1; k2 < t.row; k2++) {
                            int tmp = k2;
                            while (tmp != 0) {
                                if (jpnC.cardNum[tmp - 1][k] == 0) {
                                    jpnC.cardNum[tmp - 1][k] = jpnC.cardNum[tmp][k];
                                    jpnC.cardNum[tmp][k] = 0;
                                } else if (jpnC.cardNum[tmp - 1][k] == jpnC.cardNum[tmp][k] && flag == 0) {
                                    jpnC.cardNum[tmp - 1][k] *= 2;

                                    score += jpnC.cardNum[tmp - 1][k];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[tmp][k] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp--;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();

                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    System.out.println("Move left");
                    for (int k = 0; k < t.row; k++) {
                        int flag = 0;
                        for (int k2 = 1; k2 < t.col; k2++) {
                            int tmp = k2;
                            while (tmp != 0) {
                                if (jpnC.cardNum[k][tmp - 1] == 0) {
                                    jpnC.cardNum[k][tmp - 1] = jpnC.cardNum[k][tmp];
                                    jpnC.cardNum[k][tmp] = 0;
                                } else if (jpnC.cardNum[k][tmp - 1] == jpnC.cardNum[k][tmp] && flag == 0) {
                                    jpnC.cardNum[k][tmp - 1] *= 2;

                                    score += jpnC.cardNum[k][tmp - 1];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[k][tmp] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp--;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    System.out.println("Move down");
                    for (int k = 0; k < t.col; k++) {
                        int flag = 0;
                        for (int k2 = t.row - 2; k2 >= 0; k2--) {
                            int tmp = k2;
                            while (tmp != t.row - 1) {
                                if (jpnC.cardNum[tmp + 1][k] == 0) {
                                    jpnC.cardNum[tmp + 1][k] = jpnC.cardNum[tmp][k];
                                    jpnC.cardNum[tmp][k] = 0;
                                } else if (jpnC.cardNum[tmp + 1][k] == jpnC.cardNum[tmp][k] && flag == 0) {
                                    jpnC.cardNum[tmp + 1][k] *= 2;

                                    score += jpnC.cardNum[tmp + 1][k];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[tmp][k] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp++;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    System.out.println("Move right");
                    for (int k = 0; k < t.row; k++) {
                        int flag = 0;
                        for (int k2 = t.col - 1; k2 >= 0; k2--) {
                            int tmp = k2;
                            while (tmp != t.col - 1) {
                                if (jpnC.cardNum[k][tmp + 1] == 0) {
                                    jpnC.cardNum[k][tmp + 1] = jpnC.cardNum[k][tmp];
                                    jpnC.cardNum[k][tmp] = 0;
                                } else if (jpnC.cardNum[k][tmp + 1] == jpnC.cardNum[k][tmp] && flag == 0) {
                                    jpnC.cardNum[k][tmp + 1] *= 2;

                                    score += jpnC.cardNum[k][tmp + 1];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[k][tmp] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp++;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                }

                for (int i = 0; i < jpnC.row; i++) {
                    for (int j = 0; j < jpnC.col; j++) {
                        System.out.print(jpnC.cardNum[i][j] + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println("+++++++");

                int check, c;
                while (1 == 1) {
                    check = 0;
                    c = 0;
                    for (int i = 0; i < jpnC.row; i++) {
                        for (int j = 0; j < jpnC.col; j++) {
                            if (jpnC.cardNum[i][j] == 0) {
                                check = 1;  //have empty card
                                break;
                            }
                        }
                        if (check == 1) {
                            break;
                        }
                    }

                    if (check == 1) {
                        break;
                    }

                    // Check if nearby card values are equal
                    for (int ii = 0; ii < jpnC.row; ii++) {
                        for (int jj = 0; jj < jpnC.col; jj++) {

                            if (ii != jpnC.row - 1 && jj != jpnC.col - 1) {
                                // check that the cards on the right and below are equal
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii + 1][jj]
                                        || jpnC.cardNum[ii][jj] == jpnC.cardNum[ii][jj + 1]) {
                                    c = 1;
                                }
                            } else if (ii != jpnC.row - 1 && jj == jpnC.col - 1) {
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii + 1][jj]) {
                                    c = 1;
                                }
                            } // check the last row card's valume
                            else if (ii == jpnC.row - 1 && jj != jpnC.col - 1) {
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii][jj + 1]) {
                                    c = 1;
                                }
                            }

                        }

                    }

                    if (c == 1) {
                        break;
                    }

                    if (c != 1) {
                        JOptionPane.showMessageDialog(f, "No empty card!!");
                        for (int q = 0; q < jpnC.row; q++) {
                            for (int d = 0; d < jpnC.col; d++) {
                                // set card's num=0
                                jpnC.cardNum[q][d] = 0;
                            }
                        }
                        score = 0;
                        jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);
                        Graphics g = null;
                        jpnC.randomCard(g);
                        jpnC.randomCard(g);
                        repaint();
                    }
                    break;
                }

            } else if (jpnC.type == 2) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    System.out.println(e.getKeyChar() + " Move up");
                    for (int k = 0; k < t.col; k++) {
                        int flag = 0;
                        for (int k2 = 1; k2 < t.row; k2++) {
                            int tmp = k2;
                            while (tmp != 0) {
                                if (jpnC.cardNum[tmp - 1][k] == 0) {
                                    jpnC.cardNum[tmp - 1][k] = jpnC.cardNum[tmp][k];
                                    jpnC.cardNum[tmp][k] = 0;
                                } else if (jpnC.cardNum[tmp - 1][k] == jpnC.cardNum[tmp][k] && flag == 0) {
                                    jpnC.cardNum[tmp - 1][k] *= 2;

                                    score += jpnC.cardNum[tmp - 1][k];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[tmp][k] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp--;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();

                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    System.out.println(e.getKeyChar() + " Move left");
                    for (int k = 0; k < t.row; k++) {
                        int flag = 0;
                        for (int k2 = 1; k2 < t.col; k2++) {
                            int tmp = k2;
                            while (tmp != 0) {
                                if (jpnC.cardNum[k][tmp - 1] == 0) {
                                    jpnC.cardNum[k][tmp - 1] = jpnC.cardNum[k][tmp];
                                    jpnC.cardNum[k][tmp] = 0;
                                } else if (jpnC.cardNum[k][tmp - 1] == jpnC.cardNum[k][tmp] && flag == 0) {
                                    jpnC.cardNum[k][tmp - 1] *= 2;

                                    score += jpnC.cardNum[k][tmp - 1];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[k][tmp] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp--;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    System.out.println(e.getKeyChar() + " Move down");
                    for (int k = 0; k < t.col; k++) {
                        int flag = 0;
                        for (int k2 = t.row - 2; k2 >= 0; k2--) {
                            int tmp = k2;
                            while (tmp != t.row - 1) {
                                if (jpnC.cardNum[tmp + 1][k] == 0) {
                                    jpnC.cardNum[tmp + 1][k] = jpnC.cardNum[tmp][k];
                                    jpnC.cardNum[tmp][k] = 0;
                                } else if (jpnC.cardNum[tmp + 1][k] == jpnC.cardNum[tmp][k] && flag == 0) {
                                    jpnC.cardNum[tmp + 1][k] *= 2;

                                    score += jpnC.cardNum[tmp + 1][k];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[tmp][k] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp++;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    System.out.println(e.getKeyChar() + " Move right");
                    for (int k = 0; k < t.row; k++) {
                        int flag = 0;
                        for (int k2 = t.col - 1; k2 >= 0; k2--) {
                            int tmp = k2;
                            while (tmp != t.col - 1) {
                                if (jpnC.cardNum[k][tmp + 1] == 0) {
                                    jpnC.cardNum[k][tmp + 1] = jpnC.cardNum[k][tmp];
                                    jpnC.cardNum[k][tmp] = 0;
                                } else if (jpnC.cardNum[k][tmp + 1] == jpnC.cardNum[k][tmp] && flag == 0) {
                                    jpnC.cardNum[k][tmp + 1] *= 2;

                                    score += jpnC.cardNum[k][tmp + 1];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[k][tmp] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp++;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                }

                for (int i = 0; i < jpnC.row; i++) {
                    for (int j = 0; j < jpnC.col; j++) {
                        System.out.print(jpnC.cardNum[i][j] + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println("+++++++");

                int check, c;
                while (1 == 1) {
                    check = 0;
                    c = 0;
                    for (int i = 0; i < jpnC.row; i++) {
                        for (int j = 0; j < jpnC.col; j++) {
                            if (jpnC.cardNum[i][j] == 0) {
                                check = 1;
                                break;
                            }
                        }
                        if (check == 1) {
                            break;
                        }
                    }
                    if (check == 1) {
                        break;
                    }

                    // Check if nearby card values are equal
                    for (int ii = 0; ii < jpnC.row; ii++) {
                        for (int jj = 0; jj < jpnC.col; jj++) {

                            if (ii != jpnC.row - 1 && jj != jpnC.col - 1) {
                                // check that the cards on the right and below are equal
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii + 1][jj]
                                        || jpnC.cardNum[ii][jj] == jpnC.cardNum[ii][jj + 1]) {
                                    c = 1;
                                }
                            } else if (ii != jpnC.row - 1 && jj == jpnC.col - 1) {
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii + 1][jj]) {
                                    c = 1;
                                }
                            } // check the last row card's valume
                            else if (ii == jpnC.row - 1 && jj != jpnC.col - 1) {
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii][jj + 1]) {
                                    c = 1;
                                }
                            }

                        }

                    }

                    if (c == 1) {
                        break;
                    }

                    if (c != 1) {
                        JOptionPane.showMessageDialog(f, "No empty card!!");
                        for (int q = 0; q < jpnC.row; q++) {
                            for (int d = 0; d < jpnC.col; d++) {
                                // set card's num=0
                                jpnC.cardNum[q][d] = 0;
                            }
                        }
                        score = 0;
                        jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);
                        Graphics g = null;
                        jpnC.randomCard(g);
                        jpnC.randomCard(g);
                        repaint();
                    }
                    break;
                }

            } else if (jpnC.type == 3) {

                if (show == 0 || e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    JOptionPane.showMessageDialog(f,
                            "Up :" + move[0] + " Down :" + move[1] + " Left :" + move[2] + " Right :" + move[3]);
                    show = 1;
                }
                System.out.println("Up :" + move[0] + " Down :" + move[1] + " Left :" + move[2] + " Right :" + move[3]);

                // Character n = new Character(e.getKeyChar());
                System.out.println("KeyChar =" + kc);

                if (kc.equals(move[0])) {
                    System.out.println(n + "Move up");
                    for (int k = 0; k < t.col; k++) {
                        int flag = 0;
                        for (int k2 = 1; k2 < t.row; k2++) {
                            int tmp = k2;
                            while (tmp != 0) {
                                if (jpnC.cardNum[tmp - 1][k] == 0) {
                                    jpnC.cardNum[tmp - 1][k] = jpnC.cardNum[tmp][k];
                                    jpnC.cardNum[tmp][k] = 0;
                                } else if (jpnC.cardNum[tmp - 1][k] == jpnC.cardNum[tmp][k] && flag == 0) {
                                    jpnC.cardNum[tmp - 1][k] *= 2;

                                    score += jpnC.cardNum[tmp - 1][k];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[tmp][k] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp--;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();

                } else if (kc.equals(move[2])) {
                    System.out.println("Move left");
                    for (int k = 0; k < t.row; k++) {
                        int flag = 0;
                        for (int k2 = 1; k2 < t.col; k2++) {
                            int tmp = k2;
                            while (tmp != 0) {
                                if (jpnC.cardNum[k][tmp - 1] == 0) {
                                    jpnC.cardNum[k][tmp - 1] = jpnC.cardNum[k][tmp];
                                    jpnC.cardNum[k][tmp] = 0;
                                } else if (jpnC.cardNum[k][tmp - 1] == jpnC.cardNum[k][tmp] && flag == 0) {
                                    jpnC.cardNum[k][tmp - 1] *= 2;

                                    score += jpnC.cardNum[k][tmp - 1];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[k][tmp] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp--;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                } else if (kc.equals(move[1])) {
                    System.out.println("Move down");
                    for (int k = 0; k < t.col; k++) {
                        int flag = 0;
                        for (int k2 = t.row - 2; k2 >= 0; k2--) {
                            int tmp = k2;
                            while (tmp != t.row - 1) {
                                if (jpnC.cardNum[tmp + 1][k] == 0) {
                                    jpnC.cardNum[tmp + 1][k] = jpnC.cardNum[tmp][k];
                                    jpnC.cardNum[tmp][k] = 0;
                                } else if (jpnC.cardNum[tmp + 1][k] == jpnC.cardNum[tmp][k] && flag == 0) {
                                    jpnC.cardNum[tmp + 1][k] *= 2;

                                    score += jpnC.cardNum[tmp + 1][k];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[tmp][k] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp++;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                } else if (kc.equals(move[3])) {
                    System.out.println("Move right");
                    for (int k = 0; k < t.row; k++) {
                        int flag = 0;
                        for (int k2 = t.col - 1; k2 >= 0; k2--) {
                            int tmp = k2;
                            while (tmp != t.col - 1) {
                                if (jpnC.cardNum[k][tmp + 1] == 0) {
                                    jpnC.cardNum[k][tmp + 1] = jpnC.cardNum[k][tmp];
                                    jpnC.cardNum[k][tmp] = 0;
                                } else if (jpnC.cardNum[k][tmp + 1] == jpnC.cardNum[k][tmp] && flag == 0) {
                                    jpnC.cardNum[k][tmp + 1] *= 2;

                                    score += jpnC.cardNum[k][tmp + 1];
                                    jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);

                                    jpnC.cardNum[k][tmp] = 0;
                                    flag = 1;
                                } else {
                                    break;
                                }
                                tmp++;
                            }
                        }
                    }
                    Graphics g = null;
                    jpnC.randomCard(g);
                    repaint();
                }

                for (int i = 0; i < jpnC.row; i++) {
                    for (int j = 0; j < jpnC.col; j++) {
                        System.out.print(jpnC.cardNum[i][j] + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println("+++++++");

                int check, c;
                while (1 == 1) {
                    check = 0;
                    c = 0;
                    for (int i = 0; i < jpnC.row; i++) {
                        for (int j = 0; j < jpnC.col; j++) {
                            if (jpnC.cardNum[i][j] == 0) {
                                check = 1;
                                break;
                            }
                        }
                        if (check == 1) {
                            break;
                        }
                    }
                    if (check == 1) {
                        break;
                    }

                    // Check if nearby card values are equal
                    for (int ii = 0; ii < jpnC.row; ii++) {
                        for (int jj = 0; jj < jpnC.col; jj++) {

                            if (ii != jpnC.row - 1 && jj != jpnC.col - 1) {
                                // check that the cards on the right and below are equal
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii + 1][jj]
                                        || jpnC.cardNum[ii][jj] == jpnC.cardNum[ii][jj + 1]) {
                                    c = 1;
                                }
                            } else if (ii != jpnC.row - 1 && jj == jpnC.col - 1) {
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii + 1][jj]) {
                                    c = 1;
                                }
                            } // check the last row card's valume
                            else if (ii == jpnC.row - 1 && jj != jpnC.col - 1) {
                                if (jpnC.cardNum[ii][jj] == jpnC.cardNum[ii][jj + 1]) {
                                    c = 1;
                                }
                            }

                        }

                    }

                    if (c == 1) {
                        break;
                    }

                    if (c != 1) {
                        JOptionPane.showMessageDialog(f, "No empty card!!");
                        // create random number
                        for (int i = 0; i < num.length; i++) {
                            num[i] = i;
                        }

                        for (int i = 0; i < arr.length; i++) {
                            n = (int) (Math.random() * (4 - i));
                            arr[i] = num[n];
                            for (int j = n; j < num.length - 1; j++) {
                                num[j] = num[j + 1];
                            }
                        }

                        for (int i = 0; i < 4; i++) {
                            move[i] = str[arr[i]];
                        }

                        for (int q = 0; q < jpnC.row; q++) {
                            for (int d = 0; d < jpnC.col; d++) {
                                // set card's num=0
                                jpnC.cardNum[q][d] = 0;
                            }
                        }

                        JOptionPane.showMessageDialog(f,
                                "Up :" + move[0] + " Down :" + move[1] + " Left :" + move[2] + " Right :" + move[3]);
                        score = 0;
                        jlbsc.setText("<html><body><p align=\"center\">Score<br/" + score);
                        Graphics g = null;
                        jpnC.randomCard(g);
                        jpnC.randomCard(g);
                        repaint();
                    }
                    break;
                }

            }

        }

    }

}