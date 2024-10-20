package co.edu.uniquindio.ingsoftwareproject.dto.userDto;

public record ReporteDto(
        String rut,
        String nombre,
        String descuentoSalud,
        String descuentoPension,
        String salarioBruto,
        String salarioNeto
) {
}
