#!/bin/sh

PID=`ps -ef | grep "jdk1.6.0_43" | grep -v "grep" | awk '{print $2}'`

if [ "$PID" != "" ]; then
	echo "stop PID:$PID..."
    #/usr/local/tomcat/bin/catalina.sh jpda stop
	kill -9 $PID
    sleep 5
fi

echo "start..."
/usr/local/tomcat/bin/catalina.sh jpda start
sleep 5
