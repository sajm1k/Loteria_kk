package loto;

import java.awt.BorderLayout;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Loteria_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_pravy;
	private JButton btnNoveZrebovanie;
	private JTextField textVypis;
	private JButton btnHistoria;
	private JButton btnVypisZreby;
	private JButton btnLoteriaInfo;
	private JPanel panel_dolny;
	private JButton btnNewButton_2;
	private JButton btnPridajZrebManual;
	private JButton btnVyhodnotenie;
	private JButton btnPridajZrebAuto;
	private JTextField textVysledkyZrebovania;
	private JLabel lblNewLabel;
	int pocitadlo1 = 0;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loteria_GUI frame = new Loteria_GUI();
					frame.setTitle("KENO 10 ZREBOVANIE");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	/**
	 * Create the frame.
	 */
	public Loteria_GUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_pravy = new JPanel();
		contentPane.add(panel_pravy, BorderLayout.EAST);
		panel_pravy.setLayout(new BoxLayout(panel_pravy, BoxLayout.Y_AXIS));
		
		btnNoveZrebovanie = new JButton("Nove Zrebovanie");
		btnNoveZrebovanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noveZrebovanie();
			}
		});
		panel_pravy.add(btnNoveZrebovanie);


		
		lblNewLabel = new JLabel();
		panel_pravy.add(lblNewLabel);
		
		btnHistoria = new JButton("Historia Zrebovania");
		panel_pravy.add(btnHistoria);
		
		btnVypisZreby = new JButton("Vypis Posledny Zreb");
		btnVypisZreby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vypisTikety();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_pravy.add(btnVypisZreby);
		
		btnLoteriaInfo = new JButton("Info Loteria");
		btnLoteriaInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loteriaInfo();
			}
		});
		
		btnNewButton = new JButton("Vypis Zreby");
		panel_pravy.add(btnNewButton);
		panel_pravy.add(btnLoteriaInfo);
		
		textVypis = new JTextField();
		textVypis.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textVypis, BorderLayout.CENTER);

		
		panel_dolny = new JPanel();
		contentPane.add(panel_dolny, BorderLayout.SOUTH);
		
		btnPridajZrebAuto = new JButton("Pridaj Zreb auto");
		btnPridajZrebAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novyZreb();
			}
		});
		panel_dolny.add(btnPridajZrebAuto);
		
		btnPridajZrebManual = new JButton("Pridaj Zreb manual");
		btnPridajZrebManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pridajZrebManual();
			}
		});
		panel_dolny.add(btnPridajZrebManual);
		
		btnVyhodnotenie = new JButton("Vyhodnotenie");
		panel_dolny.add(btnVyhodnotenie);
		
		btnNewButton_2 = new JButton("New button");
		panel_dolny.add(btnNewButton_2);
		
		textVysledkyZrebovania = new JTextField();
		textVysledkyZrebovania.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textVysledkyZrebovania.setHorizontalAlignment(SwingConstants.LEADING);
		textVysledkyZrebovania.setText("Vysledky Zrebovania");
		contentPane.add(textVysledkyZrebovania, BorderLayout.NORTH);
		textVysledkyZrebovania.setColumns(10);
		textVysledkyZrebovania.setEditable(false);
	}
	
	public void novyZreb() {
		Loteria loteria = new Loteria("Loto", 1000, 99, 10);
		Zreb zreb = loteria.pridajZrebovanie();
		textVypis.setText(zreb.getInfo());
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tikety.txt", true))) {
            bw.write((zreb.getInfo()));
            bw.newLine();
            bw.flush();
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Tiket zapisany");
    }


    catch (Exception e){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Do suboru sa  nepodarilo zapisat");
    }
		
	}
	
	public void loteriaInfo() {
		Loteria loteria = new Loteria("Loto", 1000, 99, 10);
		textVypis.setText("Nazov: " + loteria.getNazov() + " Peniaze: " +  loteria.getPeniaze() + " Cisla Od: " + loteria.getCislaOd() + " Cisla Do: " + loteria.getCislaDo() + " Pocet cisel: " + loteria.getPocet());
		
		
		
	}
	
	public void vypisTikety() throws Exception {
		int zorad  = JOptionPane.showConfirmDialog(this, "Chces vypisat Zreb?");
		if (zorad == JOptionPane.YES_OPTION) {
		File file = new File ("C:\\Users\\Student\\eclipse-workspace\\loteria.zip_expanded\\Loteria_kk\\Tikety.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		

		String st;
		while((st = br.readLine()) != null) {
			textVypis.setText(st);
		}
		}
	}
	public void noveZrebovanie() {
		Loteria loteria = new Loteria("Loto", 1000, 99, 10);
		int [] cisla = loteria.vyzrebujCisla();
		String cislaS = Arrays.toString(cisla);
		//textVypis.setText("" + Arrays.toString(loteria.vyzrebujCisla()));
		textVypis.setText(cislaS);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Zrebovania.txt", true))) {
            bw.write(cislaS);
            bw.newLine();
            bw.flush();
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Dnesne zrebovanie zapisane");
    }


    catch (Exception e){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Do suboru sa  nepodarilo zapisat");
    }
		
	}
	
	public void pridajZrebManual() {
		String value = JOptionPane.showInputDialog("Zadaj 10 cisel od 1 do 99:");
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		textVypis.setText("Datum: " + dateFormat.format(date) + " | " + " Cisla: " + value);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tikety.txt", true))) {
            bw.write(("Datum: " + dateFormat.format(date) + " | " + " Cisla: " + value));
            bw.newLine();
            bw.flush();
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Tiket zapisany");
    }


    catch (Exception e){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Do suboru sa  nepodarilo zapisat");
    }
	}
	
}
