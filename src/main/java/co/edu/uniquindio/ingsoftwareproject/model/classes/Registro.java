package co.edu.uniquindio.ingsoftwareproject.model.classes;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class Registro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDateTime entrada;
    private LocalDateTime salida;

    public Registro(){

        entrada = LocalDateTime.parse("1111-11-11T00:00:00");
        salida = LocalDateTime.parse("1111-11-11T00:00:00");
    }

    public int getTotalHoras(){

        return (int)Duration.between(entrada, salida).toHours();

    }

    public String getHoraEntrada(){

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        return entrada.format(formato);

    }

    public String getHoraSalida(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        return salida.format(formato);
    }

    public String getFecha(){

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return entrada.format(formato);

    }


}
