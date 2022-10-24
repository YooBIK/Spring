package hello.itemservice.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemUpdateParamDto {

    private String itemName;
    private Integer itemPrice;
    private Integer itemQuantity;
}
