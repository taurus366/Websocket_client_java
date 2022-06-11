package com.example.demo123.init;

import com.example.demo123.model.BtcBidsAsksDTO;
import com.example.demo123.model.StreamDTO;
import com.example.demo123.websocketEndpoint.WebsocketClientEndpoint;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class init implements CommandLineRunner {

    private final Gson gson;
    final String url = "wss://stream.binance.com:9443/stream?streams=btcusdt@depth@100ms/ethusdt@depth@100ms";

    public init(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void run(String... args) {

        try {

            WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(url));

            // start to listen from websocket server
            clientEndPoint.addMessageHandler(message -> {

                StreamDTO streamDTO = gson.fromJson(message, StreamDTO.class);

                // ASKS
                List<BtcBidsAsksDTO> a = doFilterThenReturnList(streamDTO.getData().getA());

                // BIDS
                List<BtcBidsAsksDTO> b = doFilterThenReturnList(streamDTO.getData().getB());

                print("<" + "-".repeat(30) + ">");

                print(a.toString());

                // Here I try to get the first one from filtered list , So if isn't exists then I return message.
                print("best bid", b.size() > 0 ? b.get(0).toString() : "Server didn't response any bids");

                // Here I try to get the last one from filtered list , So if isn't exists then I return message.
                print("best ask", a.size() > 0 ? a.get(a.size() - 1).toString() : "Server didn't response any asks");

                print(b.toString());
                // Here I get time in milli then convert it to local
                print(Instant.ofEpochMilli(streamDTO.getData().getE()));

                // Here I get text from stream then subSequence it
                print(streamDTO.getStream().toUpperCase().subSequence(0, 3) + "/" + streamDTO.getStream().toUpperCase().subSequence(3, 6));

                print(">" + "-".repeat(30) + "<");

            });

        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }

    }

    private BtcBidsAsksDTO asBtcBidsAsks(String[] strings) {

        BtcBidsAsksDTO btcBidsAsksDTO = new BtcBidsAsksDTO();

        btcBidsAsksDTO.setPrice(Double.parseDouble(strings[0]))
                .setQuantity(Double.parseDouble(strings[1]));

        return btcBidsAsksDTO;
    }

    private <T> void print(T text, String... values) {
        String msg = values.length >= 1 ? text + ": " + values[0] : String.valueOf(text);

        System.out.println(msg);
    }

    private List<BtcBidsAsksDTO> doFilterThenReturnList(List<String[]> list) {

        return list
                .stream()
                .map(this::asBtcBidsAsks)
                .sorted(Comparator.comparing(BtcBidsAsksDTO::getPrice).thenComparing(BtcBidsAsksDTO::getPrice).reversed())
                .collect(Collectors.toList());
    }


}
