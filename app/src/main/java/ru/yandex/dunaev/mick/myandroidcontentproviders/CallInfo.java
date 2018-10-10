package ru.yandex.dunaev.mick.myandroidcontentproviders;

class CallInfo {
    private String number;
    private String formatedNumber;
    private String Name;
    private String contactUri;
    private String photoUri;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFormatedNumber() {
        return formatedNumber;
    }

    public void setFormatedNumber(String formatedNumber) {
        this.formatedNumber = formatedNumber;
    }

    public String getContactUri() {
        return contactUri;
    }

    public void setContactUri(String contactUri) {
        this.contactUri = contactUri;
    }


    public CallInfo() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
