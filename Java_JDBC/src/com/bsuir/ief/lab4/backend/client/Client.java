package com.bsuir.ief.lab4.backend.client;

import com.bsuir.ief.lab4.backend.sql.Faculty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final int port = 8080;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(){
        startClient();
    }

    public void startClient() {
        try{
            socket = new Socket("localhost", port);
            System.out.print("Client starts...");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Faculty> showRequest(){
        int counter = 1;
        ObservableList<Faculty> faculties = FXCollections.observableArrayList();
        try {
            out.writeUTF("show");
            out.flush();
            while (!in.readUTF().equals("end")) {
                Faculty faculty = new Faculty(counter++, in.readUTF(), in.readUTF(), Integer.parseInt(in.readUTF()),
                        Integer.parseInt(in.readUTF()), Integer.parseInt(in.readUTF()), in.readUTF());
                faculties.add(faculty);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return faculties;
    }

    public void addRequest(Faculty faculty){
        try{
            out.writeUTF("add");
            out.flush();
            out.writeUTF(faculty.getName());
            out.flush();
            out.writeUTF(faculty.getDean());
            out.flush();
            out.writeUTF(Integer.toString(faculty.getSpecialitiesQuantity()));
            out.flush();
            out.writeUTF(Integer.toString(faculty.getStudentsQuantity()));
            out.flush();
            out.writeUTF(Integer.toString(faculty.getTeachersQuantity()));
            out.flush();
            out.writeUTF(faculty.getAddress());
            out.flush();
        }catch (Exception e){

        }
    }

    public void deleteRequest(Faculty faculty){
        try {
            out.writeUTF("delete");
            out.flush();
            out.writeUTF(faculty.getName());
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateRequest(String oldSpeciality, Faculty faculty){
        try{
            out.writeUTF("update");
            out.flush();
            out.writeUTF(oldSpeciality);
            out.flush();
            out.writeUTF(faculty.getName());
            out.flush();
            out.writeUTF(faculty.getDean());
            out.flush();
            out.writeUTF(Integer.toString(faculty.getSpecialitiesQuantity()));
            out.flush();
            out.writeUTF(Integer.toString(faculty.getStudentsQuantity()));
            out.flush();
            out.writeUTF(Integer.toString(faculty.getTeachersQuantity()));
            out.flush();
            out.writeUTF(faculty.getAddress());
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
