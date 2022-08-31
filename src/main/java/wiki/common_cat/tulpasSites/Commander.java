package wiki.common_cat.tulpasSites;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import wiki.common_cat.tulpasSites.toolkits.CommonDatabase;
import wiki.common_cat.tulpasSites.toolkits.DataBase;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Commander {
    public Commander(){
        try {
            defaultStart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        startClient();

    }
    protected boolean isRun;
    protected Executor executor= Executors.newSingleThreadExecutor();
    protected DataBase dataBase;
    ApplicationContext context;
    public void defaultStart() throws IOException {
        startTomcat();
        startDefaultDatabase();
    }
    public void startTomcat(){
        context=SpringApplication.run(Application.class);
    }
    //启动终端
    public void startClient(){
        System.out.println("client start");
        isRun=true;
        executor.execute(new Client());
    }
    public void stopClient(){
        isRun=false;
        System.out.println("client has stopped");
    }
    //停止这个服务器的服务
    public void stop(){
        ((ConfigurableApplicationContext)context).close();
        //关闭Tomcat容器
        System.out.println("stopped");
    }

    //目前服务器所采用的数据库
    protected void startDefaultDatabase() throws IOException {
        if (dataBase != null) {
            dataBase.close();
        }
        String resource = "mybatis/mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        startDatabase(inputStream);
    }
    //根据默认配置创建数据库
    protected void startDatabase(String address, String user, String pwd, String driver) {
        if (dataBase != null) {
            dataBase.close();
        }
        DataSource dataSource=new PooledDataSource(driver,address,user,pwd);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        dataBase=new CommonDatabase((new SqlSessionFactoryBuilder()).build(configuration).openSession());
    }
    //根据指定参数连接到数据库
    protected void startDatabase(InputStream inputStream) {
        if (dataBase != null) {
            dataBase.close();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        dataBase=new CommonDatabase(sqlSessionFactory.openSession());
    }
    //根据指定配置创建数据库
    protected class Client implements Runnable{
        Scanner scanner=new Scanner(System.in);
        @Override
        public void run() {
            while (isRun){
                analyse(scanner.nextLine());
            }
        }
        protected void analyse(String command){
            switch (command){
                case "start-default":
                    try {
                        Commander.this.defaultStart();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "stop":
                    stop();
                    break;
            }
        }
        //解析命令
    }
}
