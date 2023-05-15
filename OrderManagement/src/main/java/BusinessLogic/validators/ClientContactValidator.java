package BusinessLogic.validators;

import Model.Clients;

public class ClientContactValidator implements Validator<Clients> {
    @Override
    public void validate(Clients clients) {
        if(!clients.getContact().matches("^0[0-9]{9}$"))
        {
            throw new IllegalArgumentException("Client's contact is not valid!");
        }

    }
}
