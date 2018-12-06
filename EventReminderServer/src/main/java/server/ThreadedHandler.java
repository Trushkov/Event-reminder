package server;

import file.JsonParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import shared.Message;

public class ThreadedHandler implements Runnable
{
    private Socket incoming;
    private Timer eventTimer;
    private TimerTask eventTask;
    private String login;
    private Message msg;

    public ThreadedHandler(Socket incoming)
    {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(incoming.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(incoming.getOutputStream());
            msg = (Message) in.readObject();
            login = msg.getLogin();
            sendEvent(out);
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendEvent(ObjectOutputStream out){
        JsonParser jsonParser = new JsonParser();
        eventTimer = new Timer();
        eventTask = new TimerTask() {
            @Override
            public void run() {
                while (incoming.isConnected()){
                    try {
                        out.writeObject(new Message("server",jsonParser.getEvent(login)));
                    }catch (SocketException se){
                        System.out.println("Клиент "+msg.getLogin()+" отключился");
                        eventTimer.cancel();
                        break;
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        eventTimer.schedule(eventTask,1000,60000);
    }
}
