package co.edu.umanizales.listdeleds.Model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private NodeDE tail;
    private int size;
    private List<Led> ledList = new ArrayList<>();

    public void add(Led led) {
        if (head == null) {
            head = new NodeDE(led);
        } else {
            NodeDE newNode = new NodeDE(led);
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }

        size++;
    }

    public void addToStart(Led led) {
        NodeDE newNode = new NodeDE(led);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    public void addToEnd(Led led) {
        NodeDE newLed = new NodeDE(led);
        if (this.head == null) {
            this.head = newLed;
        } else {
            NodeDE tail = this.head;
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
            tail.setNext(newLed);
            newLed.setPrevious(tail);
        }
        this.size++;
    }
/*
// Inicio del método: establecer la lista en su estado inicial
// Comenzar en el primer nodo de la lista
     // Recorrer cada nodo de la lista
     // Obtener los datos del LED almacenados en el nodo actual
     // Establecer el estado del LED en "apagado"
     // Establecer la fecha de apagado del LED en nulo
     // Establecer la fecha de encendido del LED en nulo
     // Avanzar al siguiente nodo en la lista
 // Fin del bucle de recorrido de la lista
 */

    public void restart() {
        NodeDE current = head;
        while (current != null) {
            Led data = current.getData();
            data.setState(false);
            data.setDateoff(null);
            data.setDateon(null);
            current = current.getNext();
        }
    }

    /*
    Primero, el método comprueba si la lista está vacía, verificando si el nodo head es nulo.
    Si la lista no está vacía, se establece una variable temporal temp como una referencia al primer nodo de la lista.
    A continuación, se recorre la lista mientras que current no sea nulo. Durante cada iteración del bucle, se realiza lo siguiente:
    Se obtiene el objeto Led correspondiente al nodo actual utilizando el método getData() de la clase NodeDE.
    Se establece el valor de state del objeto Led a true, indicando que la luz está encendida.
    Se establece el valor de dateon del objeto Led a la hora actual utilizando el método LocalTime.now(). Esto permite registrar la hora en que se encendió la luz.
    Se actualiza la variable temporal temp para apuntar al siguiente nodo de la lista.
    Después de recorrer toda la lista, el método turnOn habrá encendido todas las luces en la lista y registrado la hora en que se encendieron.
     */
    public void turnOn() {
        NodeDE current = head;
        while (current != null) {
            Led data = current.getData();
            data.setState(true);
            data.setDateon(LocalTime.now());
            current = current.getNext();
        }
    }

    /*
Comprobación de la lista: la función comienza comprobando si la lista está vacía, lo que se hace mediante una verificación del valor de la variable head. Si el valor de head es nulo, esto significa que la lista está vacía y la función no tiene nada que hacer.
Establecimiento de una variable temporal: si la lista no está vacía, la función establece una variable temporal llamada temp que apunta al primer nodo de la lista.
Recorrido de la lista: se inicia un bucle que recorre la lista a través de la variable temp. Durante cada iteración del bucle, se ejecutan una serie de instrucciones que modifican los objetos de tipo Led que se encuentran en los nodos de la lista.
Actualización de los objetos Led: dentro del bucle, se utiliza el método getData() de la clase NodeDE para obtener el objeto Led correspondiente al nodo actual. A continuación, se establece el valor de la propiedad state del objeto Led en false para indicar que la luz está apagada. También se establece el valor de la propiedad dateoff en el momento actual utilizando el método LocalDateTime.now().
Actualización de la variable temporal: finalmente, se actualiza la variable temporal temp para que apunte al siguiente nodo de la lista. Esto permite que el bucle recorra toda la lista hasta el final.
    */
    public void turnOff() {

        NodeDE current = head;
        while (current != null) {
            Led data = current.getData();
            data.setState(false);
            data.setDateoff(LocalTime.now());
            current = current.getNext();
        }
    }

    public void turnOnByid(int id) {
        NodeDE current = head;
        // Empezar en el primer nodo de la lista

        while (current != null) {
            if (current.getData().getId() == id) {
                // Si se encuentra el LED con el ID indicado

                current.getData().setState(true);
                // Encender el LED

                break;
                // Salir del bucle
            }

            current = current.getNext();
            // Avanzar al siguiente nodo
        }
    }

    public void turnOffById(int id) {
        NodeDE current = head;
        // Empezar en el primer nodo de la lista

        while (current != null) {
            if (current.getData().getId() == id) {
                // Si se encuentra el LED con el ID indicado

                current.getData().setState(false);
                // Apagar el LED

                break;
                // Salir del bucle
            }

            current = current.getNext();
            // Avanzar al siguiente nodo
        }
    }

    public void turnOnExtremesBytheHalf() {
        NodeDE temp = head;
        NodeDE tempEnd = tail;

        while (temp != null && tempEnd != null && temp != tempEnd && temp.getPrevious() != tempEnd) {
            temp.getData().setState(true);
            temp.getData().setDateon(LocalTime.now());
            tempEnd.getData().setState(true);
            tempEnd.getData().setDateon(LocalTime.now());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            temp.getData().setState(false);
            temp.getData().setDateoff(LocalTime.now());
            tempEnd.getData().setState(false);
            tempEnd.getData().setDateoff(LocalTime.now());

            temp = temp.getNext();
            tempEnd = tempEnd.getPrevious();
        }

        // Enciende el nodo del medio si la lista tiene un tamaño impar
        if (temp == tempEnd) {
            temp.getData().setState(true);
            temp.getData().setDateon(LocalTime.now());
            temp.getData().setDateoff(LocalTime.now().plusSeconds(1));
        }

        // Enciende los nodos de los extremos si la lista tiene un tamaño par
        if (temp.getPrevious() == tempEnd) {
            temp.getData().setState(true);
            tempEnd.getData().setState(true);
            temp.getData().setDateon(LocalTime.now());
            tempEnd.getData().setDateon(LocalTime.now());
            temp.getData().setDateoff(LocalTime.now().plusSeconds(1));
            tempEnd.getData().setDateoff(LocalTime.now().plusSeconds(1));
        }
    }

}
