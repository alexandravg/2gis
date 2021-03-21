package ru.alexandravg.worker;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.sql.Connection;

public class SqlScriptExecutor {

    public static void executeSqlScript(InputStream file, Connection conn) throws Exception {
        Reader reader = new BufferedReader(new InputStreamReader(file));
        ScriptRunner sr = new ScriptRunner(conn);
        sr.setAutoCommit(true);
        sr.setStopOnError(true);
        sr.runScript(reader);
    }
}