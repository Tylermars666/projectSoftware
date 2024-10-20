package co.edu.uniquindio.ingsoftwareproject.services.interfaces;

import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ActualizarEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ControlTimexDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.NotificacionDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.CrearEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;

import java.util.List;

public interface AdminService {

    public List<ControlTimexDTO> listarEmpleados()throws Exception;
    public List<ControlTimexDTO> listarEmpleadosPorRut(String rut)throws Exception;
    public String registrarEmpleado(CrearEmpleadoDTO crearEmpleadoDTO)throws Exception;
    public String actualizarEmpleado(ActualizarEmpleadoDTO actualizarEmpleadoDTO)throws Exception;
    public ReporteDto generarReporte(String rut, int mes)throws Exception;
    public List<NotificacionDTO> listarNotificaciones()throws Exception;



}
