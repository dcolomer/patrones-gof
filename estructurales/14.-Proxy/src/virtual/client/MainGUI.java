package estructurales.proxy.virtual.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import estructurales.proxy.virtual.ProxyImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	private JButton btnCargarImagen = new JButton("Cargar");
	
	private ProxyImageIcon proxy = 
			new ProxyImageIcon("estructurales/proxy/virtual/recursos/real.jpg");	
	private JLabel etiImagen = new JLabel(proxy);	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainGUI().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Constructor
	 */
	private MainGUI() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		
		btnCargarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cargarImagen();				
			}
		});
		btnCargarImagen.setVerticalAlignment(SwingConstants.BOTTOM);
		
		panel.add("Center", etiImagen);
		panel.add("South", btnCargarImagen);
		setContentPane(panel);		
	}

	protected void cargarImagen() {
		proxy.load(this);
		btnCargarImagen.setEnabled(false);		
	}

}
