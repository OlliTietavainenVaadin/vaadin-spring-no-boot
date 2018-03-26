package org.vaadin;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@SpringUI
@Push
public class MyUI extends UI {


    private Backend backend = new Backend();
    final ExecutorService executor = Executors.newCachedThreadPool();
    final VerticalLayout layout = new VerticalLayout();


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            loadDataAsynchronously();
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        UI.getCurrent().getPushConnection().disconnect();
        setContent(layout);
    }

    private void loadDataAsynchronously() {

        // We wrap the call in a Future for better control over the operation.
        // This way we can even cancel it if needed.
        final CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(() -> backend.getStrings(),
            executor);

        // Whenever the call is done...
        future.thenAccept(stringList -> {

            // this happens outside of a normal request, so we need access()
            getUI().access(() -> {
                for (String s: stringList) {
                    layout.addComponent(new Label(s));
                }
            });
        });
    }


    @WebServlet(urlPatterns = {"/bar/*", "/foo/*", "/VAADIN/*"}, name = "MyUIServlet", asyncSupported = true)
    public static class MyUIServlet extends SpringVaadinServlet {
    }

    @WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener {

    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {

    }
}
