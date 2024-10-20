package co.edu.uniquindio.ingsoftwareproject.model.classes;


import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoUsuario;
import lombok.*;

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

    @Builder
    public Usuario(String rut, String nombre, String direccion, String telefono, TipoUsuario tipo, String huella, String userName, String password) {
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.huella = huella;
        this.userName = userName;
        this.password = password;
    }
}
