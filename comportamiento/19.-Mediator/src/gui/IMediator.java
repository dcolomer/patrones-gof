package comportamiento.mediator.gui;

//Abstract Mediator
public interface IMediator {
    void reservar();
    void visualizar();
    void buscar();
    void registrarReservar(BtnReservar objReservar);
    void registrarVisualizar(BtnVisualizar objVisualizar);
    void registrarBuscar(BtnBuscar objBuscar);    
    void registrarEtiqueta(Etiqueta objEtiqueta);
}
