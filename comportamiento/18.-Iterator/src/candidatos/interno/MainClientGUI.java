package comportamiento.iterator.candidatos.interno;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static final String MOSTRAR_TODOS = "Mostrar todos";
	static final String SALIR = "Salir";
	
	private JTextArea txtCandidatosSeleccionados;

	public MainClientGUI() throws Exception {
		super("Ejemplo Patron Iterator (interno)");

		// Crear los controles graficos
		txtCandidatosSeleccionados = new JTextArea(15, 20);		
		txtCandidatosSeleccionados.setEditable(false);

		JLabel lblCandidatosSeleccionados = new JLabel("Lista :");
		
		// Botones
		
		ListenerBoton vf = new ListenerBoton(this);
		
		JButton btnGetCandidatosSeleccionados = new JButton(MainClientGUI.MOSTRAR_TODOS);
		btnGetCandidatosSeleccionados.setMnemonic(KeyEvent.VK_S);
		
		JButton btnSalir = new JButton(MainClientGUI.SALIR);
		btnSalir.setMnemonic(KeyEvent.VK_X);
		
		btnGetCandidatosSeleccionados.addActionListener(vf);
		btnSalir.addActionListener(vf);

		// Ponemos los botones en un panel separado
		JPanel botonera = new JPanel();
		JPanel panel = new JPanel();

		GridBagLayout gridbag2 = new GridBagLayout();
		panel.setLayout(gridbag2);
		panel.add(btnGetCandidatosSeleccionados);
		panel.add(btnSalir);

		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gridbag2.setConstraints(btnGetCandidatosSeleccionados, gbc2);
		gbc2.gridx = 3;
		gbc2.gridy = 0;
		gridbag2.setConstraints(btnSalir, gbc2);

		// ****************************************************
		GridBagLayout gridbag = new GridBagLayout();
		botonera.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();

		botonera.add(lblCandidatosSeleccionados);
		botonera.add(txtCandidatosSeleccionados);
		botonera.add(panel);

		gbc.insets.top = 5;
		gbc.insets.bottom = 5;
		gbc.insets.left = 5;
		gbc.insets.right = 5;

		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gridbag.setConstraints(lblCandidatosSeleccionados, gbc);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gridbag.setConstraints(txtCandidatosSeleccionados, gbc);

		gbc.insets.left = 2;
		gbc.insets.right = 2;
		gbc.insets.top = 40;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gridbag.setConstraints(panel, gbc);
		
		// Añadimos los botones al frame
		Container contentPane = getContentPane();
		contentPane.add(botonera, BorderLayout.CENTER);		
	}

	public static void main(String[] args) throws Exception {

		JFrame frame = new MainClientGUI();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.setSize(600, 500);
		frame.setVisible(true);
	}

	public void setSelectedCandidates(String selectedCandidates) {
		txtCandidatosSeleccionados.setText(selectedCandidates);
	}

}

class ListenerBoton implements ActionListener {
	MainClientGUI manager;

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(MainClientGUI.SALIR)) {
			System.exit(1);
		}
		if (e.getActionCommand().equals(MainClientGUI.MOSTRAR_TODOS)) {
			Candidatos candidatos = new Candidatos();
			String candidatosSeleccionados = "Nombre --- Empresa --- Localidad"
					+ "\n" + "--------------------------------------";

			while (candidatos.hasNext()) {
				Candidato c = (Candidato) candidatos.next();
				candidatosSeleccionados = candidatosSeleccionados + "\n" + c.getNombre()
						+ " - " + c.getEmpresa() + " - "
						+ c.getLocalidad();
			}
			manager.setSelectedCandidates(candidatosSeleccionados);
		}
	}

	public ListenerBoton() {
	}

	public ListenerBoton(MainClientGUI inmanager) {
		manager = inmanager;
	}

}
