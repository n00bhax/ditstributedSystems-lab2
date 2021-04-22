package Server;

import java.util.concurrent.ConcurrentHashMap;
import static spark.Spark.*;

public class Server {

    private final ConcurrentHashMap<String, Account> db = new ConcurrentHashMap<>();

    public Server() {
        port(80);

        post("/account", (req, res) -> {
            String name = req.body();
            Account p = new Account(name);
            if (db.put(name, p) == null)
                return "Account created";
            return "Account with this name already exists";
        });

        get("/balance/:name", (req, res) -> {
            String naam = req.params("name");
            if (db.get(naam) != null)
                return db.get(naam).getBalance();
            return "This person does not exist";
        });

        put("/balance/:name", (req, res) -> {
            String naam = req.params("name");
            Account p = db.get(naam);

            if ( p == null)
                return "This person does not exist";

            double change = Double.parseDouble(req.body());
            p.changeBalance(change);
            return "New balance: " + db.get(naam).getBalance();

        });
    }

    public static void main(String[] args) {
        new Server();
    }

}
