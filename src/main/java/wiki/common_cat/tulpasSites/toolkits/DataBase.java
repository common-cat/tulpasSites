package wiki.common_cat.tulpasSites.toolkits;

import org.apache.ibatis.session.SqlSession;

import java.io.InputStream;

public interface DataBase {
    SqlSession getDatabase();
    void setDataBase(InputStream xmlConfig);
    //设置数据库配置
    void close();
}
