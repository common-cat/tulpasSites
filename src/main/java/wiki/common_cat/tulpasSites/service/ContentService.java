package wiki.common_cat.tulpasSites.service;

import wiki.common_cat.tulpasSites.toolkits.DataBase;

public class ContentService {
    protected DataBase dataBase;
    public ContentService(DataBase dataBase){
        this.dataBase=dataBase;
    }
    public String getContentPage(String id){
        return "";
    }
}
