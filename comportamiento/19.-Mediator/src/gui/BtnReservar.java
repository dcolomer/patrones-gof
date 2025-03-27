package comportamiento.mediator.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

// Clase colega
public class BtnReservar extends JButton implements Command {

	private static final long serialVersionUID = 1L;

	private IMediator mediator;
 
    public BtnReservar(ActionListener oyente, IMediator mediator) {
        super("Reserva");
        addActionListener(oyente);
        this.mediator = mediator;
        this.mediator.registrarReservar(this);
    }
 
    @Override
    public void execute() {
    	mediator.reservar();
    }
 
}
