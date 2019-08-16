#--------------------------------------------------------------------
#使用基础镜像jdk8u181:latest

FROM jdk8u181:latest
#指定镜像创建者信息

MAINTAINER xzyuan

#切换镜像的目录，进入/soft目录（基础镜像是一个linux系统，可参照linux查看相应目录）
WORKDIR /soft

#在/soft/下创建java目录，用来存放jdk
RUN mkdir java

#切换镜像的目录至/soft/java
WORKDIR /soft/java

#在/soft/java下创建jdk目录，用来存放jdk文件
RUN mkdir jdk

#切换镜像的目录至/soft/java/jdk
WORKDIR /soft/java/jdk

#将宿主机的jdk目录下的全部文件考入至镜像的/soft/java/jdk目录下
ADD jdk /soft/java/jdk

#切换镜像的目录至/opt
#WORKDIR /opt

#在/opt下创建tomcat目录，用来存放tomcat文件
#RUN mkdir tomcat

#将宿主机的tomcat目录下的全部文件考入至镜像的/opt/tomcat目录下
#ADD tomcat /opt/tomcat

#设置环境变量

ENV JAVA_HOME=/soft/java/jdk
ENV JAVA_BIN=/soft/java/jdk/bin
ENV PATH=$PATH:$JAVA_HOME/bin
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

#设置容器启动时执行的操作

#CMD /opt/tomcat/bin/catalina.sh run

#---------------------------------------------------------------------