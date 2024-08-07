package tin.dam.kafkademo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    private String toEmail;
    private String toName;
    private String subject;
    private String content;


}
