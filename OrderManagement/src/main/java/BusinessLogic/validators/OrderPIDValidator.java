package BusinessLogic.validators;

import DataAccess.ProductDAO;
import Model.Orders;
import Model.Product;

public class OrderPIDValidator implements Validator<Orders> {
    @Override
    public void validate(Orders orders) {
        Product product= ProductDAO.findProductById(orders.getPID());
        if(product==null)
        {
            throw new IllegalArgumentException("Product's ID for order is not valid!");
        }
    }
}
