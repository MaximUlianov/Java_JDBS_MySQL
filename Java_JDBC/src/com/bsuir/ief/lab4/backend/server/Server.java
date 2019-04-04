package com.bsuir.ief.lab4.backend.server;

import com.bsuir.ief.lab4.backend.sql.DBHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {


    private static final int port = 8080;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;
    private DBHandler db;

    public Server(){
        db = new DBHandler();
        startServer();
    }

    public void startServer(){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server starts...");
            clientSocket = serverSocket.accept();

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            String request = "";

            while(true){
                request = in.readUTF();

                if(request.equals("show")){
                    getShowRequest();
                }
                else if(request.equals("add")){
                    getAddRequest();
                }
                else if(request.equals("delete")){
                    getDeleteRequest();
                }
                else if(request.equals("update")){
                    getUpdateRequest();
                }
            }
        }catch (Exception e){
        }
    }

    public void getShowRequest() throws ClassNotFoundException, SQLException {
        ResultSet rs = db.showTable();
        try{
            while(rs.next()){
                out.writeUTF("ok");
                out.flush();
                out.writeUTF(rs.getString(2));
                out.flush();
                out.writeUTF(rs.getString(3));
                out.flush();
                out.writeUTF(Integer.toString(rs.getInt(4)));
                out.flush();
                out.writeUTF(Integer.toString(rs.getInt(5)));
                out.flush();
                out.writeUTF(Integer.toString(rs.getInt(6)));
                out.flush();
                out.writeUTF(rs.getString(7));
                out.flush();
            }
            out.writeUTF("end");
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getAddRequest(){
        try{
            db.addInfo(in.readUTF(), in.readUTF(), Integer.parseInt(in.readUTF()), Integer.parseInt(in.readUTF()),
                    Integer.parseInt(in.readUTF()), in.readUTF());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getDeleteRequest() {
        try {
            db.deleteInfo(in.readUTF());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getUpdateRequest(){
        try{
            db.updateInfo(in.readUTF(), in.readUTF(), in.readUTF(), Integer.parseInt(in.readUTF()),
                    Integer.parseInt(in.readUTF()), Integer.parseInt(in.readUTF()), in.readUTF());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
