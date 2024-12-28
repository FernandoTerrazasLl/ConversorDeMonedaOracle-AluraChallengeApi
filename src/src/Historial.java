import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<String> historial;

    public Historial() {
        this.historial = new ArrayList<>();
    }

    public void agregar(String consulta) {
        historial.add(consulta);
    }

    public void mostrar() {
        System.out.println("Historial de conversiones:");
        for (String doc : historial) {
            System.out.println(doc);
        }
    }
}

