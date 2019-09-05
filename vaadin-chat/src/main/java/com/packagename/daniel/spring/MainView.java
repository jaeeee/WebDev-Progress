package com.packagename.daniel.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.awt.*;

@Route
@Push
@CssImport("frontend://styles/styles.css")
//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> messages;
    private String username;

    public MainView(UnicastProcessor<ChatMessage> publisher, Flux<ChatMessage> messages) {
        this.publisher = publisher;
        this.messages = messages;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-view");
        H1 header = new H1("Vaadin Chat");
        header.getElement().getThemeList().add("dark");

        add(header);

        askUserName();
    }

    private void askUserName() {
    HorizontalLayout usernameLayout = new HorizontalLayout();
    TextField usernameField = new TextField();
    Button startButton = new Button("Start chatting");
    usernameLayout.add(usernameField, startButton);

    startButton.addClickListener(click -> {
      username = usernameField.getValue();
       remove(usernameLayout);
       showChat();
    });
    add(usernameLayout);
    }

    private void showChat() {
        MessageList messageList = new MessageList();
        add(messageList, createInputLayout());
        expand(messageList);

        messages.subscribe(message -> {
            getUI().ifPresent(ui ->
                    ui.access(() ->  messageList.add(new Paragraph(
                            message.getFrom() + ": " + message.getMessage()
           ))
                    ));
        });
    }

    private Component createInputLayout() {
        HorizontalLayout inputLayout = new HorizontalLayout();
        inputLayout.setWidth("100%");
        TextField messageField = new TextField();
        Button sendButton = new Button("Send");
        sendButton.getElement().getThemeList().add("primary");

        inputLayout.add(messageField, sendButton);
        inputLayout.expand(messageField);

        sendButton.addClickListener(click -> {
           publisher.onNext(new ChatMessage(username, messageField.getValue()));
           messageField.clear();
           messageField.focus();
        });
        messageField.focus();

        return inputLayout;
    }

}
