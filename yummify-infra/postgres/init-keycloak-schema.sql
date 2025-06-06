CREATE SCHEMA keycloak AUTHORIZATION dev;

CREATE USER keycloak WITH PASSWORD 'keycloak_password';

GRANT USAGE ON SCHEMA keycloak TO keycloak;
GRANT ALL ON SCHEMA keycloak TO keycloak;

ALTER SCHEMA keycloak OWNER TO keycloak;

ALTER DEFAULT PRIVILEGES IN SCHEMA keycloak
GRANT ALL ON TABLES TO keycloak_user;