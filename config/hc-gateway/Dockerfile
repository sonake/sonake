FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER hc@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /hc-gateway

WORKDIR /hc-gateway

EXPOSE 9999

ADD ./hc-gateway/target/hc-gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar hc-gateway.jar
