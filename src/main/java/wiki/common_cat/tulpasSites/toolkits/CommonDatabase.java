package wiki.common_cat.tulpasSites.toolkits;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class CommonDatabase implements DataBase{
    //数据库包装
    protected SqlSession session;
    public CommonDatabase(SqlSession session){
        this.session=session;
    }
    public CommonDatabase(InputStream xmlConfig){
        setDataBase(xmlConfig);
    }
    @Override
    public SqlSession getDatabase() {
        return session;
    }

    @Override
    public void setDataBase(InputStream xmlConfig) {
        SqlSessionFactory factory=(new SqlSessionFactoryBuilder()).build(xmlConfig);
        session= factory.openSession();
    }

    @Override
    public void close() {

    }
}

