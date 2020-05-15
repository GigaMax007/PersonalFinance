package personalfinance.gui.panel;

import personalfinance.gui.MainFrame;
import personalfinance.gui.table.ArticleTableData;
import personalfinance.gui.toolbar.FunctionsToolBar;
import personalfinance.settings.Settings;
import personalfinance.settings.Style;

public class ArticlePanel extends RightPanel {

    public ArticlePanel(MainFrame frame) {
        super(frame, new ArticleTableData(Settings.COUNT_OVERVIEW_ROWS), "ARTICLES", Style.ICON_PANEL_ARTICLES, new FunctionsToolBar());
    }
}