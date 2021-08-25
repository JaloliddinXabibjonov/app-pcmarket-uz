package uz.pcmarket.apppcmarketuz.payload.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    String message;
    boolean succes;
}
