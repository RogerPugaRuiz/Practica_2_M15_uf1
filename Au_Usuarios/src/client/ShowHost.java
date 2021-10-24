/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.util.TimerTask;
import javax.swing.JTextPane;

/**
 *
 * @author roger
 */
public class ShowHost extends TimerTask {

    private JTextPane jTextPane1;
    private JTextPane jTextPane2;

    public ShowHost(JTextPane jTextPane1, JTextPane jTextPane2) {
        this.jTextPane1 = jTextPane1;
        this.jTextPane2 = jTextPane2;
    }

    @Override
    public void run() {
        jTextPane1.setText(ClientSocket.getIp());
        jTextPane2.setText(Integer.toString(ClientSocket.getPort()));
    }

}
