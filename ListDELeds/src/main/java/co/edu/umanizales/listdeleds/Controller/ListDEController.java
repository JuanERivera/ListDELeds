package co.edu.umanizales.listdeleds.Controller;
import co.edu.umanizales.listdeleds.Controller.DTO.ResponseDTO;
import co.edu.umanizales.listdeleds.Model.Led;
import co.edu.umanizales.listdeleds.Service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="ledsde")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLeds().print(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/add/{id}")
    public ResponseEntity<ResponseDTO> add(@PathVariable int id) {


        listDEService.getLeds().add(new Led(id));


        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado un nuevo led",
                null), HttpStatus.OK);

    }

    @GetMapping("/addledtostart/{id}")
    public ResponseEntity<String> addToStart(@PathVariable int id) {
        try {
            listDEService.getLeds().addToStart(new Led(id));
            return ResponseEntity.ok("Led added to start");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add led to start: " + e.getMessage());
        }
    }

    @GetMapping("/addToEnd")
    public ResponseEntity<String> addToEnd(@PathVariable int id) {
        listDEService.getLeds().addToEnd(new Led(id));
        return new ResponseEntity<>("Led added to end of list", HttpStatus.OK);
    }

    @GetMapping("/restart")
    public ResponseEntity<ResponseDTO> restart() {
        listDEService.getLeds().restart();
        return new ResponseEntity<>(new ResponseDTO(200, "Se han reiniciado los leds", null), HttpStatus.OK);
    }

    @GetMapping("/turnonleds")
    public ResponseEntity<ResponseDTO> turnOn() {
        try {
            listDEService.getLeds().turnOn();
            return new ResponseEntity<>(new ResponseDTO(200, "Se han encendido los leds", null), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/off")
    public ResponseEntity<ResponseDTO> turnOff() {
        listDEService.getLeds().turnOff();
        return new ResponseEntity<>(new ResponseDTO(200, "Se han apagado los leds", null), HttpStatus.OK);
    }

    @GetMapping("/turn-on/{id}")
    public ResponseEntity<ResponseDTO> turnOnById(@PathVariable int id) {
        listDEService.getLeds().turnOnByid(id);
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha encendido el led con la id dada", null), HttpStatus.OK);
    }
    @GetMapping("/turn-off/{id}")
    public ResponseEntity<ResponseDTO> turnOffById(@PathVariable int id) {
        listDEService.getLeds().turnOffById(id);
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha apagado el led con la id dada", null), HttpStatus.OK);
    }
    @GetMapping("/turnonextremesbythehalf")
    public ResponseEntity<ResponseDTO> turnOnExtremesBytheHalf() {
        listDEService.getLeds().turnOnExtremesBytheHalf();
        return new ResponseEntity<>(new ResponseDTO(200, "Se han dejado encendidos los leds de los extremos", null), HttpStatus.OK);
    }

}
