package co.edu.uniquindio.ingsoftwareproject.dto.userDto;

import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoUsuario;

public record CrearEmpleadoDTO(
        String username,
        String password,
        String rut,
        String nombre,
        String direccion,
        String telefono,
        TipoUsuario tipoUsuario,
        String huella
) {
}
