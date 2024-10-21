import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Registro;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoUsuario;
import co.edu.uniquindio.ingsoftwareproject.repository.Singleton;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.UserRepoImpl;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UsuarioTest {

    UserRepoImpl userRepo = new UserRepoImpl();

    @Test
    public void fechaHora(){


        LocalDateTime fechaHora = LocalDateTime.now();

        //Fecha formateada con la hora
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = fechaHora.format(formato);
        System.out.println(horaFormateada);


        //Fecha formateada con Año, Mes, Día
        DateTimeFormatter formateo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaHora.format(formateo);
        System.out.println(fechaFormateada);


    }

    @Test
    public void serializar(){

        Registro registro = new Registro();
        ArrayList<Registro> registros = new ArrayList<>();
        registros.add(registro);

        Singleton.getInstance().crear("user",new Usuario("666","Jaimito","cra 14","7327003", TipoUsuario.CLIENTE,
                "imagen1Huella",registros,"jaimito1","0000"));

        /*Singleton.getInstance().crear("notificacion",new Notificacion("888","666","Jaimito","Me enfermé de lepra",
                LocalDateTime.now()));*/

        Singleton.getInstance().crear("user",new Usuario("1717","Omar","Santander","3207368522", TipoUsuario.ADMINISTRADOR,
                "imagen2Huella",null,"omar","0000"));


        Singleton.getInstance().crear("user",new Usuario("1718","Carlos","Tuluaquistán","Número privado", TipoUsuario.CLIENTE,
                "ImagenHuella3",registros,"carlos","0000"));


    }

    @Test
    public void deserializar(){

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) Singleton.getInstance().listar("user");

        for(Usuario usuario : usuarios){
            System.out.println(usuario.getNombre());
        }

        ArrayList<Notificacion> notificaciones = (ArrayList<Notificacion>) Singleton.getInstance().listar("notificacion");

        for(Notificacion notificacion : notificaciones){
            System.out.println(notificacion.getJustificacion());
        }
    }

    @Test
    public void sobreEscribirUsuario(){



        ArrayList<Registro> registros = new ArrayList<>();
        registros.add(new Registro(LocalDateTime.parse("2024-09-20T"+"07:31:06"),LocalDateTime.parse("2024-09-20T"+"17:31:06")));
        registros.add(new Registro(LocalDateTime.parse("2024-10-20T"+"07:31:06"),LocalDateTime.parse("2024-10-20T"+"17:31:06")));
        registros.add(new Registro(LocalDateTime.parse("2024-08-20T"+"07:31:06"),LocalDateTime.parse("2024-08-20T"+"17:31:06")));
        registros.add(new Registro(LocalDateTime.parse("2024-07-20T"+"07:31:06"),LocalDateTime.parse("2024-07-20T"+"17:31:06")));

        /*String response = userRepo.save(new Usuario("666","Jaimito","cra 14","7327003", TipoUsuario.CLIENTE,
                "imagen1Huella",registros,"jaimito1","0000"));*/

        String response2 = userRepo.save(new Usuario("1718","Carlos","Tuluaquistán","Número privado", TipoUsuario.CLIENTE,
                "ImagenHuella3",registros,"carlos","0000"));



        //System.out.println(response);
        //System.out.println(response2);


    }

    @Test
    public void buscarPorUserName(){

        Usuario user = userRepo.findByUsername("jaimito1");
        System.out.println(user.getUserName());

    }

    @Test
    public void getMes(){

        LocalDateTime fecha = LocalDateTime.now().plusMonths(5);
        System.out.println(fecha.getMonthValue());

    }

    @Test
    public void stringAFecha(){


        LocalDateTime fecha1 = LocalDateTime.now();
        System.out.println(fecha1);

        LocalDateTime fecha2 = LocalDateTime.parse("2024-10-20T"+"16:31:06");
        System.out.println(fecha2);

        LocalDateTime fecha3 = LocalDateTime.now().minusHours(8);
        System.out.println(fecha3);


    }

    @Test
    public void fechaParse(){

        String fechaStr = "2024-10-12";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaStr, formatter);
        int mes = fecha.getMonthValue();

        System.out.println(mes);

    }

}
