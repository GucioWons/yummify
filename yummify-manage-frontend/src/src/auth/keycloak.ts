import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: "http://localhost:8080/",
    realm: "yummify",
    clientId: "yummify-manage-client",
});

export default keycloak;