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
    public List<Led> print()  {
        ledList.clear();

        NodeDE temp = head;

        while (temp != null) {
            ledList.add(temp.getData());
            temp = temp.getNext();
        }

        return ledList;
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
/*
Creo el asistente que recorra la lista.
compruebo que la lista no este vacia. si no esta vacia entonces
Empiezo e recorrer los leds mediante un ciclo.
Si encuentro el led con la id proporcionada anteriormente lo defino como true
salgo del bucle.
paso al siguiente nodo
 */
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
    /*
    Creo el asistente que recorra la lista.
    compruebo que la lista no este vacia. si no esta vacia entonces
    Empiezo e recorrer los leds mediante un ciclo.
    Si encuentro el led con la id proporcionada anteriormente lo defino como false
    salgo del bucle.
    paso al siguiente nodo

     */
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
    /*
    Verificar si la lista está vacía. Si es así, lanzar una excepción.
    Crear una nueva lista vacía llamada listCP para almacenar los cambios.
    Inicializar un nodo temporal llamado temp con la cabeza de la lista original.
    Calcular el punto de inicio para el encendido y apagado de los elementos.
    Si la lista tiene un número impar de elementos, el punto de inicio será la mitad más uno.
    Si la lista tiene un número par de elementos, el punto de inicio será la mitad.
    Iniciar un bucle mientras el nodo temporal temp no sea nulo.
    Dentro del bucle, verificar si el contador es igual al punto de inicio.
    Si es así, guardar una referencia al siguiente nodo en tempNext.
    Encender el elemento asociado al nodo temporal estableciendo su estado en true.
    Registrar la fecha de encendido del elemento asociado al nodo temporal con el valor actual de tiempo.
    Luego, ejecutar otro bucle mientras el siguiente nodo después de tempNext no sea nulo.
    Pausar la ejecución por 1 segundo.
    Apagar los elementos asociados a temp y tempNext estableciendo su estado en false.
    Registrar la fecha de apagado de los elementos asociados a temp y tempNext con el valor actual de tiempo.
    Actualizar temp al nodo anterior y tempNext al siguiente nodo.
    Encender nuevamente los elementos asociados a temp y tempNext.
    Registrar la fecha de encendido de los elementos asociados a temp y tempNext con el valor actual de tiempo.
    Una vez finalizado el bucle interno, salir del bucle principal.
    Incrementar el contador y avanzar temp al siguiente nodo de la lista original.
    Actualizar la cabeza de la lista original con la cabeza de la lista modificada listCP.
     */

    public void turnOnExtremesBytheHalf() {

            if (head != null) {
                NodeDE temp = head;
                int count = 1;
                int start;

                if ((size % 2) != 0) {
                    start = (size / 2) + 1;

                    while (temp != null) {
                        if (count == start) {
                            NodeDE tempNext = temp;
                            temp.getData().setState(true);
                            temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));

                            while (tempNext.getNext() != null) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                temp.getData().setState(false);
                                temp.getData().setDateoff(LocalTime.from(LocalDateTime.now()));
                                tempNext.getData().setState(false);
                                tempNext.getData().setDateoff(LocalTime.from(LocalDateTime.now()));

                                temp = temp.getPrevious();
                                tempNext = tempNext.getNext();

                                temp.getData().setState(true);
                                temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                                tempNext.getData().setState(true);
                                tempNext.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                            }

                            break;
                        }
                        count++;
                        temp = temp.getNext();
                    }
                } else {
                    start = size / 2;

                    while (temp != null) {
                        if (count == start) {
                            NodeDE tempNext = temp.getNext();
                            temp.getData().setState(true);
                            temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                            tempNext.getData().setState(true);
                            tempNext.getData().setDateon(LocalTime.from(LocalDateTime.now()));

                            while (tempNext.getNext() != null) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                temp.getData().setState(false);
                                temp.getData().setDateoff(LocalTime.from(LocalDateTime.now()));
                                tempNext.getData().setState(false);
                                tempNext.getData().setDateoff(LocalTime.from(LocalDateTime.now()));

                                temp = temp.getPrevious();
                                tempNext = tempNext.getNext();

                                temp.getData().setState(true);
                                temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                                tempNext.getData().setState(true);
                                tempNext.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                            }

                            break;
                        }
                        count++;
                        temp = temp.getNext();
                    }
                }
            }
        }


    }
