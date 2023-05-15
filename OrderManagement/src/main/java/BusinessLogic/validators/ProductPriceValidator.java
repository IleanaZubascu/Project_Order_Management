package BusinessLogic.validators;

import Model.Product;

public class ProductPriceValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        if(product.getPret()<=0)
        {
            throw new IllegalArgumentException("Product's price is not valid!");
        }

    }
}
