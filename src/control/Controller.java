package control;

import protocol.BodyMaker;
import protocol.Header;
import protocol.ResponseReceiver;
import protocol.RequestSender;
import persistence.UserDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    public static final int SIGN_UP = 1;
    public static final int LOG_IN = 2;
    public static final int INQUIRE_AND_PW_CHANGE = 3;
    public static final int INQUIRE_STORE = 4;
    public static final int ORDER = 5;
    public static final int ORDER_CANCLE = 6;
    public static final int INQUIRE_ORDER = 7;
    public static final int WRITE_REVIEW = 8;
    public static final int QUIT = 9;

    SignUpController signUpController;

    public boolean handleCommand(int command, Scanner s, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        ResponseReceiver resReceiver = new ResponseReceiver();
        RequestSender reqSender = new RequestSender();

        switch(command)
        {

            case SIGN_UP:
                Header outHeader = new Header(
                        Header.TYPE_REQ,
                        Header.CODE_USER_DTO,
                        0);
                outputStream.write(outHeader.getBytes());
                signUpController.handleSignUp(s,inputStream,outputStream);
                break;

            case LOG_IN:
                //
                break;

            case INQUIRE_AND_PW_CHANGE:
                //
                break;

            case INQUIRE_STORE:
                //
                break;

            case QUIT:
                /*reqSender.sendQuitReq(outputStream);*/
                return false;

        }

        return true;
    }




}
