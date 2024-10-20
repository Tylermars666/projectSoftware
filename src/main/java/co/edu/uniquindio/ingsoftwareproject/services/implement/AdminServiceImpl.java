package co.edu.uniquindio.ingsoftwareproject.services.implement;

import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ActualizarEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ControlTimexDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.NotificacionDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.CrearEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public List<ControlTimexDTO> listarEmpleados() throws Exception {
        return List.of();
    }

    @Override
    public List<ControlTimexDTO> listarEmpleadosPorRut(String rut) throws Exception {
        return List.of();
    }

    @Override
    public String registrarEmpleado(CrearEmpleadoDTO crearEmpleadoDTO) throws Exception {
        return "";
    }

    @Override
    public String actualizarEmpleado(ActualizarEmpleadoDTO actualizarEmpleadoDTO) throws Exception {
        return "";
    }

    @Override
    public ReporteDto generarReporte(String rut) throws Exception{
        return null;
    }

    @Override
    public List<NotificacionDTO> listarNotificaciones() throws Exception {
        return List.of();
    }

    //------------------------------Métodos AUX------------------------------------
}
