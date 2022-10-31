package Java;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.File;  // Import the File class

/**
 * Processor of HTTP request.
 */
public class Processor
//        extends Thread
{
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

//    @Override
//    public void run(){
//        try {
//            process();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void process() throws IOException {
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Hello</title></head>");
        output.println("<body><p>Hello, world!</p></body>");
        String requestString = request.getRequestLine().replaceAll("GET ", "");
        requestString = requestString.replaceAll(" HTTP/1.1", "");
        output.print("<p>"+"REQUEST: "+requestString+"<p>");
        CallFunction(requestString);
        output.flush();
        socket.close();
    }

    public void createFile(String filename){
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    int CalculateFibonacci(int cal){
        if (cal <= 1)
            return cal;
        return CalculateFibonacci(cal - 1) + CalculateFibonacci(cal - 2);

    }

    public void CallFunction(String request){
        if (request.contains("/create/")){
            String fileName = request.replaceAll("/create/","");
            System.out.println("FILENAME: "+fileName);
            createFile(fileName);
        } else if (request.contains("/calculate/")) {
            String calculate = request.replaceAll("/calculate/","");
            System.out.println("CALCULATE: "+calculate+"\n");
            int calculate_int = Integer.parseInt(calculate);
            int fibonacci = CalculateFibonacci(calculate_int);
            System.out.println(fibonacci);
        }
    }
}