package co.edu.umanizales.listdeleds.Controller;
import co.edu.umanizales.listdeleds.Controller.DTO.ResponseDTO;
import co.edu.umanizales.listdeleds.Model.Led;
import co.edu.umanizales.listdeleds.Service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping(path ="ledsde")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLeds(), null), HttpStatus.OK);
    }

    @PostMapping("/addled")
    public ResponseEntity<String> addLed(@RequestBody Led led) {
        listDEService.getLeds().add(led);
        return new ResponseEntity<>("LED added to the list", HttpStatus.OK);
    }
    @PostMapping("/addledtostart")
    public ResponseEntity<String> addToStart(@RequestBody Led led) {
        try {
            listDEService.getLeds().addToStart(led);
            return ResponseEntity.ok("Led added to start");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add led to start: " + e.getMessage());
        }
    }
    @PostMapping("/leds/addToEnd")
    public ResponseEntity<String> addToEnd(@RequestBody Led led) {
        listDEService.getLeds().addToEnd(led);
        return new ResponseEntity<>("Led added to end of list", HttpStatus.OK);
    }
    @PostMapping("/restart")
    public ResponseEntity<String> restart() {
        listDEService.getLeds().restart();
        return ResponseEntity.ok("All LEDs have been restarted.");
    }
    @PostMapping("/turnonleds")
    public ResponseEntity<String> turnOn() {
        try {
            listDEService.getLeds().turnOn();
            return ResponseEntity.ok("All LEDs turned on successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error turning on LEDs: " + e.getMessage());
        }
    }
    @PutMapping("/leds/off")
    public ResponseEntity<String> turnOff() {
        listDEService.getLeds().turnOff();
        return ResponseEntity.ok("All LEDs have been turned off.");
    }
    @PostMapping("/leds/turn-on/{id}")
    public ResponseEntity<String> turnOnById(@PathVariable int id) {
        listDEService.getLeds().turnOnByid(id);
        return ResponseEntity.ok(String.format("LED with ID %d has been turned on.", id));
    }
    @PostMapping("/leds/turn-on/{id}")
    public ResponseEntity<String> turnOffById(@PathVariable int id) {
        listDEService.getLeds().turnOffById(id);
        return ResponseEntity.ok(String.format("LED with ID %d has been turned off.", id));
    }
    @PostMapping("/turnonextremesbythehalf")
    public ResponseEntity<String> turnOnExtremesBytheHalf() {
        listDEService.getLeds().turnOnExtremesBytheHalf();
        return ResponseEntity.ok("All LEDs have been turned on and off.");
    }

}
