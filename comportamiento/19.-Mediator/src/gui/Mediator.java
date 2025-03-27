package comportamiento.mediator.gui;

//Concrete mediator
public class Mediator implements IMediator {
 
	BtnVisualizar btnVisualizar;
    BtnBuscar btnBuscar;
    BtnReservar btnReservar;
    Etiqueta etiqueta;
     
    @Override
    public void registrarVisualizar(BtnVisualizar v) {
        btnVisualizar = v;
    }
 
    @Override
    public void registrarBuscar(BtnBuscar b) {
        btnBuscar = b;
    }
 
    @Override
    public void registrarReservar(BtnReservar r) {
        btnReservar = r;
    }
 
    @Override
    public void registrarEtiqueta(Etiqueta e) {
        etiqueta = e;
    }
 
    @Override
    public void reservar() {
        btnReservar.setEnabled(false);
        btnVisualizar.setEnabled(true);
        btnBuscar.setEnabled(true);
        etiqueta.setText("Reservando...");
    }
 
    @Override
    public void visualizar() {
        btnVisualizar.setEnabled(false);
        btnBuscar.setEnabled(true);
        btnReservar.setEnabled(true);
        etiqueta.setText("Visualizando...");
    }
 
    @Override
    public void buscar() {
        btnBuscar.setEnabled(false);
        btnVisualizar.setEnabled(true);
        btnReservar.setEnabled(true);
        etiqueta.setText("Buscando...");
    }
 
}
