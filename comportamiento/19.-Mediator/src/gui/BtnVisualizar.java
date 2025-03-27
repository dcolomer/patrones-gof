package comportamiento.mediator.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

// Clase colega
public class BtnVisualizar extends JButton implements Command {
     
	private static final long serialVersionUID = 1L;
	
	private IMediator mediator;
 
    public BtnVisualizar(ActionListener oyente, IMediator mediator) {
        super("Visualizar");
        addActionListener(oyente);
        this.mediator = mediator;
        this.mediator.registrarVisualizar(this);
    }
 
    @Override
    public void execute() {
    	this.mediator.visualizar();
    }
 
}
