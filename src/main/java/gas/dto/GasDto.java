package gas.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class GasDto {
    private int id;
    private int previous;
    private int current;
    private int spent;
    private float amount;
    private LocalDateTime dateTransferred;
    private LocalDateTime datePaid;
}
