package com.uservotes;
import com.uservotes.model.Idea;
import com.uservotes.model.IdeaDAO;
import com.uservotes.model.SimpleIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        staticFileLocation("/public");
        IdeaDAO dao = new SimpleIdeaDAO();

        before((req, res) -> {
            if (req.cookie("username") != null) {
                req.attribute("username", req.attribute("username"));
            }
        });

        before("ideas", (req, res) -> {
            if (req.attribute("username") == null) {
                res.redirect("/");
                halt();
            }
        });

        get("/", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            model.put("username", req.attribute("username"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sign-in", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            String username = req.queryParams("username");
            res.cookie("username", username);
            model.put("username", username);
            return new ModelAndView(model, "sign-in.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ideas", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("ideas", dao.findAll());
            return new ModelAndView(model, "ideas.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ideas", (req, res) -> {
            String title = req.queryParams("title");
            Idea idea = new Idea(title, req.attribute("username"));
            dao.add(idea);
            res.redirect("ideas");
            return null;
        });

        get ("/ideas/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("idea", dao.findBySlug(req.params("slug")));
            return new ModelAndView(model, "idea.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ideas/:slug/vote", (req, res) -> {
            Idea idea = dao.findBySlug(req.params("slug"));
            idea.addVoter(req.attribute("username"));
            res.redirect("/ideas");
            return null;
        });
    }
}