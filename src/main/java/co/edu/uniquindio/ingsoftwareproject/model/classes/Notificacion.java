package co.edu.uniquindio.ingsoftwareproject.model.classes;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String rutUsuario;
    private String nombreUsuario;
    private String justificacion;
    private LocalDateTime fecha;
}
