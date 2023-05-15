package BusinessLogic.validators;

import Model.Product;

public class ProductStockValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        if(product.getStoc()<=0)
        {
            throw new IllegalArgumentException("Product's stock is not valid!");
        }
    }
}
