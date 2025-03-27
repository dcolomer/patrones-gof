package comportamiento.mediator.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

// Clase colega
public class BtnBuscar extends JButton implements Command {
 
	private static final long serialVersionUID = 1L;
	
	private IMediator mediator;
 
    public BtnBuscar(ActionListener oyente, IMediator mediator) {
        super("Buscar");
        addActionListener(oyente);
        this.mediator = mediator;
        this.mediator.registrarBuscar(this);
    }
 
    @Override
    public void execute() {
    	mediator.buscar();
    }
 
}
