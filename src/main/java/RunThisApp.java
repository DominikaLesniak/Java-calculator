import javax.swing.*;

public class RunThisApp {
    public static void main(String[] argv){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalcApp demo = new CalcApp();
                demo.setFunctionList();
            }
        });
    }
}
