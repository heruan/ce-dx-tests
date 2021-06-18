package com.example.application.views.chat;

import java.util.UUID;

import com.example.application.views.MainLayout;

import com.vaadin.collaborationengine.CollaborationMessageInput;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "chat", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Chat")
public class ChatView extends VerticalLayout {

    static class ChatTab extends Tab {

        final Span count = new Span("0");

        final String channel;

        public ChatTab(String title, String channel) {
            add(new HorizontalLayout(new Span(title), count));
            this.channel = channel;
        }

        void setCount(int count) {
            this.count.setText("" + count);
        }

        public String getChannel() {
            return channel;
        }
    }

    public ChatView() {
        addClassName("chat-view");
        setSpacing(false);
        // UserInfo is used by Collaboration Engine and is used to share details
        // of users to each other to able collaboration. Replace this with
        // information about the actual user that is logged, providing a user
        // identifier, and the user's real name. You can also provide the users
        // avatar by passing an url to the image as a third parameter, or by
        // configuring an `ImageProvider` to `avatarGroup`.
        UserInfo userInfo = new UserInfo(UUID.randomUUID().toString(),
                "Steve Lange");

        // Tabs allow us to change chat rooms.
        ChatTab generalTab = new ChatTab("General", "#general");
        ChatTab supportTab = new ChatTab("Support", "#support");
        ChatTab casualTab = new ChatTab("Casual", "#casual");

        Tabs tabs = new Tabs(generalTab, supportTab, casualTab);
        tabs.setWidthFull();

        // `CollaborationMessageList` displays messages that are in a
        // Collaboration Engine topic. You should give in the user details of
        // the current user using the component, and a topic Id. Topic id can be
        // any freeform string.
        CollaborationMessageList list = new CollaborationMessageList(userInfo,
                generalTab.getChannel());
        list.setWidthFull();
        list.addClassNames("chat-view-message-list");

        // `CollaborationMessageInput is a textfield and button, to be able to
        // submit new messages. To avoid having to set the same info into both
        // the message list and message input, the input takes in the list as an
        // constructor argument to get the information from there.
        CollaborationMessageInput input = new CollaborationMessageInput(list);
        input.addClassNames("chat-view-message-input");
        input.setWidthFull();

        // Layouting
        add(tabs, list, input);
        setSizeFull();
        expand(list);

        // Change the topic id of the chat when a new tab is selected
        tabs.addSelectedChangeListener(event -> {
            ChatTab selectedTab = (ChatTab) event.getSelectedTab();
            String channel = selectedTab.getChannel();
            list.setTopic(channel);
        });
    }

}