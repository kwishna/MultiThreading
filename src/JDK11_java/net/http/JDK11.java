package JDK11_java.net.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket.Builder;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JDK11 {

	/**
	 * Copied!
	 * @param args
	 * @throws InterruptedException
	 * https://www.mkyong.com/java/java-11-httpclient-examples/
	 */
	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(6);
		HttpClient httpClient = HttpClient.newBuilder().executor(executor).build();
		Builder webSocketBuilder = httpClient.newWebSocketBuilder();
		WebSocket webSocket = webSocketBuilder.buildAsync(URI.create("wss://echo.websocket.org"), new WebSocket.Listener() {
		    @Override
		    public void onOpen(WebSocket webSocket) {

		        webSocket.sendText("This is a message", true);
		        Listener.super.onOpen(webSocket);
		    }
		    @Override
		    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {

		        if(!webSocket.isOutputClosed()) {
		            webSocket.sendText("This is a message", true);
		        }
		        return Listener.super.onText(webSocket, data, last);
		    }
		    @Override
		    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {

		        executor.shutdown();
		        return Listener.super.onClose(webSocket, statusCode, reason);
		    }
		}).join();

		Thread.sleep(1000);
		webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok").thenRun(() -> System.out.println("Send Close"));

	}

}
