package comportamiento.observer.ventas_con_patron;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CuadroMandosVentas extends JFrame implements PropertyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtVentas;
	
	/* Ya no es necesario
	 * public void setTxtVentas(String ventas) {
		txtVentas.setText(ventas);
	}*/

	/*
	 * Método que permite recibir notificaciones del subject.
	 * Notad que el primer parámetro es una referencia al
	 * propio subject. En ciertas circunstancias puede ser
	 * útil, aunque en nuestro caso no la necesitamos
	 */
	@Override
	public void onPropertyEvent(VentaManager mng, String propiedad, float valor) {		
		if (propiedad.equals("venta.total")) {
			txtVentas.setText(valor + "");
			System.out.println(valor);
		}		
	}
	
	public CuadroMandosVentas(VentaManager mng) {
		
		// Registrarse como listener en el objeto VentaManager
		mng.addPropertyListener(this);
		
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
		
		txtVentas = new JTextField("0.00");
		txtVentas.setBounds(159, 82, 128, 34);
		contentPane.add(txtVentas);
		txtVentas.setColumns(10);
	}
}
