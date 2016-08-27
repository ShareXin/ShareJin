/*
    ShareJin                                             Author: Jorge Huicochea

    GUI using Swing to Tweet via Twitter4j, intended as an alternative to ShareX
 */

package learn;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import twitter4j.Twitter;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import org.ini4j.*;

public final class GUI extends javax.swing.JFrame {
    
    static String cmdName = "[ShareJin] ";
    
    public GUI() {
        initComponents();
    }

    public void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ShareJin");
        setLocation(new java.awt.Point(480, 230));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setMargin(new java.awt.Insets(7, 7, 7, 7));
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Cancel");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1ActionPerformed(evt);
        });

        jButton2.setText("Tweet");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jButton2MouseClicked(evt);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }

    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    public void jButton2MouseClicked(java.awt.event.MouseEvent evt) throws IOException {
        this.setVisible(false);
        System.out.println(cmdName + "Closing...");
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String osVer = System.getProperty("os.version");
        String osNameCheck = osName.toLowerCase();
        String osConfigLocation;
        String fileString;
        if (osNameCheck.contains("linux")) {
            osConfigLocation = "/.config/ShareJin";
            fileString = "/tmp/ShareJin_tmp.jpg";
        }
        else if (osNameCheck.contains("windows")) {
            osConfigLocation = "\\AppData\\Local\\ShareJin";
            fileString = "C:\\tmp\\ShareJin_tmp.jpg";
        }
        else if (osNameCheck.contains("mac")) {
            osConfigLocation = "/Library/Application Support/ShareJin";
            fileString = "/tmp/ShareJin_tmp.jpg";
        }
        else {
            osConfigLocation = "/ShareJin";
            fileString = "/tmp/ShareJin_tmp.jpg";
        }
        
        String userDirectory = System.getProperty("user.home") + osConfigLocation;
        File iniReal;
        Wini ini = new Wini(iniReal = new File(userDirectory + "/config.ini"));
        String iniName = iniReal.toString();
        System.out.println (cmdName + "OS: " + osName);
        System.out.println (cmdName + "Arch: " + osArch);
        System.out.println (cmdName + "OS Ver.: " + osVer);
        System.out.println (cmdName + "Conf: " + iniName);
        String consumerKeyStr = ini.get("Twitter", "api");
        String consumerSecretStr = ini.get("Twitter", "api_secret");
        String accessTokenStr = ini.get("Twitter", "access");
        String accessTokenSecretStr = ini.get("Twitter", "access_secret");
        File file = new File("/tmp/ShareJin_tmp.jpg");
        System.out.println(cmdName + "Image: " + fileString);
        String statusMessage = jTextArea1.getText();
        System.out.println (cmdName + "Tweet: "  + jTextArea1.getText());

        try {
	    Twitter twitter = new TwitterFactory().getInstance();

	    twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
	    AccessToken accessToken = new AccessToken(accessTokenStr,
		accessTokenSecretStr);

	    twitter.setOAuthAccessToken(accessToken);
            
            StatusUpdate status = new StatusUpdate(statusMessage);
            status.setMedia(file);
            twitter.updateStatus(status);
            //twitter.updateStatus(statusMessage);

	    System.out.println(cmdName + "Success.");
	    }
        catch (TwitterException te)
        {
        }
        System.exit(0);
    } 
   public static void main(String args[]) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        /* Set either GTK, default macOS, or default Windows style/theme */
        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        
        /* Make GUI visible */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration
    public javax.swing.JButton jButton1;
    javax.swing.JButton jButton2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    // End of variables declaration
}
