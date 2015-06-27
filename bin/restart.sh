#!/bin/sh

PID=`ps -ef | grep "jre1.6.0_43" | awk '{print $2}'`

echo "PID is $PID"

if [ "$PID" != "" ]; then
    /usr/local/tomcat/bin/catalina.sh jpda stop
    
    sleep 5

    PID2=`ps -ef | grep "jre1.6.0_43" | awk '{print $2}'`

    echo "PID2 is $PID2"

    if [ "$PID2" != "" ]; then
        kill -9 $PID2
    fi
fi

/usr/local/tomcat/bin/catalina.sh jpda start

sleep 5
