import java.util.HashMap;

import static spark.Spark.*;

public class Server {

    private HashMap<String, Person> db = new HashMap<>();

    public Server() {
        port(80);

        post("/account", (req, res) -> {
            String name = req.body();
            Person p = new Person(name);
            db.put(name, p);
            return "Account created";
        });

        get("/balance/:naam", (req, res) -> {
            String naam = req.params("naam");
            if (db.get(naam) != null)
                return db.get(naam).getBalance();
            return "This person does not exist";
        });

        put("/balance/:naam", (req, res) -> {
            String naam = req.params("naam");
            Person p = db.get(naam);
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
