package com.example.myandroidsupportlibrary.DatabaseSupport;

public class TaskDescription
{
    /* This class implements a database access task, every task consists of a code representing
     * the kind of operation we want to perform (connect to the database, login to the database,
     * retrieve items, delete items, etc) and a set of parameters that provide required info to
     * perform the task (Ids of items to retrieve or delete, etc).
     */

    private int code;
    private Object[] params;

    //The following are constants representing the built in reserved database operation codes, applications can
    //extend this class and provide new operations
    public static final int CONNECT_DATABASE_TASK = 0;
    public static final int LOGIN_DATABASE_TASK = 1;

    public TaskDescription(int initCode, Object[] initParams)
    {
        code = initCode;
        params = initParams;
    }

    public int getCode()
    {
        return code;
    }

    public Object getParam(int index)
    {
        Object param = null;

        if (index >= 0 && index < params.length)
            param = params[index];

        return param;
    }
}
