package dev.boyne.chat;

import java.io.*;

public class PageLoader {
    public String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
    public String readFile(String fileName) {
        try {
            return this.readFromInputStream(ChatBoyneDev.class.getResourceAsStream("/index.html"));
        } catch (IOException e) {
            return "Error: " + e;
        }
    }
}
