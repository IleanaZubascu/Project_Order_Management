package BusinessLogic.validators;

import DataAccess.ClientDAO;
import Model.Clients;
import Model.Orders;

public class OrderCIDValidator implements Validator<Orders> {

    @Override
    public void validate(Orders orders) {
        Clients client= ClientDAO.findClientById(orders.getCID());
        if(client==null)
        {
            throw new IllegalArgumentException("Client's ID for order is not valid!");
        }
    }
}
