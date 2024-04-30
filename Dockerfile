FROM nixos/nix as builder
WORKDIR /workspace
RUN nix-channel --remove nixpkgs
RUN nix-channel --add https://nixos.org/channels/nixos-23.11 pkgs
RUN nix-channel --update
RUN nix-channel --list
RUN nix-env -iA pkgs.maven
RUN nix-env -iA pkgs.jdk21
RUN nix-env -iA pkgs.kotlin

COPY . .

RUN mvn clean install


FROM quay.io/keycloak/keycloak:24.0.3

COPY --from=builder /workspace/target/*jar-with-dependencies.jar /opt/keycloak/providers/