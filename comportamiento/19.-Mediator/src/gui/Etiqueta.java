package comportamiento.mediator.gui;

import java.awt.Font;

import javax.swing.JLabel;

public class Etiqueta extends JLabel {
 
	private static final long serialVersionUID = 1L;

	public Etiqueta(IMediator mediator) {
        super("Aplicacion iniciada...");
        mediator.registrarEtiqueta(this);
        setFont(new Font("Arial", Font.BOLD, 24));
    }
 
}
