package com.example.johan.cabinclient;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * TODO Project information
 * Project : CabinClient
 * Filename : CabinClient.java
 * Author : Liu Chang
 * Student ID : 20153178[SIST.STDU]
 * Date : 2018/7/7
 * Version : 1.0
 * Description : A screen client put in train cabins to
 * 				 print information of the train and to
 * 				 inform the stuff some jobs.
 *
 */


public class MainActivity extends AppCompatActivity {

    // TODO Attributes
    private String nextStop;
    private String arriveTime;
    private String getOff;
    private String getOn;
    private String train;
    private String terminal;
    private String terminus;
    private String cabin;
    private String time;

    // TODO Views
    private TextView tNextStop = null;
    private TextView tArriveTime = null;
    private TextView tGetOff = null;
    private TextView tGetOn = null;
    private TextView tTrain = null;
    private TextView tTerminal = null;
    private TextView tTerminus = null;
    private TextView tCabin = null;
    private TextView tTime = null;

    // TODO Supports
    private Handler handler = null;   // next stop
    private Handler handler2 = null;  // time
    private Handler handler3 = null;  // arrive time
    private Handler handler4 = null;  // get off
    private Handler handler5 = null;  // get on
    private Handler handler6 = null;  // cabin
    private Handler handler7 = null;  // train
    private Handler handler8 = null;  // terminal
    private Handler handler9 = null;  // terminus
    private Handler handler10 = null; // twinkle
    private boolean twinkleSwitch;
    private int count;
    private int counter;

    // TODO Threads
    private AssistantThread AT;
    private TimerThread TT;
    private ArriveTimeThread ATT;
    private GetOffThread OFFT;
    private GetOnThread ONT;
    private CabinThread CT;
    private TrainThread TAT;
    private TerminalThread TNT;
    private TerminusThread TUT;
    private TwinkleThread TKT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get views
        tNextStop = (TextView) findViewById(R.id.textNextStop);
        tArriveTime = (TextView)findViewById(R.id.textArriveTime);
        tGetOff = (TextView) findViewById(R.id.textGetOff);
        tGetOn = (TextView) findViewById(R.id.textGetOn);
        tTrain = (TextView) findViewById(R.id.textTrain);
        tTerminal = (TextView) findViewById(R.id.textTerminal);
        tTerminus = (TextView) findViewById(R.id.textTerminus);
        tCabin = (TextView) findViewById(R.id.textCabin);
        tTime = (TextView) findViewById(R.id.textTime);

        // initial supports
        twinkleSwitch = false;
        count = 0;
        counter = count + 10;

        // initial handlers
        // next stop
        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        nextStop = data.substring(1,data.length());
                        tNextStop.setText("Next stop:" + nextStop);
                        break;
                    default:
                        break;
                }
            }
        };

        // time
        handler2 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        changeTime(data);
                    default:
                        break;
                }
            }
        };

        // arrive time
        handler3 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        arriveTime = data.substring(1,data.length());
                        tArriveTime.setText("Arrive time:" + arriveTime);
                        break;
                    default:
                        break;
                }
            }
        };

        // get off
        handler4 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        getOff = data.substring(1,data.length());
                        tGetOff.setText("Get off:" + getOff);
                        break;
                    default:
                        break;
                }
            }
        };

        // get on
        handler5 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        getOn = data.substring(1,data.length());
                        tGetOn.setText("Get on:" + getOn);
                        break;
                    default:
                        break;
                }
            }
        };

        // cabin
        handler6 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        cabin = data.substring(1,data.length());
                        tCabin.setText("Cabin:" + cabin);
                        break;
                    default:
                        break;
                }
            }
        };

        // train
        handler7 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        train = data.substring(1,data.length());
                        tTrain.setText("Train:" + train);
                        break;
                    default:
                        break;
                }
            }
        };

        // terminal
        handler8 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        terminal = data.substring(1,data.length());
                        tTerminal.setText("Terminal:" + terminal);
                        break;
                    default:
                        break;
                }
            }
        };

        // terminus
        handler9 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // get data and release
                        String data = (String)msg.obj;
                        terminus = data.substring(1,data.length());
                        tTerminus.setText("Terminus:" + terminus);
                        break;
                    default:
                        break;
                }
            }
        };

        // twinkle
        handler10 = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        String data = (String)msg.obj;
                        if(data.equals("8")){
                            twinkleSwitch = true;
                        }
                        else{
                            twinkle(Integer.parseInt(data));
                        }
                        break;
                    default:
                        break;
                }
            }
        };

        // start threads
        AT = new AssistantThread();
        TT = new TimerThread();
        ATT = new ArriveTimeThread();
        OFFT = new GetOffThread();
        ONT = new GetOnThread();
        CT = new CabinThread();
        TAT = new TrainThread();
        TNT = new TerminalThread();
        TUT = new TerminusThread();
        TKT = new TwinkleThread();

        AT.start();
        TT.start();
        ATT.start();
        OFFT.start();
        ONT.start();
        CT.start();
        TAT.start();
        TNT.start();
        TUT.start();
        TKT.start();

    }

    /**
     * Function name : changeText
     * Description : change the ui
     * Variables : String data
     */
    private void changeTime(String time){
        this.time = time;
        tTime.setText("Current time:" + time);
    }

    /**
     * Function name : twinkle
     * Description : make a twinkle
     * Variables : int state
     */
    private void twinkle(int state){
        if(state == 0){
            tNextStop.setText(Html.fromHtml("<font color=#ee2c2c>Next stop:"  + nextStop + "</font>"));
            tArriveTime.setText(Html.fromHtml("<font color=#ee2c2c>Arrive time:"  + arriveTime + "</font>"));
            tGetOff.setText(Html.fromHtml("<font color=#eead0e>Get off:"  + getOff + "</font>"));
            tGetOn.setText(Html.fromHtml("<font color=#eead0e>Get on:"  + getOn + "</font>"));
            tCabin.setText(Html.fromHtml("<font color=#eead0e>Cabin:"  + cabin + "</font>"));
            tTrain.setText(Html.fromHtml("<font color=#303030>Train:"  + train + "</font>"));
            tTerminal.setText(Html.fromHtml("<font color=#303030>Terminal:"  + terminal + "</font>"));
            tTerminus.setText(Html.fromHtml("<font color=#303030>Terminus:"  + terminus + "</font>"));
        }
        else{
            tNextStop.setText(Html.fromHtml("<font color=#303030>Next stop:"  + nextStop + "</font>"));
            tArriveTime.setText(Html.fromHtml("<font color=#303030>Arrive time:"  + arriveTime + "</font>"));
            tGetOff.setText(Html.fromHtml("<font color=#ee2c2c>Get off:"  + getOff + "</font>"));
            tGetOn.setText(Html.fromHtml("<font color=#ee2c2c>Get on:"  + getOn + "</font>"));
            tCabin.setText(Html.fromHtml("<font color=#ee2c2c>Cabin:"  + cabin + "</font>"));
            tTrain.setText(Html.fromHtml("<font color=#eead0e>Train:"  + train + "</font>"));
            tTerminal.setText(Html.fromHtml("<font color=#eead0e>Terminal:"  + terminal + "</font>"));
            tTerminus.setText(Html.fromHtml("<font color=#eead0e>Terminus:"  + terminus + "</font>"));
        }
    }


    /**
     *
     * TODO Thread to change ui
     *
     */
    public class AssistantThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3461;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * TODO Timer Thread
     *
     */
    public class TimerThread extends Thread{

        // Supports
        private String time;

        /**
         * Function name : TimerThread
         * Description : construction method
         * Variables : void
         */
        TimerThread(){
            time = "";
        }

        public void run(){

            while(true){

                // get current time
                time = "";
                Date day = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time = df.format(day);

                // inc counters
                count++;
                counter++;

                // send to main thread
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = time;
                        handler2.sendMessage(msg);

                        // if need twinkle
                        if(twinkleSwitch){
                            // ask to twinkle
                            Message msg2 = new Message();
                            msg2.what = 0;
                            msg2.obj = "" + (count % 2);
                            handler10.sendMessage(msg2);
                            counter--;

                            // stop twinkle
                            if(count == counter){
                                Message msg3 = new Message();
                                twinkleSwitch = false;
                                msg3.what = 0;
                                msg3.obj = "0";
                                handler10.sendMessage(msg3);
                                counter = count + 10;
                            }
                        }
                    }

                }).start();

                // sleep for one second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }


    /**
     *
     * TODO Arrive time Thread
     *
     */
    public class ArriveTimeThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3462;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler3.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * TODO Get off Thread
     *
     */
    public class GetOffThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3463;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler4.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * TODO Get on Thread
     *
     */
    public class GetOnThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3464;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler5.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * TODO Cabin Thread
     *
     */
    public class CabinThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3450;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler6.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * TODO Train Thread
     *
     */
    public class TrainThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3465;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler7.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * TODO Terminal Thread
     *
     */
    public class TerminalThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3466;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler8.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * TODO Terminus Thread
     *
     */
    public class TerminusThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3467;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler9.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * TODO Twinkle Thread
     *
     */
    public class TwinkleThread extends Thread{

        // Supports
        private String data;
        private final int PORT = 3460;

        public void run(){
            super.run();

            try {
                // open a socket
                DatagramSocket socket = new DatagramSocket(PORT);

                // listening
                while (true) {
                    // release packet
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    buf = packet.getData();
                    data = new String(buf, 0, packet.getLength());

                    // change ui
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = data;
                            // choose road
                            handler10.sendMessage(msg);
                        }
                    }).start();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}