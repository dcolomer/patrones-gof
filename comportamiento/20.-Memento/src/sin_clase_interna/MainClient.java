package comportamiento.memento.sin_clase_interna;

public class MainClient {
    static public void main(String[] args) {
        String crlf = System.getProperties().getProperty("line.separator");

        Originator originator = new Originator();
        CareTaker caretaker = new CareTaker(originator);

        originator.write("Con diez cañones por banda," + crlf);
        originator.write("viento en popa, a toda vela," + crlf);
        caretaker.save(); // Primera instantánea
        
        originator.write("no corta el mar, sino vuela" + crlf);
        originator.write("un velero bergantín." + crlf);
        caretaker.save(); // Segunda instantánea
        
        originator.write("Bajel pirata que llaman," + crlf);
        originator.write("por su bravura, el Temido," + crlf);
        originator.write("en todo mar conocido, " + crlf);
        originator.write("del uno al otro confín. " + crlf);
        caretaker.save(); // Tercera instantánea
        originator.print();

        // Establecer el estado a la segunda instantanea
        caretaker.putBack(1);
        originator.print();

        // Establecer el estado a la primera instantanea
        caretaker.putBack(0);
        originator.print();

        // Establecer el estado a la tercera instantanea
        caretaker.putBack(2);
        originator.print(); 
    } 
}
