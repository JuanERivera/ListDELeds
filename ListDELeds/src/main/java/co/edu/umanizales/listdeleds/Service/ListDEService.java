package co.edu.umanizales.listdeleds.Service;
import co.edu.umanizales.listdeleds.Model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE leds;


    public ListDEService() {
        leds = new ListDE();

    }

}
