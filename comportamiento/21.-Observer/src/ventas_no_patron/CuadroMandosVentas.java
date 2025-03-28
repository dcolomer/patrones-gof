package comportamiento.observer.ventas_no_patron;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CuadroMandosVentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtVentas;

	public void setTxtVentas(String ventas) {
		txtVentas.setText(ventas);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CuadroMandosVentas frame = new CuadroMandosVentas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CuadroMandosVentas() {
		setTitle("Cuadro de mandos VENTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVentas = new JLabel("Ventas");
		lblVentas.setBounds(33, 91, 70, 15);
		contentPane.add(lblVentas);
		
		txtVentas = new JTextField();
		txtVentas.setBounds(159, 82, 128, 34);
		contentPane.add(txtVentas);
		txtVentas.setColumns(10);
	}
}
