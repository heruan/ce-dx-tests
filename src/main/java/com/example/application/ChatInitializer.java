package com.example.application;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Controller;

import com.vaadin.collaborationengine.CollaborationEngine;
import com.vaadin.collaborationengine.CollaborationMessage;
import com.vaadin.collaborationengine.MessageManager;
import com.vaadin.collaborationengine.SystemUserInfo;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

@Controller
public class ChatInitializer implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        CollaborationEngine ce = CollaborationEngine.getInstance();
        MessageManager messageManager = new MessageManager(
                ce.getSystemContext(), SystemUserInfo.getInstance(), "chat",
                ce);
        UserInfo alice = new UserInfo("alice", "Alice");
        UserInfo bob = new UserInfo("bob", "Bob");
        Instant now = Instant.now();
        messageManager.submit(new CollaborationMessage(alice,
                "DX tests are fun!", now.minus(5, ChronoUnit.MINUTES)));
        messageManager.submit(new CollaborationMessage(bob, "Sure they are!",
                now.minus(5, ChronoUnit.MINUTES)));
        messageManager.submit(
                new CollaborationMessage(bob, "How many messages did I write?",
                        now.minus(5, ChronoUnit.MINUTES)));
        messageManager.submit(new CollaborationMessage(alice,
                "You should see the message count here on the right.",
                now.minus(5, ChronoUnit.MINUTES)));
        messageManager.submit(new CollaborationMessage(bob, "Great, thanks!",
                now.minus(5, ChronoUnit.MINUTES)));
    }

}
