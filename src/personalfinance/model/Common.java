package personalfinance.model;

import personalfinance.saveload.SaveData;

abstract public class Common {

    public Common() {}

    public String getValueForComboBox() {
        return null;
    }

    // Создаем обработчики, которые будут делать ряд вещей после определенного события
    public void postAdd(SaveData sd) {

    } // .. после добавления
    public void postEdit(SaveData sd) {

    } // .. после редактирования
    public void postRemove(SaveData sd) {

    } // .. после удаления
}
