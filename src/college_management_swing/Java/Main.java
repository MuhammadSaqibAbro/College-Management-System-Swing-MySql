/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package college_management_swing.Java;

import college_management_swing.Java.ui.LoginFrame;

/**
 *
 * @author DELL
 */
public class Main{
    
   
    public static void main(String[] args){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {


               if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
               }
            }
    } catch (Exception ex) {
               ex.printStackTrace();
    }

    
        
        
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
