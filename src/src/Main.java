import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        List<String> monedas = Arrays.asList(
                "ARS", "ARS", "ARS", "ARS", "ARS",
                "BOB", "BOB", "BOB", "BOB", "BOB",
                "BRL", "BRL", "BRL", "BRL", "BRL",
                "CLP", "CLP", "CLP", "CLP", "CLP",
                "COP", "COP", "COP", "COP", "COP",
                "USD", "USD", "USD", "USD", "USD"
        );

        List<String> cambio = Arrays.asList(
                "BOB", "BRL", "CLP", "COP", "USD",
                "ARS", "BRL", "CLP", "COP", "USD",
                "ARS", "BOB", "CLP", "COP", "USD",
                "ARS", "BOB", "BRL", "COP", "USD",
                "ARS", "BOB", "BRL", "CLP", "USD",
                "ARS", "BOB", "BRL", "CLP", "COP"
        );

        Historial historial = new Historial();

        while(true) {
            System.out.println("********************");
            System.out.println("""
                    Conversion de Monedas de:
                    1) ARS -> BOB |6) BOB -> ARS  |11) BRL -> ARS |16) CLP -> ARS |21) COP -> ARS |26) USD -> ARS
                    2) ARS -> BRL |7) BOB -> BRL  |12) BRL -> BOB |17) CLP -> BOB |22) COP -> BOB |27) USD -> BOB
                    3) ARS -> CLP |8) BOB -> CLP  |13) BRL -> CLP |18) CLP -> BRL |23) COP -> BRL |28) USD -> BRL
                    4) ARS -> COP |9) BOB -> COP  |14) BRL -> COP |19) CLP -> COP |24) COP -> CLP |29) USD -> CLP
                    5) ARS -> USD |10) BOB -> USD |15) BRL -> USD |20) CLP -> USD |25) COP -> USD |30) USD -> COP

                    31) Salir  32) Historial
                    """);

            System.out.println("Seleccione opcion de cambio:");
            int opcion = scanner.nextInt();

            if(opcion==31){
                break;
            }else if(opcion==32){
                System.out.println("Imprimiendo Historial");
                historial.mostrar();
            }else {
                ApiConexion conexion = new ApiConexion(monedas.get(opcion - 1));
                Map<String, Double> result = conexion.getApiResults();

                Double cambio_actual = result.get(cambio.get(opcion - 1));
                System.out.println("Ingrese cantidad de dinero a cambiar:");
                Double cantidad_dinero = scanner.nextDouble();

                String query="De " + cantidad_dinero + monedas.get(opcion - 1) + " a "
                        + cambio.get(opcion - 1) + " da un total de " + cantidad_dinero * cambio_actual;

                System.out.println(query);
                historial.agregar("- "+query);
            }
        }
        System.out.println("Final del programa");
    }
}