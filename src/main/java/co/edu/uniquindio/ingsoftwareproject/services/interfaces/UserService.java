package co.edu.uniquindio.ingsoftwareproject.services.interfaces;

import co.edu.uniquindio.ingsoftwareproject.dto.registroDto.RegistroClienteDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;

import java.util.List;

public interface UserService {

    public List<RegistroClienteDTO> listarRegistros(String rut) throws Exception;
    public List<RegistroClienteDTO> listarRegistrosPorMes(int mes, String rut) throws Exception;
    public ReporteDto generarReporte(String rut, int mes) throws Exception;
    public String enviarNotificacion(String rut, String mensaje) throws Exception;

}
