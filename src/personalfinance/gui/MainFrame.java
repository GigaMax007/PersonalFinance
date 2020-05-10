package personalfinance.gui;

import personalfinance.gui.menu.MainMenu;
import personalfinance.gui.toolbar.MainToolBar;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Refresh {

    private final GridBagConstraints constraints;
    private final MainMenu mm;
    private final MainToolBar tb;

    public MainFrame() {
        super(Text.get("PROGRAMM_NAME"));
        setResizable(false);
        setIconImage(Style.ICON_MAIN.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Временно

        mm = new MainMenu(this);
        setJMenuBar(mm);

        setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth =2;

        tb = new MainToolBar();
        add(tb, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;

//        add(new FunctionsToolBar(), constraints);
//        add leftpanel
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
        mm.refresh();
        pack();
    }
}
