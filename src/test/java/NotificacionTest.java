import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.NotificacionRepoImpl;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.NotificacionRepo;
import co.edu.uniquindio.ingsoftwareproject.services.implement.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class NotificacionTest {

    NotificacionRepo notificacionRepo = new NotificacionRepoImpl();
    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void buscarPorId(){

        Notificacion notificacion = notificacionRepo.findById("888");
        System.out.println(notificacion.getJustificacion());

    }

    @Test
    public void sobreEscribir(){

        Notificacion notificacion = new Notificacion("888","666","Jaimito","Me dio severa venerea", LocalDateTime.now());

        String response = notificacionRepo.save(notificacion);
        System.out.println(response);
    }

    @Test
    public void crearNotificacion(){

        Notificacion notificacion = new Notificacion("1094","666","Jaimito","Herpes en la boca", LocalDateTime.now());
        String response = notificacionRepo.save(notificacion);
        System.out.println(response);

    }

    @Test
    public void generarId(){
        System.out.println(userService.generarId());
    }


}
