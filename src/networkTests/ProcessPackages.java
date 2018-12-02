package networkTests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class ProcessPackages {

    private static final int numberOfTests = 22;

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
        String path = "tests\\network\\";

        for (int i = 1; i <= numberOfTests; i++) {
            String fileName = String.format("%02d", i);
            Scanner scanner = new Scanner(new File(path + fileName));
            int bufferSize = scanner.nextInt();
            Buffer buffer = new Buffer(bufferSize);

            ArrayList<Request> requests = readQueries(scanner);
            ArrayList<Response> responses = processRequests(requests, buffer);

            StringBuilder sb = new StringBuilder();
            StringBuilder actualResult = printResponses(responses, sb);

            scanner = new Scanner(new File(path + fileName + ".a"));
            StringBuilder expectedResult = new StringBuilder();
            while (scanner.hasNext()) {
                expectedResult.append(scanner.nextInt()).append("\n");
            }

            if (sb.toString().equals(expectedResult.toString())) {
                System.out.println("\033[32m" + "Test #" + i + " passed" + "\033[0m");
            } else {
                System.out.println("\033[31m" + "Test #" + i + " failed" + "\033[0m");
                BufferedWriter writer = new BufferedWriter(new FileWriter("actualResult"));
                writer.write(actualResult.toString());
                writer.close();
//                break;
            }
        }
    }

    private static ArrayList<Request> readQueries(Scanner scanner) {
        int requestsCount = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<>();
        for (int i = 0; i < requestsCount; ++i) {
            int arrivalTime = scanner.nextInt();
            int processTime = scanner.nextInt();
            requests.add(new Request(arrivalTime, processTime));
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

    private static StringBuilder printResponses(ArrayList<Response> responses, StringBuilder sb) {
        for (Response response : responses) {
            if (response.dropped) {
                sb.append(-1 + "\n");
            } else {
                sb.append(response.startTime).append("\n");
            }
        }
        return sb;
    }

}

class Buffer {
    private int maxSize;
    private ArrayList<Integer> finishTime;

    Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.finishTime = new ArrayList<>();
    }

    Response process(Request request) {
        // write your code here
        Integer removedElem = null;
        if (!finishTime.isEmpty()) {
                while (finishTime.get(0) <= request.getArrivalTime()) {
                    removedElem = finishTime.remove(0);
                    if (finishTime.isEmpty()) {
                        break;
                    }
                }
        }
        if (finishTime.size() < maxSize) {
            if (finishTime.size() == 0) {
                if (removedElem != null && removedElem == request.getArrivalTime()) {
                    int finishTimeWithRemoved = request.getProcessTime() + removedElem;
                    this.finishTime.add(finishTimeWithRemoved);
                    return new Response(false, removedElem);
                } else {
                    finishTime.add(request.getProcessTime());
                    return new Response(false, request.getArrivalTime());
                }
            } else {
                int lastElemIndex = finishTime.size() - 1;
                finishTime.add(finishTime.get(lastElemIndex) + request.getProcessTime());
                return new Response(false, finishTime.get(finishTime.size() - 2));
            }
        }
        return new Response(true, -1);
    }
}

class Request {
    private int arrivalTime;
    private int processTime;

    Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessTime() {
        return processTime;
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


