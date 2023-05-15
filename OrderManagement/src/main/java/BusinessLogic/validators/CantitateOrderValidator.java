package BusinessLogic.validators;

import Model.Orders;

public class CantitateOrderValidator implements Validator<Orders> {
    @Override
    public void validate(Orders order) {
        if(order.getCantitate()<=0)
        {
            throw new IllegalArgumentException("Order's quantity is not valid!");
        }
    }
}
