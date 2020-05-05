package personalfinance.model;

import personalfinance.exception.ModelException;
import personalfinance.saveload.SaveData;

import java.util.Objects;

public class Article extends Common {

    private String title;

    public Article() {

    }

    public Article(String title) throws ModelException {
        if (title.length() == 0) throw new ModelException(ModelException.TITLE_EMPTY);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        final Article other = (Article) o;
        if (!Objects.equals(this.title, other.title)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public String getValueForComboBox() {
        return title;
    }

    @Override
    public void postEdit(SaveData sd) {
        for (Transaction t :
                sd.getTransactions()) {
            if (t.getArticle().equals(sd.getOldCommon())) t.setArticle(this);
        }
    }
}
