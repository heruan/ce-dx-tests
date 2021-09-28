package com.example.application.views.chat;

import java.util.HashMap;
import java.util.Map;

import com.example.application.views.MainLayout;

import com.vaadin.collaborationengine.CollaborationMessageInput;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "chat", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Chat")
public class ChatView extends HorizontalLayout {

    static class MessageSummary extends HorizontalLayout {

        final Span countSpan = new Span();

        int count = 0;

        public MessageSummary(UserInfo user) {
            add(new Span(user.getName() + ": "), countSpan);
        }

        void increment() {
            countSpan.setText(++count + "");
        }
    }

    private final UserInfo userInfo;

    private final CollaborationMessageList messageList;

    private final CollaborationMessageInput messageInput;

    private final VerticalLayout chatLayout = new VerticalLayout();

    private final VerticalLayout summaryLayout = new VerticalLayout();

    private final Map<UserInfo, MessageSummary> stats = new HashMap<>();

    public ChatView() {
        userInfo = new UserInfo("you", "You");
        messageList = new CollaborationMessageList(userInfo, "chat");
        messageInput = new CollaborationMessageInput(messageList);
        messageList.setWidthFull();
        messageInput.setWidthFull();
        chatLayout.addAndExpand(messageList);
        chatLayout.add(messageInput);
        addAndExpand(chatLayout);
        add(summaryLayout);
        setSizeFull();

        // Add code here
    }

    private MessageSummary getSummary(UserInfo user) {
        return stats.computeIfAbsent(user, u -> {
            MessageSummary summary = new MessageSummary(u);
            summaryLayout.add(summary);
            return summary;
        });
    }

}
