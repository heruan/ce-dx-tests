package com.collaborationengine.dx.views;

import com.vaadin.collaborationengine.CollaborationEngine;
import com.vaadin.collaborationengine.CollaborationList;
import com.vaadin.collaborationengine.TopicConnection;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@PageTitle("CE DX Test")
@Route(value = "", layout = MainLayout.class)
public class TestView extends VerticalLayout {

    final UserInfo localUser = new UserInfo("local");

    final Button addButton = new Button("Add an item to the list");

    final Button showButton = new Button("Show the current item value");

    final Button replaceButton = new Button("Replace the item value");

    final Button removeButton = new Button("Remove the item from the list");

    public TestView() {
        add(addButton, showButton, replaceButton, removeButton);
        CollaborationEngine.getInstance().openTopicConnection(this, "dx-test",
                localUser, this::onConnectionActivate);
    }

    Registration onConnectionActivate(TopicConnection connection) {
        CollaborationList values = connection.getNamedList("values");
        Registration r1 = addButton.addClickListener(event -> {
            // Insert an item with the value "foo" in the list
        });
        Registration r2 = showButton.addClickListener(event -> {
            // Show a notification with the current item value
        });
        Registration r3 = replaceButton.addClickListener(event -> {
            // Replace the item value with "bar"
        });
        Registration r4 = removeButton.addClickListener(event -> {
            // Remove the item from the list
        });
        return Registration.combine(r1, r2, r3, r4);
    }
}
