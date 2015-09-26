package UI;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Darksirion on 19.09.15.
 */
public class Programmer {

    private final SimpleStringProperty name;
    private final SimpleStringProperty group;
    private final SimpleStringProperty lang;
    private final SimpleStringProperty code;
    private final SimpleStringProperty note;
    private final SimpleStringProperty source;
    private final SimpleStringProperty creator;
    private final SimpleStringProperty createDate;

    public Programmer(String name, String group, String lang, String code, String note, String source, String creator, String createDate) {
        this.name = new SimpleStringProperty(name);
        this.group = new SimpleStringProperty(group);
        this.lang = new SimpleStringProperty(lang);
        this.code = new SimpleStringProperty(code);
        this.note = new SimpleStringProperty(note);
        this.source =new SimpleStringProperty(source);
        this.creator =new SimpleStringProperty(creator);
        this.createDate = new SimpleStringProperty(createDate);
    }



    public String getName() {
        return name.get();
    }

    public void setName(String fName) {
        name.set(fName);
    }

    public String getGroup() {
        return group.get();
    }

    public void setGroup(String fName) {
        group.set(fName);
    }

    public String getLang() {
        return lang.get();
    }

    public SimpleStringProperty langProperty() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang.set(lang);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getNote() {
        return note.get();
    }

    public SimpleStringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public String getSource() {
        return source.get();
    }

    public SimpleStringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getCreator() {
        return creator.get();
    }

    public void setCreator(String creator){
        this.creator.set(creator);
    }

    public String getCreateDate() {
        return createDate.get();
    }

    public SimpleStringProperty createDateProperty() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }
}

