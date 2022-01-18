package kg.easyit.onlineshop.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetOrdersByBasketIdRequest {
    private Long basketId;
}
