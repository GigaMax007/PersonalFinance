package personalfinance.model;

abstract public class Common {

    public Common() {}

    public String getValueForComboBox(){
        return null;
    }

    // Создаем обработчики, которые будут делать ряд вещей после определенного события
    public void postAdd() {

    } // .. после добавления
    public void postEdit() {

    } // .. после редактирования
    public void postRemove() {

    } // .. после удаления
}
