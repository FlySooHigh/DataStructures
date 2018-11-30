package network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Request {
    private int arrivalTime;
    private int processTime;

    Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }
}

class Response {
    boolean dropped;
    int startTime;

    Response(boolean dropped, int startTime) {
        this.dropped = dropped;
        this.startTime = startTime;
    }
}

class Buffer {
    private int size;
    private ArrayList<Integer> finishTime;

    Buffer(int size) {
        this.size = size;
        this.finishTime = new ArrayList<>();
    }

    Response process(Request request) {
        // write your code here
        return new Response(false, -1);
    }
}

class ProcessPackages {
    private static ArrayList<Request> readQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> processRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<>();
        for (Request request : requests) {
            responses.add(buffer.process(request));
        }
        return responses;
    }

    private static void printResponses(ArrayList<Response> responses) {
        for (Response response : responses) {
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.startTime);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int bufferSize = scanner.nextInt();
        Buffer buffer = new Buffer(bufferSize);

        ArrayList<Request> requests = readQueries(scanner);
        ArrayList<Response> responses = processRequests(requests, buffer);
        printResponses(responses);
    }
}
