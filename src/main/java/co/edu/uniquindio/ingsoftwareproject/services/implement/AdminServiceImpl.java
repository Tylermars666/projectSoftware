package co.edu.uniquindio.ingsoftwareproject.services.implement;

import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ActualizarEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ControlTimexDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.NotificacionDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.CrearEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Registro;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.NotificacionRepoImpl;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.UserRepoImpl;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.NotificacionRepo;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.UserRepo;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.AdminService;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.UserService;
import co.edu.uniquindio.ingsoftwareproject.user.Alertas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    UserRepo userRepo = new UserRepoImpl();
    NotificacionRepo notificacionRepo = new NotificacionRepoImpl();
    UserService userService = new UserServiceImpl();

    @Override
    public List<ControlTimexDTO> listarEmpleados() throws Exception {

        List<Usuario> usuarios = userRepo.findAll();
        return resumenEmpleados(usuarios);
    }

    @Override
    public List<ControlTimexDTO> listarEmpleadosPorRut(String rut) throws Exception {

        Usuario usuario = userRepo.findByRut(rut);
        return resumenEmpleadoRut(usuario);
    }

    @Override
    public String registrarEmpleado(CrearEmpleadoDTO crearEmpleadoDTO) throws Exception {

        Usuario usuarioDB = userRepo.findByRut(crearEmpleadoDTO.rut());

        if(!(usuarioDB == null)){

            Alertas.alertar(TipoAlerta.ERROR,"Error de datos","Ya existe un empleado con ese rut");
            throw new Exception("Ya existe un empleado con ese rut");

        }

        Usuario usuario = Usuario.builder().rut(crearEmpleadoDTO.rut())
                .nombre(crearEmpleadoDTO.nombre()).direccion(crearEmpleadoDTO.direccion())
                .telefono(crearEmpleadoDTO.telefono()).tipo(crearEmpleadoDTO.tipoUsuario())
                .huella(crearEmpleadoDTO.huella()).build();



        return userRepo.save(usuario);
    }

    @Override
    public String actualizarEmpleado(ActualizarEmpleadoDTO actualizarEmpleadoDTO) throws Exception {

        Usuario usuarioDB = userRepo.findByRut(actualizarEmpleadoDTO.rut());

        if(usuarioDB == null){
            Alertas.alertar(TipoAlerta.ERROR,"Error de datos","No existe un empleado con ese rut");
            throw new Exception("No existe un empleado con ese rut: " + actualizarEmpleadoDTO.rut());
        }

        Registro registro = filtrarRegistroPorFecha(usuarioDB, actualizarEmpleadoDTO.fecha());

        if(!actualizarEmpleadoDTO.horaEntrada().isBlank()){

            registro.setEntrada(LocalDateTime.parse(actualizarEmpleadoDTO.fecha()
                    +"T"+actualizarEmpleadoDTO.horaEntrada()));

        }

        if(!actualizarEmpleadoDTO.horaSalida().isBlank()){
            registro.setSalida(LocalDateTime.parse(actualizarEmpleadoDTO.fecha()+
                    "T"+actualizarEmpleadoDTO.horaSalida()));
        }

        Usuario usuarioEditado = eliminarRegistro(registro, usuarioDB);
        usuarioEditado.getRegistros().add(registro);


        return userRepo.save(usuarioEditado);
    }



    @Override
    public ReporteDto generarReporte(String rut, int mes) throws Exception{

        return userService.generarReporte(rut,mes);

    }

    @Override
    public List<NotificacionDTO> listarNotificaciones() throws Exception {

        List<Notificacion> notificaciones = notificacionRepo.findAll();
        return resumenNotificacion(notificaciones);
    }

    //------------------------------Métodos AUX------------------------------------

    private List<ControlTimexDTO> resumenEmpleados(List<Usuario> usuarios) throws Exception {

        ArrayList<ControlTimexDTO> resumen = new ArrayList<>();

        for (Usuario usuario : usuarios) {

            for(Registro registro : usuario.getRegistros()) {

                resumen.add(new ControlTimexDTO(usuario.getRut(),
                        usuario.getNombre(),registro.getFecha(),
                        registro.getHoraEntrada(),registro.getHoraSalida()));

            }
        }

        return resumen;

    }

    private List<ControlTimexDTO> resumenEmpleadoRut (Usuario usuario) throws Exception {

        ArrayList<ControlTimexDTO> resumen = new ArrayList<>();

        for(Registro registro : usuario.getRegistros()) {

            resumen.add(new ControlTimexDTO(usuario.getRut(),usuario.getNombre(),
                    registro.getFecha(),registro.getHoraEntrada(),registro.getHoraSalida()));

        }

        return resumen;

    }

    private Registro filtrarRegistroPorFecha(Usuario usuarioDB, String fecha) {

        for(Registro registro : usuarioDB.getRegistros()) {

            if(registro.getFecha().equals(fecha)) {
                return registro;
            }

        }

        return null;

    }

    private Usuario eliminarRegistro(Registro registro, Usuario usuario) throws Exception {

        for(Registro registroU : usuario.getRegistros()) {

            if(registroU.getFecha().equals(registro.getFecha())) {

                usuario.getRegistros().remove(registroU);
            }

        }

        return usuario;

    }

    private List<NotificacionDTO> resumenNotificacion(List<Notificacion> notificaciones){

        ArrayList<NotificacionDTO> notificacionDTOS = new ArrayList<>();

        for (Notificacion notificacion : notificaciones) {

            notificacionDTOS.add(new NotificacionDTO(notificacion.getRutUsuario(),
                    notificacion.getNombreUsuario(),notificacion.getJustificacion()));

        }

        return notificacionDTOS;

    }

}
