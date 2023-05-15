package BusinessLogic.validators;

import Model.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductNameValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(product.getNumeProdus());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Product's name is not valid!");
        }
    }
}
