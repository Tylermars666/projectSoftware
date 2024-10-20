import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Registro;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoUsuario;
import co.edu.uniquindio.ingsoftwareproject.repository.Singleton;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.UserRepoImpl;
import org.junit.jupiter.api.Test;

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

        Singleton.getInstance().crear("user",new Usuario("666","Jaimito","cra 14","7327003", TipoUsuario.CLIENTE,
                "imagen1Huella",null,"jaimito1","0000"));

        Singleton.getInstance().crear("notificacion",new Notificacion("888","666","Jaimito","Me enfermé de lepra",
                LocalDateTime.now()));


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
        registros.add(new Registro(LocalDateTime.now(),LocalDateTime.now().plusHours(8)));

        String response = userRepo.save(new Usuario("666","Jaimito","cra 14","7327003", TipoUsuario.CLIENTE,
                "imagen1Huella",registros,"jaimito1","0000"));

        System.out.println(response);


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


}
