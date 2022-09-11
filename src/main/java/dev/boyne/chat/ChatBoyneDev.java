package dev.boyne.chat;

import io.javalin.Javalin;
import dev.boyne.chat.DatabaseManager;
import java.sql.SQLException;
import dev.boyne.chat.PageLoader;

public class ChatBoyneDev {

    public static void registerPaths(Javalin app) {
        PageLoader pageLoader = new PageLoader();
        app.get("/", ctx -> ctx.html(pageLoader.readFile("/index.html")));

        app.get("/static/<file>", ctx -> ctx.html("/" + pageLoader.readFile(ctx.pathParam("file"))));

        app.get("/style/<cssfile>", ctx -> {
            ctx.contentType("css");
            ctx.result(pageLoader.readFile("/styles/" + ctx.pathParam("cssfile")));
        });
    }

    public static void main(String[] args) {

        DatabaseManager databaseManager = new DatabaseManager();

        Javalin app = Javalin.create().start(7070);

        registerPaths(app);

        try
        {
            databaseManager.prepareDatabase();
        }

        finally

        {
            try

            {
                if(databaseManager.connection != null)
                    databaseManager.connection.close();
            }

            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());

            }
        }


    }
}
