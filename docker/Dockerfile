#
# Dockerfile for guestPassword
#

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre11u-nightly

VOLUME /var/www/
COPY guestPassword-0.0.1-SNAPSHOT.jar /var/www/guestPassword.jar
COPY init.sh /var/www/

WORKDIR /var/www/
RUN chmod +x /var/www/init.sh

#Configurable Variables:
ENV SERVICE_KEY=
ENV SSID=Guest
ENV PORT=8013

CMD ["./init.sh"]





