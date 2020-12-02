package soa.eip;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Message;


@Component
public class Router extends RouteBuilder {

  public static final String DIRECT_URI = "direct:twitter";

  @Override
  public void configure() {
    from(DIRECT_URI)
      .log("Body contains \"${body}\"")
      .log("Searching twitter for \"${body}\"!")
      .process(exchange -> {
        // Getting input and creating output String
        String inputBody = exchange.getIn().getBody(String.class);
        StringBuilder outputBody = new StringBuilder();

        // Number of tweets to retrieve
        String number;

        // Splitting the input. One could be "max:[0-9]+$"
        String[] tokens = inputBody.split(" ");

        for (String t : tokens) {
          // We found the condition "max" and it's the last token
          if (t.matches("max:[0-9]+$")) {
            number = t.split(":")[1];
            outputBody.append("?count=").append(number);
          }
          else {
            // Appending the input
            outputBody.append(t).append(" ");
          }
        }

        // Ending of the process step
        exchange.getOut().setBody(outputBody);
      })
      .toD("twitter-search:${body}")
      .log("Body now contains the response from twitter:\n${body}");
  }
}
