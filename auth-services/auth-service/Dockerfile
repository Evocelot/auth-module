FROM icellmobilsoft/wildfly-mp-full-mariadb-jdbc:3.0.0

ARG SERVICE_NAME
ARG VERSION

COPY target/${SERVICE_NAME}-${VERSION}.war /home/icellmobilsoft/wildfly/standalone/deployments/ROOT.war
