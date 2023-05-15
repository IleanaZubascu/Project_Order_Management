package BusinessLogic.validators;

import Model.Clients;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientNameValidator implements Validator<Clients> {
    @Override
    public void validate(Clients clients) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+\\s+[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(clients.getNume());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Client's name is not a valid name!");
        }
    }
}
