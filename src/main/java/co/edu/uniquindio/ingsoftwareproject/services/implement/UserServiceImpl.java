package co.edu.uniquindio.ingsoftwareproject.services.implement;

import co.edu.uniquindio.ingsoftwareproject.dto.registroDto.RegistroClienteDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Registro;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.NotificacionRepoImpl;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.UserRepoImpl;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.NotificacionRepo;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.UserRepo;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.UserService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserRepo userRepo = new UserRepoImpl();
    NotificacionRepo notificacionRepo = new NotificacionRepoImpl();
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LONGITUD_COMBINACION = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public List<RegistroClienteDTO> listarRegistros(String rut) throws Exception{

        List<Registro> registros = userRepo.findByRut(rut).getRegistros();

        return resumenRegistros(registros);
    }

    @Override
    public List<RegistroClienteDTO> listarRegistrosPorMes(int mes, String rut) throws Exception{

        List<Registro> registros = userRepo.findByRut(rut).getRegistros();
        List<Registro> registrosPorMes = registrosPorMes(mes, registros);

        return resumenRegistros(registrosPorMes);
    }

    @Override
    public ReporteDto generarReporte(String rut, int mes)throws Exception {

        Usuario usuario = userRepo.findByRut(rut);
        String nombre = usuario.getNombre();
        int horasMes = horasPorMes(mes, usuario.getRegistros());
        double salarioBruto =(double) horasMes *8000;
        double descuentoSalud = salarioBruto*0.04;
        double descuentoPension = salarioBruto*0.05;
        double salarioNeto =  salarioBruto-(descuentoSalud+descuentoPension);

        return new ReporteDto(rut, nombre,String.valueOf(descuentoSalud), String.valueOf(descuentoPension),
                String.valueOf(salarioBruto), String.valueOf(salarioNeto));
    }

    @Override
    public String enviarNotificacion(String rut, String mensaje) throws Exception{

        Usuario usuario = userRepo.findByRut(rut);
        String response = notificacionRepo.save(new Notificacion(generarId(),rut, usuario.getNombre(), mensaje, LocalDateTime.now()));
        return response;
    }

    //------------------------------Métodos AUX------------------------------------

    private List<RegistroClienteDTO> resumenRegistros(List<Registro> registros){

        ArrayList<RegistroClienteDTO> registrosDTO = new ArrayList<>();

        for (Registro registro : registros) {

            registrosDTO.add(new RegistroClienteDTO(registro.getFecha()
                    ,registro.getHoraEntrada(),registro.getHoraSalida(),
                    String.valueOf(registro.getTotalHoras())));

        }

        return registrosDTO;

    }

    private List<Registro> registrosPorMes(int mes, List<Registro> registros){

        ArrayList<Registro> registrosFiltrados = new ArrayList<>();

        for(Registro registro : registros){

            if(registro.getEntrada().getMonthValue()==mes){

                registrosFiltrados.add(registro);

            }
        }

        return registrosFiltrados;

    }

    private int horasPorMes (int mes, List<Registro> registros){

        int horas = 0;

        for(Registro registro : registros){

            if(registro.getEntrada().getMonthValue()==mes){
                horas = horas + registro.getTotalHoras();
            }
        }

        return horas;

    }

        public  String generarId() {
            StringBuilder combinacion = new StringBuilder(LONGITUD_COMBINACION);

            for (int i = 0; i < LONGITUD_COMBINACION; i++) {
                int indice = RANDOM.nextInt(CARACTERES.length());
                combinacion.append(CARACTERES.charAt(indice));
            }

            return combinacion.toString();
        }


}
