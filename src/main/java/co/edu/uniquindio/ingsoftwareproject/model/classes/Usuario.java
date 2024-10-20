package co.edu.uniquindio.ingsoftwareproject.model.classes;


import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String rut;
    private String nombre;
    private String direccion;
    private String telefono;
    private TipoUsuario tipo;
    private String huella;
    private ArrayList<Registro> registros;

    private String userName;
    private String password;

}
