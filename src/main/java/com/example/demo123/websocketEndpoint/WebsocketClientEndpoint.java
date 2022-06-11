package com.example.demo123.websocketEndpoint;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;

@ClientEndpoint
public class WebsocketClientEndpoint {

    private Session userSession = null;
    private MessageHandler messageHandler;

    public WebsocketClientEndpoint(URI endpointURI) {

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param session the session which is opened.
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connecting to websocket server");
        this.userSession = session;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason      the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("Closing connection to websocket server");
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be called when a server send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    @OnMessage
    public void onMessage(ByteBuffer bytes) throws IOException {
        System.out.println("Handle byte buffer");
        userSession.getAsyncRemote().sendPing(bytes);
    }


    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    /**
     * Message handler interface
     */
    public static interface MessageHandler {

        public void handleMessage(String message);
    }
}
