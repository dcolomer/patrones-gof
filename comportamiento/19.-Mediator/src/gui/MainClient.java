package comportamiento.mediator.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClient extends JFrame implements ActionListener {
 
	private static final long serialVersionUID = 1L;
	private IMediator mediator = new Mediator();
 
    public MainClient() {
        JPanel p = new JPanel();
        p.add(new BtnVisualizar(this, mediator));
        p.add(new BtnReservar(this, mediator));
        p.add(new BtnBuscar(this, mediator));
        getContentPane().add(new Etiqueta(mediator), "North");
        getContentPane().add(p, "South");
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    @Override
    public void actionPerformed(ActionEvent evt) {
        Command command = (Command) evt.getSource();
        command.execute();
    }
 
    public static void main(String[] args) {      	
        EventQueue.invokeLater(new Runnable() {
        	@Override
			public void run() {
				MainClient instancia = new MainClient();
		    	instancia.setLocationRelativeTo(null);					
				instancia.setVisible(true);
			}
		});	
    }
 
}
