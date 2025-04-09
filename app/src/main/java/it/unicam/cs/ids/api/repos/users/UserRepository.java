package it.unicam.cs.ids.api.repos.users;

import it.unicam.cs.ids.api.model.user.User;
import it.unicam.cs.ids.api.repos.Repository;

public class UserRepository extends Repository<User> {

    private static UserRepository instance;

    private UserRepository() {
        super();
    }

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

}
