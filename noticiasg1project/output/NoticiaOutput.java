package br.com.ifs.noticiasg1project.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaOutput {

    private String titulo;
    @JsonFormat(pattern = "dd/mm/yyyy", timezone = "America/Maceio")
    private String dt_publicacao;

}
