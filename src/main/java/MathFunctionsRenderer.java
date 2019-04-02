import javax.swing.*;
import java.awt.*;

public class MathFunctionsRenderer extends JLabel implements ListCellRenderer<MathFunction> {
    public MathFunctionsRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends MathFunction> list, MathFunction value, int index, boolean isSelected, boolean cellHasFocus) {
         String code = value.getFunctionCode();

        setText(value.getFunctionName());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}