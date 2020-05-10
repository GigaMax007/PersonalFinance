package personalfinance.gui.dialog;

import personalfinance.settings.Style;
import personalfinance.settings.Text;

import javax.swing.*;

public class AboutDialog extends JDialog {

    public AboutDialog() {
        super();
        init();
        setTitle(Text.get("DIALOG_ABOUT_TITLE"));
        setIconImage(Style.ICON_ABOUT.getImage());
        setResizable(false);
    }

    private void init() {
        JEditorPane pane =new JEditorPane("text/html", Text.get("ABOUT"));
        pane.setEditable(false);
        pane.setOpaque(false);
// Реализация действия на клик мышки по ссылке. ЭТА РЕАЛИЗАЦИЯ МЕТОДА ПОД LINUX НЕ РАБОТАЕТ
//        pane.addHyperlinkListener(new HyperlinkListener() {
//            @Override
//            public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
//                if (HyperlinkEvent.EventType.ACTIVATED.equals(hyperlinkEvent.getEventType()));
//                try {
//                    Desktop.getDesktop().browse(hyperlinkEvent.getURL().toURI());
//                } catch (IOException | URISyntaxException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        add(pane);
        pack();
        setLocationRelativeTo(null); // По центру рабочего стола
    }
}
